package hyu.erica.v1.capstone.repository;

import hyu.erica.v1.capstone.domain.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 키워드 검색
    List<Restaurant> findByRestaurantNameContaining(String keyword);
}
