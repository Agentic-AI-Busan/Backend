package hyu.erica.capstone.service.user.impl;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.TripPlan;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.TripScheduleItemRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.security.JwtTokenProvider;
import hyu.erica.capstone.service.user.UserCommandService;
import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateTripPlanRequestDTO;
import hyu.erica.capstone.web.dto.user.response.MyTripPlanResponse;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final TripPlanRepository tripPlanRepository;
    private final TripScheduleItemRepository tripScheduleItemRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public Long signUp(SignUpRequestDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new GeneralException(ErrorStatus._DUPLICATED_EMAIL);
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .birthday(request.birthDate())
                .phoneService(request.phoneService())
                .phoneNumber(request.phoneNumber())
                .termsOfService(request.termsOfService())
                .privacyPolicy(request.privacyPolicy())
                .marketingAgreement(request.marketingAgreement())
                .gender(request.gender())
                .profileImage(request.profileImage())
                .build();

        User save = userRepository.save(user);

        return save.getId();
    }

    @Override
    public String signIn(SignInRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        if (Objects.equals(user.getPassword(), request.password())) {
            return jwtTokenProvider.createToken(user.getId());
        }
        else {
            throw new GeneralException(ErrorStatus._INVALID_PASSWORD);
        }
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void updateInfo(Long userId, UpdateInfoRequestDTO request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        user.updateInfo(request.nickname(), request.phoneNumber(), request.profileImage(), request.gender(), request.birthDay());
    }

    @Override
    public MyTripPlanResponse getMyTripPlans(Long userId) {
        log.info(userId.toString());
       if (!userRepository.existsById(userId)){
            throw new GeneralException(ErrorStatus._USER_NOT_FOUND);
        }
        List<TripPlan> allByUserId = tripPlanRepository.findAllByUser_Id(userId);
        return MyTripPlanResponse.of(allByUserId);
    }

    @Override
    public void reissueToken(String refreshToken) {

    }

    @Override
    public void updateTripPlan(Long tripPlanId, UpdateTripPlanRequestDTO request) {
        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        tripPlan.updateDetails(request.title(), request.memo());
    }

    @Override
    public void deleteTripPlan(Long tripPlanId) {
        tripScheduleItemRepository.deleteAllByTripPlanId(tripPlanId);

        TripPlan tripPlan = tripPlanRepository.findById(tripPlanId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._TRIP_PLAN_NOT_FOUND));

        tripPlanRepository.delete(tripPlan);
    }
}
