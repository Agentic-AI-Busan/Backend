package hyu.erica.capstone.web.dto.tripPlan.response.attraction;

import hyu.erica.capstone.domain.Attraction;
import java.util.List;

public record AttractionListResponseDTO(List<AttractionResponseDTO> attractions, int totalElements) {

    public static AttractionListResponseDTO of(List<Attraction> attractions) {
        List<AttractionResponseDTO> responseDTOS = attractions.stream()
                .map(attraction -> new AttractionResponseDTO(attraction.getContentId(), attraction.getContentName(),
                        attraction.getImageUrl(), attraction.getAddress(), attraction.getOperatingHours(),
                        attraction.getTitle(), attraction.getLatitude(), attraction.getLongitude()))
                .toList();
        return new AttractionListResponseDTO(responseDTOS, attractions.size());
    }
}
