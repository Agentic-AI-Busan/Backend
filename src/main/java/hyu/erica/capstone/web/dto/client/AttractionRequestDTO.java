package hyu.erica.capstone.web.dto.client;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.List;

public record AttractionRequestDTO (List<AttractionDetailDTO> recommendations, List<Long> attraction_ids) {

    private record AttractionDetailDTO (String name, String description, Integer index) {

    }
}
