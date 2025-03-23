package hyu.erica.capstone.service.style;

import hyu.erica.capstone.web.dto.style.request.UserStyleRequestDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleFinalResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleInitResponseDTO;
import hyu.erica.capstone.web.dto.style.response.UserStyleResponseDTO;
import hyu.erica.capstone.web.dto.tripPlan.response.TripPlanResponseDTO;

public interface StyleCommandService {

    UserStyleInitResponseDTO initStyle(Long userId);

    UserStyleResponseDTO updateStyle(Long userId, Long styleId, UserStyleRequestDTO request);

    TripPlanResponseDTO submitStyle(Long styleId, Long userId);
}
