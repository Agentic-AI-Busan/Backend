package hyu.erica.v1.capstone.service.style;

import hyu.erica.v1.capstone.web.dto.style.request.UserStyleRequestDTO;
import hyu.erica.v1.capstone.web.dto.style.response.UserStyleInitResponseDTO;
import hyu.erica.v1.capstone.web.dto.style.response.UserStyleResponseDTO;
import hyu.erica.v1.capstone.web.dto.tripPlan.response.TripPlanResponseDTO;

public interface StyleCommandService {

    UserStyleInitResponseDTO initStyle(Long userId);

    UserStyleResponseDTO updateStyle(Long userId, Long styleId, UserStyleRequestDTO request);

    TripPlanResponseDTO submitStyle(Long styleId, Long userId);
}
