package benedicto.learnspring.repository;

import benedicto.learnspring.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantModel, Long>{
}
