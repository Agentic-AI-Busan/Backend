package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.TripPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPlanRepository extends JpaRepository<TripPlan, Long> {

    List<TripPlan> findAllByUser_Id(Long userId);
}
