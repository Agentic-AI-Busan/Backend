package hyu.erica.capstone.service.tripPlan.impl;

import hyu.erica.capstone.domain.mapping.PreferAttraction;
import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import hyu.erica.capstone.repository.PreferAttractionRepository;
import hyu.erica.capstone.repository.PreferRestaurantRepository;
import hyu.erica.capstone.repository.TripPlanRepository;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.service.tripPlan.TripPlanCommandService;
import hyu.erica.capstone.web.dto.trip.request.SaveAttractionRequestDTO;
import hyu.erica.capstone.web.dto.trip.request.SaveRestaurantRequestDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripPlanCommandServiceImpl implements TripPlanCommandService {

    private final PreferAttractionRepository preferAttractionRepository;
    private final PreferRestaurantRepository preferRestaurantRepository;
    private final TripPlanRepository tripPlanRepository;

    @Override
    public Long confirmAttractionRecommendation(Long tripPlanId, SaveAttractionRequestDTO request) {
        List<PreferAttraction> preferAttractions = preferAttractionRepository.findAllByTripPlanId(tripPlanId);

        for (PreferAttraction preferAttraction : preferAttractions) {
            if (!request.attractionIds().contains(preferAttraction.getAttraction().getContentId())) {
                preferAttraction.setPrefer(false);
            }
        }

        return tripPlanId;
    }

    @Override
    public Long confirmRestaurantRecommendation(Long tripPlanId, SaveRestaurantRequestDTO request) {
        List<PreferRestaurant> preferRestaurants = preferRestaurantRepository.findAllByTripPlanId(tripPlanId);

        for (PreferRestaurant preferRestaurant : preferRestaurants) {
            if (!request.restaurantIds().contains(preferRestaurant.getRestaurant().getId())) {
                preferRestaurant.setPrefer(false);
            }
        }

        return tripPlanId;
    }
}
