package hyu.erica.capstone.web.dto.client;

import java.util.List;

public record RestaurantRequestDTO(String answer, List<Long> restaurant_ids) {
}
