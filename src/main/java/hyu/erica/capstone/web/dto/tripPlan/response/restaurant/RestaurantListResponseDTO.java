package hyu.erica.capstone.web.dto.tripPlan.response.restaurant;

import hyu.erica.capstone.domain.Restaurant;
import java.util.ArrayList;
import java.util.List;

public record RestaurantListResponseDTO (List<RestaurantResponseDTO> restaurants, int totalElements) {

    public static RestaurantListResponseDTO of(List<Restaurant> restaurants) {
        List<RestaurantResponseDTO> responseDTOS = new ArrayList<>();

        for (Restaurant restaurant : restaurants)
            responseDTOS.add(RestaurantResponseDTO.of(restaurant));

        return new RestaurantListResponseDTO(responseDTOS, restaurants.size());
    }
}
