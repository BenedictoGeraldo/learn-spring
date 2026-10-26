package benedicto.learnspring.service.impl;

import benedicto.learnspring.model.RefreshTokenModel;
import benedicto.learnspring.model.UserModel;
import benedicto.learnspring.repository.RefreshTokenRepository;
import benedicto.learnspring.repository.UserRepository;
import benedicto.learnspring.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Value("${jwt.refresh-token-expiration-ms}")
    private Long refreshTokenExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository){
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshTokenModel> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    //1. Membuat dan menyimpan refreh token ke database
    @Transactional
    public RefreshTokenModel createRefreshToken(String username ) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User tidak ditemukan: " + username));

        RefreshTokenModel refreshToken = RefreshTokenModel.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpirationMs))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    //2. Memeriksa apakah masa berlaku refresh token sudah habis
    public RefreshTokenModel verifyExpiration(RefreshTokenModel token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new IllegalArgumentException("Refresh token telah kadalauarsa. SIlahkan login ulang");
        }
        return token;
    }

    //3. Menghapus sesi saat user logout
    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
