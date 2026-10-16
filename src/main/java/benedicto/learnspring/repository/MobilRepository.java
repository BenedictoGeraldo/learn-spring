package benedicto.learnspring.repository;

import benedicto.learnspring.model.MobilModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilRepository extends JpaRepository<MobilModel, Long>{
}
