package hyu.erica.capstone.service.user.impl;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.service.user.UserCommandService;
import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;


    @Override
    public Long signUp(SignUpRequestDTO request) {
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .birthday(request.birthDate())
                .phoneService(request.phoneService())
                .build();

        User save = userRepository.save(user);

        return save.getId();
    }

    @Override
    public void signIn(SignInRequestDTO request) {

    }

    @Override
    public void updateInfo(Long userId, UpdateInfoRequestDTO request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        user.updateInfo(request.nickname(), request.phoneNumber(), request.profileImage());
    }

    @Override
    public void reissueToken(String refreshToken) {

    }
}
