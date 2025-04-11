package hyu.erica.v1.capstone.service.user;

import hyu.erica.v1.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.v1.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.v1.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import hyu.erica.v1.capstone.web.dto.user.response.MyTripPlanResponse;

public interface UserCommandService {

    Long signUp(SignUpRequestDTO request);

    String signIn(SignInRequestDTO request);

    boolean checkEmail(String email);

    void updateInfo(Long userId, UpdateInfoRequestDTO request);

    MyTripPlanResponse getMyTripPlans(Long userId);

    void reissueToken(String refreshToken);
}
