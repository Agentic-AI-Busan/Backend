package hyu.erica.capstone.service.style;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.client.PlanClient;
import hyu.erica.capstone.domain.Style;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.domain.enums.City;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.StyleRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.capstone.web.dto.client.RestaurantRequestDTO;
import hyu.erica.capstone.web.dto.style.request.UserStyleRequestDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleFinalResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleInitResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StyleCommandServiceImpl implements StyleCommandService {

    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final RestaurantRepository restaurantRepository;
    private final AttractionRepository attractionRepository;

    private final PreferRestaurantRepository preferRestaurantRepository;
    private final PreferAttractionRepository preferAttractionRepository;

    private final PlanClient planClient;

    @Override
    public UserStyleInitResponseDTO initStyle(Long userId) {
        User user = userRepository.findById(userId).orElseThrow( () -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        Style style = Style.builder()
                .city(City.BUSAN)
                .user(user)
                .build();
        Style save = styleRepository.save(style);

        return UserStyleInitResponseDTO.of(userId, save.getId());
    }

    @Override
    public UserStyleResponseDTO updateStyle(Long userId, Long styleId, UserStyleRequestDTO request) {
        Style style = styleRepository.findById(styleId).orElseThrow( () -> new GeneralException(ErrorStatus._STYLE_NOT_FOUND));

        if (!Objects.equals(style.getUser().getId(), userId))
            throw new GeneralException(ErrorStatus._UNAUTHORIZED_USER);

        style.updateStyle(request.startDate(), request.endDate(), request.preferActivity(), request.requirement());

        Style save = styleRepository.save(style);

        return UserStyleResponseDTO.of(save);
    }

    @Override
    public UserStyleFinalResponseDTO submitStyle(Long styleId) {
        Style style = styleRepository.findById(styleId).orElseThrow( () -> new GeneralException(ErrorStatus._STYLE_NOT_FOUND));


        StringBuilder sb = new StringBuilder();
        sb.append("여행 지역 : ").append(style.getCity().name()).append("\n")
                .append("시작 날짜 : ").append(style.getStartDate()).append("\n")
                .append("종료 날짜 : ").append(style.getEndDate()).append("\n")
                .append("선호 활동 : ").append(style.getPreferActivity()).append("\n")
                .append("추가 요구 사항 : ").append(style.getRequirement());

        //AttractionRequestDTO attractions = planClient.getAttractions(sb.toString());
        RestaurantRequestDTO restaurants = planClient.getRestaurants(sb.toString());

        // TODO 매핑 테이블에 데이터 저장하기

        List<Long> restaurantIds = restaurants.restaurant_ids();




        return UserStyleFinalResponseDTO.of(restaurants.restaurant_ids(), List.of());
    }
}
