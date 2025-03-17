package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferRestaurantRepository extends JpaRepository<PreferRestaurant, Long> {
    List<PreferRestaurant> findAllByTripPlanId(Long tripPlanId);
}
