package hyu.erica.capstone.web.dto.tripPlan.response;

import java.util.List;

public record AttractionListResponseDTO(List<AttractionResponseDTO> attractions, int totalElements) {

    public static AttractionListResponseDTO of(List<AttractionResponseDTO> attractions) {
        return new AttractionListResponseDTO(attractions, attractions.size());
    }
}
