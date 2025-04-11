package hyu.erica.v1.capstone.web.dto.tripPlan.response.attraction;

import hyu.erica.v1.capstone.domain.Attraction;

public record AttractionDetailResponseDTO (Long attractionId, String name, String imageUrl, String address, String phone, String description, String usageDay) {

    public static AttractionDetailResponseDTO of(Attraction attraction) {
        return new AttractionDetailResponseDTO(attraction.getContentId(),
                attraction.getContentName(), attraction.getImageUrl(), attraction.getAddress(),
                attraction.getContact(), attraction.getSubtitle(), attraction.getClosedDays());
    }
}
