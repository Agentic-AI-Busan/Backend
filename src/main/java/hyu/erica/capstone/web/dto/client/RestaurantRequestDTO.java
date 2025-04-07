package hyu.erica.capstone.web.dto.client;

import java.util.List;

public record RestaurantRequestDTO(List<RestaurantDetailDTO> recommendations, List<Long> restaurant_ids) {

    private record RestaurantDetailDTO (String name, String description, Integer index) {

    }
}
