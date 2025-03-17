package hyu.erica.capstone.web.dto.tripPlan.response;

import hyu.erica.capstone.domain.Attraction;

public record AttractionDetailResponseDTO (Long attractionId, String name, String imageUrl, String address, String phone, String description, String usageDay) {

    public static AttractionDetailResponseDTO of(Attraction attraction) {
        return new AttractionDetailResponseDTO(attraction.getUcSeq(),
                attraction.getMainTitle(), attraction.getMainImgNormal(), attraction.getAddr1(),
                attraction.getCntctTel(), attraction.getSubtitle(), attraction.getUsageDayWeekAndTime());
    }
}
