package hyu.erica.capstone.service.user.impl;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.security.JwtTokenProvider;
import hyu.erica.capstone.service.user.UserCommandService;
import hyu.erica.capstone.web.dto.user.request.SignInRequestDTO;
import hyu.erica.capstone.web.dto.user.request.SignUpRequestDTO;
import hyu.erica.capstone.web.dto.user.request.UpdateInfoRequestDTO;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
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

        user.updateInfo(request.nickname(), request.phoneNumber(), request.profileImage());
    }

    @Override
    public void reissueToken(String refreshToken) {

    }
}
