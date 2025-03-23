package hyu.erica.capstone.service.async;

import hyu.erica.capstone.client.PlanClient;
import hyu.erica.capstone.web.dto.client.AttractionRequestDTO;
import hyu.erica.capstone.web.dto.client.RestaurantRequestDTO;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final PlanClient planClient;

    @Async
    public CompletableFuture<AttractionRequestDTO> getAttractionsAsync(String prompt) {
        return CompletableFuture.completedFuture(planClient.getAttractions(prompt));
    }

    @Async
    public CompletableFuture<RestaurantRequestDTO> getRestaurantsAsync(String prompt) {
        return CompletableFuture.completedFuture(planClient.getRestaurants(prompt));
    }
}
