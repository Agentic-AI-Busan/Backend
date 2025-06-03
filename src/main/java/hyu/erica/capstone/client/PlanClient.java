package hyu.erica.capstone.client;

import hyu.erica.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.capstone.web.dto.client.RestaurantRequestDTO;
import hyu.erica.capstone.web.dto.client.StyleRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "planApiClient", url = "${plan.api.url}")
public interface PlanClient {

    @PostMapping("/restaurant_graph_rag/search")
    RestaurantRequestDTO getRestaurants(@RequestBody StyleRequestDTO request);

    @PostMapping("/attraction_graph_rag/search")
    AttractionRequestDTO getAttractions(@RequestBody StyleRequestDTO request);
}
