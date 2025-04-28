package hyu.erica.capstone.repository;

import hyu.erica.capstone.domain.Style;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StyleRepository extends JpaRepository<Style, Long> {
}
