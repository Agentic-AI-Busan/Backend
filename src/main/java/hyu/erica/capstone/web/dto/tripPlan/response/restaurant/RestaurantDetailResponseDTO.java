package hyu.erica.capstone.web.dto.tripPlan.response.restaurant;

import static hyu.erica.capstone.utils.CategoryImageMapper.*;

import hyu.erica.capstone.domain.Restaurant;
import hyu.erica.capstone.utils.CategoryImageMapper;

public record RestaurantDetailResponseDTO (
        Long restaurantId, String name, String imageUrl, String address, String phone, String title, String operatingHours,
        Double latitude, Double longitude) {

    public static RestaurantDetailResponseDTO of(Restaurant restaurant) {
        return new RestaurantDetailResponseDTO(restaurant.getId(),
                restaurant.getRestaurantName(), getImageUrl(restaurant.getBusinessStatus()), restaurant.getRoadAddress(),
                restaurant.getPhoneNumber(), restaurant.getDescription(), restaurant.getBusinessHours(),
                restaurant.getLatitude(), restaurant.getLongitude());
    }
}
