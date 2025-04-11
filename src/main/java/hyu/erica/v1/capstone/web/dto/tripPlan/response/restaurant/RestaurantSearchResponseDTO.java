package hyu.erica.v1.capstone.web.dto.tripPlan.response.restaurant;

import hyu.erica.v1.capstone.domain.Restaurant;
import java.util.ArrayList;
import java.util.List;

public record RestaurantSearchResponseDTO(List<RestaurantSearchDTO> restaurants, int totalElements) {

    private record RestaurantSearchDTO(Long restaurantId, String name, String imageUrl, String address, String usageDay) {
        private static RestaurantSearchDTO of(Restaurant restaurant) {
            return new RestaurantSearchDTO(restaurant.getId(), restaurant.getRestaurantName(), "추후 작업 예정",
                    restaurant.getRoadAddress(), restaurant.getBusinessHours());
        }
    }

    public static RestaurantSearchResponseDTO of(List<Restaurant> restaurants) {
        List<RestaurantSearchDTO> restaurantSearchDTOS = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            restaurantSearchDTOS.add(RestaurantSearchDTO.of(restaurant));
        }

        return new RestaurantSearchResponseDTO(restaurantSearchDTOS, restaurants.size());
    }
}
