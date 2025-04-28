package hyu.erica.capstone.web.dto.tripPlan.response.attraction;

public record AttractionResponseDTO(Long attractionId, String name, String imageUrl, String address) {

    public static AttractionResponseDTO of(Long attractionId, String name, String imageUrl, String address) {
        return new AttractionResponseDTO(attractionId, name, imageUrl, address);
    }
}
