package hyu.erica.capstone.web.dto.tripPlan.response.attraction;

import hyu.erica.capstone.domain.Attraction;

public record AttractionDetailResponseDTO (Long attractionId, String name, String imageUrl, String address,
                                           String phone, String title, String operatingHour,
                                           Double latitude, Double longitude) {

    public static AttractionDetailResponseDTO of(Attraction attraction) {
        return new AttractionDetailResponseDTO(attraction.getContentId(),
                attraction.getTravelDestination(), attraction.getImageUrl(), attraction.getAddress(),
                attraction.getContact(), attraction.getSubtitle(), attraction.getOperatingHours(),
                attraction.getLatitude(), attraction.getLongitude());
    }
}
