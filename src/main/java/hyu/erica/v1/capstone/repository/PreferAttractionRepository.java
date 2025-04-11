package hyu.erica.v1.capstone.repository;

import hyu.erica.v1.capstone.domain.mapping.PreferAttraction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferAttractionRepository extends JpaRepository<PreferAttraction, Long> {

    List<PreferAttraction> findAllByTripPlanId(Long tripPlanId);
    List<PreferAttraction> findByTripPlanIdAndIsPreferTrue(Long tripPlanId);
    boolean existsByAttraction_ContentIdAndUserId(Long attractionId, Long userId);
}
