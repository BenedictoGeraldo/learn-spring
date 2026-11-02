package benedicto.learnspring.repository;

import benedicto.learnspring.model.RekeningModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RekeningRepository extends JpaRepository<RekeningModel, Long> {
    //akan diisi otomatis oleh jpa repository

    //cek nomor rekening yang dimasukan apakah sudah ada di db
    boolean existsByNoRekening(String noRekening);

    //cari 1 rekening detail
    Optional<RekeningModel> findByNoRekening(String     noRekening);

    //Cari daftar semua rekening di bank tertentu
    List<RekeningModel> findByBankId(Long bankId);
}
