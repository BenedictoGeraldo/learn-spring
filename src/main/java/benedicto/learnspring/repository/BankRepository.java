package benedicto.learnspring.repository;

import benedicto.learnspring.model.BankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankModel, Long> {
    //akan diisi otomatis repo nya dengan jparepository
}
