package hyu.erica.capstone.web.dto.tripPlan.response.attraction;

public record AttractionResponseDTO(Long attractionId, String name, String imageUrl, String address,
                                    String operatingHours, String title, Double latitude, Double longitude) {

    public static AttractionResponseDTO of(Long attractionId, String name, String imageUrl, String address,
            String operatingHours, String title, Double latitude, Double longitude) {
        return new AttractionResponseDTO(attractionId, name, imageUrl, address,
                operatingHours, title, latitude, longitude);
    }
}
