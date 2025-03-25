package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.TripScheduleItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripScheduleItemRepository extends JpaRepository<TripScheduleItem, Long> {
    List<TripScheduleItem> findAllByTripPlanId(Long tripPlanId);

    List<TripScheduleItem> findAllByTripPlanIdAndDayNumber(Long tripPlanId, int dayNumber);
}
