package hyu.erica.capstone.service.tripPlan;

import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateAllScheduleOrderRequest;
import hyu.erica.capstone.web.dto.tripPlan.request.UpdateMemoRequest;

public interface TripPlanCommandService {

    Long confirmAttractionRecommendation(Long tripPlanId, Long userId, SaveAttractionRequestDTO request);

    Long confirmRestaurantRecommendation(Long tripPlanId, Long userId, SaveRestaurantRequestDTO request);

    void updatePlan(UpdateAllScheduleOrderRequest request);

    void editAttractionRecommendation(Long tripPlansId, SaveAttractionRequestDTO request);

    void editRestaurantRecommendation(Long tripPlansId, SaveRestaurantRequestDTO request);

    void updateMemo(Long tripPlanId, Long itemId, UpdateMemoRequest memo);

}
