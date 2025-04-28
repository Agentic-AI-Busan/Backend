package hyu.erica.capstone.service.style;

import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;

public interface StyleQueryService {

    UserStyleResponseDTO getStyle(Long styleId, Long userId);
}
