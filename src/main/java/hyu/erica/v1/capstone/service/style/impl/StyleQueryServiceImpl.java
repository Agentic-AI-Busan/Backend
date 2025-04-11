package hyu.erica.v1.capstone.service.style.impl;

import hyu.erica.v1.capstone.api.code.status.ErrorStatus;
import hyu.erica.v1.capstone.api.exception.GeneralException;
import hyu.erica.v1.capstone.domain.Style;
import hyu.erica.v1.capstone.domain.User;
import hyu.erica.v1.capstone.repository.StyleRepository;
import hyu.erica.v1.capstone.repository.UserRepository;
import hyu.erica.v1.capstone.service.style.StyleQueryService;
import hyu.erica.v1.capstone.web.dto.style.response.UserStyleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StyleQueryServiceImpl implements StyleQueryService {

    private final StyleRepository styleRepository;
    private final UserRepository userRepository;

    @Override
    public UserStyleResponseDTO getStyle(Long styleId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));

        Style style = styleRepository.findById(styleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._STYLE_NOT_FOUND));

        if (!style.getUser().getId().equals(user.getId()))
            throw new GeneralException(ErrorStatus._UNAUTHORIZED_USER);

        return UserStyleResponseDTO.of(style);
    }
}
