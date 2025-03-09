package hyu.erica.capstone.service.style;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Style;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.StyleRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;
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
