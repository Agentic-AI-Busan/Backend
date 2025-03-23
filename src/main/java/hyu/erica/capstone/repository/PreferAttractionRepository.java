package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.mapping.PreferAttraction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferAttractionRepository extends JpaRepository<PreferAttraction, Long> {

    List<PreferAttraction> findAllByTripPlanId(Long tripPlanId);
    boolean existsByAttraction_ContentIdAndUserId(Long attractionId, Long userId);
}
