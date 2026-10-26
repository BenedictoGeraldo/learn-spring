package benedicto.learnspring.repository;

import benedicto.learnspring.model.RefreshTokenModel;
import benedicto.learnspring.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, Long> {

    //1. mencari token saat klien memanggil endpoint /refresh /atau logout
    Optional<RefreshTokenModel> findByToken(String token);

    //2. Menghapus token saat user logout
    @Modifying
    int deleteByToken(String token);

    //3. Menghapus seluruh token milik user tertentu
    @Modifying
    int deleteByUser(UserModel user);

    //4. Menghapus token yang sudah expired secara otomatis
    @Modifying
    int deleteByExpiryDateBefore(Instant now);
}
