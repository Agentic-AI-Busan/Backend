package hyu.erica.v1.capstone.service.style;

import hyu.erica.v1.capstone.web.dto.style.response.UserStyleResponseDTO;

public interface StyleQueryService {

    UserStyleResponseDTO getStyle(Long styleId, Long userId);
}
