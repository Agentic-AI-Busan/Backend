package hyu.erica.v1.capstone.client;

import hyu.erica.v1.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.v1.capstone.web.dto.client.RestaurantRequestDTO;
import hyu.erica.v1.capstone.web.dto.client.StyleRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "planApiClient", url = "${plan.api.url}")
public interface PlanClient {

    @PostMapping("/restaurants/search")
    RestaurantRequestDTO getRestaurants(@RequestBody StyleRequestDTO request);

    @PostMapping("/attraction/search")
    AttractionRequestDTO getAttractions(@RequestBody StyleRequestDTO request);
}
