package hyu.erica.capstone.web.dto.trip.response;

import hyu.erica.capstone.domain.Attraction;
import java.util.List;

public record AttractionListResponseDTO(List<AttractionResponseDTO> attractions, Integer totalAttractions) {
    public static AttractionListResponseDTO of(List<Attraction> attractions) {
        return new AttractionListResponseDTO(
            attractions.stream().map(AttractionResponseDTO::of).toList(),
            attractions.size()
        );
    }


}
