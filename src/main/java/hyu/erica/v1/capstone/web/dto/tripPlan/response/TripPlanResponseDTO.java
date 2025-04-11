package hyu.erica.v1.capstone.web.dto.tripPlan.response;

import hyu.erica.v1.capstone.domain.enums.TripPlanStatus;

public record TripPlanResponseDTO(Long tripPlanId, TripPlanStatus status) {

    public static TripPlanResponseDTO of(Long tripPlanId, TripPlanStatus status) {
        return new TripPlanResponseDTO(tripPlanId, status);
    }
}
