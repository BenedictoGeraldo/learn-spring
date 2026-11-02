package benedicto.learnspring.repository;

import benedicto.learnspring.model.NasabahModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahRepository extends JpaRepository<NasabahModel, Long> {
    //diisi dengna orm jpa otomatis
}
