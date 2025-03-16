package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.mapping.PreferRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferRestaurantRepository extends JpaRepository<PreferRestaurant, Long> {
}
