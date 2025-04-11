package hyu.erica.v1.capstone.service.tripPlan;

import hyu.erica.v1.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.v1.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import hyu.erica.v1.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest;

public interface TripPlanCommandService {

    Long confirmAttractionRecommendation(Long tripPlanId, SaveAttractionRequestDTO request);

    Long confirmRestaurantRecommendation(Long tripPlanId, SaveRestaurantRequestDTO request);

    void updatePlan(UpdateAllScheduleOrderRequest request);
}
