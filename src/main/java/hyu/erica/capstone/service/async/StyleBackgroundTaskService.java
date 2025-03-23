package hyu.erica.capstone.service.async;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.client.PlanClient;
import hyu.erica.capstone.domain.Attraction;
import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.domain.Style;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.domain.enums.TripPlanStatus;
import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.capstone.web.dto.client.RestaurantRequestDTO;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StyleBackgroundTaskService {

    private final PlanClient planClient;
    private final TripPlanRepository tripPlanRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttractionRepository attractionRepository;
    private final PreferRestaurantRepository preferRestaurantRepository;
    private final PreferAttractionRepository preferAttractionRepository;

    @Async
    @Transactional
    public void handleTripPlanDetails(Long tripPlanId, Style style, User user) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        log.info("handleTripPlanDetails: " + tripPlanId);

        User managedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));


        try {
            String prompt = buildPrompt(style);

            // 외부 API 병렬 호출
            CompletableFuture<AttractionRequestDTO> attractionFuture =
                    CompletableFuture.supplyAsync(() -> planClient.getAttractions(prompt));
            CompletableFuture<RestaurantRequestDTO> restaurantFuture =
                    CompletableFuture.supplyAsync(() -> planClient.getRestaurants(prompt));

            AttractionRequestDTO attractions = attractionFuture.join();
            RestaurantRequestDTO restaurants = restaurantFuture.join();

            // 레스토랑 저장
            for (Long restaurantId : restaurants.restaurant_ids()) {
                Restaurant restaurant = restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus._RESTAURANT_NOT_FOUND));
                preferRestaurantRepository.save(PreferRestaurant.builder()
                        .restaurant(restaurant)
                        .user(managedUser)
                        .isPrefer(true)
                        .tripPlan(tripPlan)
                        .build());
            }

            // 어트랙션 저장
            for (Long attractionId : attractions.attraction_ids()) {
                Attraction attraction = attractionRepository.findById(attractionId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus._ATTRACTION_NOT_FOUND));
                preferAttractionRepository.save(PreferAttraction.builder()
                        .attraction(attraction)
                        .user(managedUser)
                        .tripPlan(tripPlan)
                        .build());
            }

            tripPlan.setStatus(TripPlanStatus.DONE);

        } catch (Exception e) {
            tripPlan.setStatus(TripPlanStatus.FAILED);
            log.info(e.toString());
            // 로그 남기기
        }

        tripPlanRepository.save(tripPlan);
    }

    private String buildPrompt(Style style) {
        return new StringBuilder()
                .append("여행 지역 : ").append(style.getCity().name()).append("\n")
                .append("시작 날짜 : ").append(style.getStartDate()).append("\n")
                .append("종료 날짜 : ").append(style.getEndDate()).append("\n")
                .append("선호 활동 : ").append(style.getPreferActivity()).append("\n")
                .append("추가 요구 사항 : ").append(style.getRequirement())
                .toString();
    }
}
