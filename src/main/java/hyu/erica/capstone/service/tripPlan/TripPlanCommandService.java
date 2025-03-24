package hyu.erica.capstone.service.tripPlan;

import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;

public interface TripPlanCommandService {

    Long confirmAttractionRecommendation(Long tripPlanId, SaveAttractionRequestDTO request);

    Long confirmRestaurantRecommendation(Long tripPlanId, SaveRestaurantRequestDTO request);
}
