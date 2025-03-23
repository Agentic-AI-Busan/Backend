package hyu.erica.capstone.web.dto.tripPlan.response;

import hyu.erica.capstone.domain.Attraction;
import java.util.ArrayList;
import java.util.List;

public record AttractionSearchResponseDTO(List<AttractionSearchDTO> attractions, int totalElements) {


    private record AttractionSearchDTO (Long attractionId, String title, String imageUrl, String address, String usageDay) {
        private static AttractionSearchDTO of(Attraction attraction) {
            return new AttractionSearchDTO(attraction.getContentId(), attraction.getTitle(), attraction.getImageUrl(),
                    attraction.getAddress(), attraction.getClosedDays());
        }
    }
    public static AttractionSearchResponseDTO of(List<Attraction> attractions) {
        List<AttractionSearchDTO> attractionSearchDTOS = new ArrayList<>();

        for (Attraction attraction : attractions) {
            attractionSearchDTOS.add(AttractionSearchDTO.of(attraction));
        }

        return new AttractionSearchResponseDTO(attractionSearchDTOS, attractions.size());
    }
}
