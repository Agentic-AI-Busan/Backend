package hyu.erica.capstone.web.dto.tripPlan.response.restaurant;

import hyu.erica.capstone.domain.Restaurant;

public record RestaurantDetailResponseDTO (
        Long restaurantId, String name, String imageUrl, String address, String phone, String title, String operatingHours,
        Double latitude, Double longitude) {

    public static RestaurantDetailResponseDTO of(Restaurant restaurant) {
        return new RestaurantDetailResponseDTO(restaurant.getId(),
                restaurant.getRestaurantName(), "추후 작업 예정", restaurant.getRoadAddress(),
                restaurant.getPhoneNumber(), restaurant.getDescription(), restaurant.getBusinessHours(),
                restaurant.getLatitude(), restaurant.getLongitude());
    }
}
