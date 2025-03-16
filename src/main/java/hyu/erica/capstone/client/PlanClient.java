package hyu.erica.capstone.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "planApiClient", url = "${plan.api.url}")
public interface PlanClient {

    @GetMapping("/restaurants/search")
    String getRestaurants(@RequestParam String query);

    @GetMapping("/attractions/search")
    String getAttractions(@RequestParam String query);
}
