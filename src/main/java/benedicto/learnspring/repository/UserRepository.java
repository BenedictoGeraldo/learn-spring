package benedicto.learnspring.repository;

import benedicto.learnspring.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    //1. Mencari user berdasarkan username (dipakai saat login)
    Optional<UserModel> findByUsername(String username);

    //2. Mencari user berdasarkan email (jika login menggunakan email)
    Optional<UserModel> findByEmail(String email);

    //3. Memeriksa apakah username/email sudah dipakai saat registrasi
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
