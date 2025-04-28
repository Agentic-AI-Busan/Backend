package hyu.erica.capstone.web.dto.tripPlan.response.restaurant;

import hyu.erica.capstone.domain.Restaurant;

public record RestaurantResponseDTO(Long restaurantId, String name, String imageUrl, String address,
                                    String operatingHours, String title, Double latitude, Double longitude) {

    public static RestaurantResponseDTO of(Restaurant restaurant) {
        return new RestaurantResponseDTO(restaurant.getId(), restaurant.getRestaurantName(), "추후 작업 예정",
                restaurant.getRoadAddress(), restaurant.getBusinessHours(), restaurant.getDescription(),
                restaurant.getLatitude(), restaurant.getLongitude());
    }
}
