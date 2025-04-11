package hyu.erica.v1.capstone.web.dto.trip.response;

import hyu.erica.v1.capstone.domain.Attraction;

public record AttractionResponseDTO(String image, String name) {
    public static AttractionResponseDTO of(Attraction attraction) {
        return new AttractionResponseDTO(new String(), new String());
    }
}
