package hyu.erica.capstone.service.user;

import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateTripPlanRequestDTO;
import hyu.erica.capstone.web.dto.user.response.MyTripPlanResponse;

public interface UserCommandService {

    Long signUp(SignUpRequestDTO request);

    String signIn(SignInRequestDTO request);

    boolean checkEmail(String email);

    void updateInfo(Long userId, UpdateInfoRequestDTO request);

    MyTripPlanResponse getMyTripPlans(Long userId);

    void reissueToken(String refreshToken);

    void updateTripPlan(Long tripPlanId, UpdateTripPlanRequestDTO request);
}
