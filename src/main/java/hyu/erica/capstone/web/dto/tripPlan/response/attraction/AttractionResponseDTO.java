package hyu.erica.capstone.web.dto.tripPlan.response.attraction;

public record AttractionResponseDTO(Long attractionId, String name, String imageUrl) {

    public static AttractionResponseDTO of(Long attractionId, String name, String imageUrl) {
        return new AttractionResponseDTO(attractionId, name, imageUrl);
    }
}
