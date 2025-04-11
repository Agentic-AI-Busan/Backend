package hyu.erica.v1.capstone.web.dto.tripPlan.request;

import hyu.erica.v1.capstone.domain.enums.PlaceType;
import java.util.List;

public record UpdateAllScheduleOrderRequest(
        Long tripPlanId,
        List<ScheduleOrderItemDTO> items
) {
    public record ScheduleOrderItemDTO(
            Long id,
            int dayNumber,
            int orderInDay,
            PlaceType placeType,
            Long attractionId,
            Long restaurantId,
            String memo
    ) {}

}


