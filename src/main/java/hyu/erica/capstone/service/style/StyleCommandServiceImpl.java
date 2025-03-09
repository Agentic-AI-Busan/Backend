package hyu.erica.capstone.service.style;

import hyu.erica.capstone.api.code.status.ErrorStatus;
import hyu.erica.capstone.api.exception.GeneralException;
import hyu.erica.capstone.domain.Style;
import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.domain.enums.City;
import hyu.erica.capstone.repository.StyleRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.web.dto.style.request.UserStyleRequestDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleInitResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;
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
}
