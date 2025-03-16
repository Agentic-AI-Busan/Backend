package hyu.erica.capstone.client;

import hyu.erica.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.capstone.web.dto.client.RestaurantRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "planApiClient", url = "${plan.api.url}")
public interface PlanClient {

    @GetMapping("/restaurants/search")
    RestaurantRequestDTO getRestaurants(@RequestParam String query);

    @GetMapping("/attractions/search")
    AttractionRequestDTO getAttractions(@RequestParam String query);
}
