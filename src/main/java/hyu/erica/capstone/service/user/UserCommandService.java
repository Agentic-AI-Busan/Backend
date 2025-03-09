package hyu.erica.capstone.service.user;

import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;

public interface UserCommandService {

    Long signUp(SignUpRequestDTO request);

    String signIn(SignInRequestDTO request);

    boolean checkEmail(String email);

    void updateInfo(Long userId, UpdateInfoRequestDTO request);

    void reissueToken(String refreshToken);
}
