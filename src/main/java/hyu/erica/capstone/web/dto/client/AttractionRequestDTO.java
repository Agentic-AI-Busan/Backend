package hyu.erica.capstone.web.dto.client;

import java.util.List;

public record AttractionRequestDTO (String answer, List<Long> attraction_ids) {
}
