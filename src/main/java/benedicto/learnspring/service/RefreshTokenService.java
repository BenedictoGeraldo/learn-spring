package benedicto.learnspring.service;

import benedicto.learnspring.model.RefreshTokenModel;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshTokenModel> findByToken(String token);

    RefreshTokenModel createRefreshToken(String username );

    RefreshTokenModel verifyExpiration(RefreshTokenModel token);

    void deleteByToken(String token);
}
