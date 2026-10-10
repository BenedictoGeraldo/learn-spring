package benedicto.learnspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilRepository extends JpaRepository<Mobil, Long> {

}
