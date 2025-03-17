package hyu.erica.capstone.web.dto.tripPlan.response;

import java.util.List;

public record RestaurantListResponseDTO (List<RestaurantResponseDTO> restaurants, int totalElements) {

    public static RestaurantListResponseDTO of(List<RestaurantResponseDTO> restaurants) {
        return new RestaurantListResponseDTO(restaurants, restaurants.size());
    }
}
