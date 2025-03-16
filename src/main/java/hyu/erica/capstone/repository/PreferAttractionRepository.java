package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.mapping.PreferAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferAttractionRepository extends JpaRepository<PreferAttraction, Long> {
}
