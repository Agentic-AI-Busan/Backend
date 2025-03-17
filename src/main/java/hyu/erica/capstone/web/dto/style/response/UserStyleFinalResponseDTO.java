package hyu.erica.capstone.web.dto.style.response;

import java.util.List;

public record UserStyleFinalResponseDTO (List<Long> restaurant_ids, List<Long> attraction_ids, Long tripPlanId) {

    public static UserStyleFinalResponseDTO of(List<Long> restaurant_ids, List<Long> attraction_ids, Long tripPlanId) {
        return new UserStyleFinalResponseDTO(restaurant_ids, attraction_ids, tripPlanId);
    }
}
