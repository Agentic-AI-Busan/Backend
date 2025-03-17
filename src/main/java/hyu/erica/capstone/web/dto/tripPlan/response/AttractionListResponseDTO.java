package hyu.erica.capstone.web.dto.tripPlan.response;

import hyu.erica.capstone.domain.Attraction;
import java.util.List;

public record AttractionListResponseDTO(List<AttractionResponseDTO> attractions, int totalElements) {

    public static AttractionListResponseDTO of(List<Attraction> attractions) {
        List<AttractionResponseDTO> responseDTOS = attractions.stream()
                .map(attraction -> new AttractionResponseDTO(attraction.getUcSeq(), attraction.getMainTitle(), attraction.getMainImgNormal()))
                .toList();
        return new AttractionListResponseDTO(responseDTOS, attractions.size());
    }
}
