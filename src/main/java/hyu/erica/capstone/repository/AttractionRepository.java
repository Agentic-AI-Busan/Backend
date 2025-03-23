package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.Attraction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByContentNameContaining(String contentName);
}


