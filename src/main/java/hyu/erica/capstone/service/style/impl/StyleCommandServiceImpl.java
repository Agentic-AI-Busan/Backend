package hyu.erica.capstone.service.style.impl;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Style;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.domain.enums.City;
import hyu.erica.capstone.domain.enums.TripPlanStatus;
import hyu.erica.capstone.repository.AttractionRepository;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.RestaurantRepository;
import hyu.erica.capstone.repository.StyleRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.service.async.StyleBackgroundTaskService;
import hyu.erica.capstone.service.style.StyleCommandService;
import hyu.erica.capstone.web.dto.style.request.UserStyleRequestDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleInitResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.TripPlanResponseDTO;
import jakarta.persistence.EntityManager;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StyleCommandServiceImpl implements StyleCommandService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final TripPlanRepository tripPlanRepository;


    private final StyleBackgroundTaskService asyncService;

    private final static String TITLE = "여행 계획";
    private final static String PROFILE_IMAGE = "https://i.imgur.com/3zX2Z1b.png";

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
    public TripPlanResponseDTO submitStyle(Long styleId, Long userId) {

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._STYLE_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        TripPlan tripPlan = TripPlan.builder()
                .user(user)
                .startDate(style.getStartDate())
                .endDate(style.getEndDate())
                .title(TITLE)
                .profileImage(PROFILE_IMAGE)
                .tripPlanStatus(TripPlanStatus.PROGRESSING)
                .build();

        TripPlan saved = tripPlanRepository.save(tripPlan);
        tripPlanRepository.flush();

        // 비동기 처리 시작
        asyncService.handleTripPlanDetails(saved.getId(), style, user);

        return TripPlanResponseDTO.of(saved.getId(), saved.getTripPlanStatus());
    }
}
