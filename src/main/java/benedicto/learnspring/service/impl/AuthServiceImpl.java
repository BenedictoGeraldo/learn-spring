package benedicto.learnspring.service.impl;

import benedicto.learnspring.dto.request.LoginRequestDto;
import benedicto.learnspring.dto.request.RefreshTokenRequestDto;
import benedicto.learnspring.dto.request.RegisterRequestDto;
import benedicto.learnspring.dto.response.AuthResponseDto;
import benedicto.learnspring.model.RefreshTokenModel;
import benedicto.learnspring.model.UserModel;
import benedicto.learnspring.model.enums.Role;
import benedicto.learnspring.repository.UserRepository;
import benedicto.learnspring.service.AuthService;
import benedicto.learnspring.service.JwtService;
import benedicto.learnspring.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.access-token-expiration-ms}")
    private Long accessTokenExpirationMs;

    public AuthServiceImpl (
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            RefreshTokenService refreshTokenService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public String registerUser(RegisterRequestDto request) {
        //1. validasi apakah username sudah terpakai
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username sudah digunakan: " + request.getUsername());
        }

        //2. Validasi apakah email sudah terpakai
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email sudah digunakan: " + request.getEmail());
        }

        UserModel user = UserModel.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        return "Registrasi berhasil untuk pengguna: " + user.getUsername();
    }

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        //1. Verifikasi kecocokan antara data yg dimasukan user dengan yang ada pada db kita
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        //2. Ambil data UserModel dari database
        UserModel user = userRepository.findByUsername(request.getUsernameOrEmail())
                .or(() -> userRepository.findByEmail(request.getUsernameOrEmail()))
                .orElseThrow(() -> new IllegalArgumentException("Pengguna tidak ditemukan: " + request.getUsernameOrEmail()));

        //3. Buat access token (JWT 15 menit)
        String accessToken = jwtService.generateToken(user);

        // 4. Buat Refresh Token (UUID 7 Hari di PostgreSQL)
        RefreshTokenModel refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        // 5. Kembalikan AuthResponseDto ke klien
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .expiresIn(accessTokenExpirationMs / 1000)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    @Override
    @Transactional
    public AuthResponseDto refreshtoken(RefreshTokenRequestDto request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenModel::getUser)
                .map(user -> {
                    //Hapus refresh token lama agar tidak bisa dipakai ulang
                    refreshTokenService.deleteByToken(request.getRefreshToken());

                    //Generate access token baru (15 menit)
                    String accessToken = jwtService.generateToken(user);

                    //Terbitkan refresh token baru (7 hari)
                    RefreshTokenModel newRefreshToken = refreshTokenService.createRefreshToken(user.getUsername());

                    return AuthResponseDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(newRefreshToken.getToken())
                            .tokenType("Bearer")
                            .expiresIn(accessTokenExpirationMs / 1000)
                            .username(user.getUsername())
                            .role(user.getRole().name())
                            .build();
                })
                .orElseThrow(() -> new IllegalArgumentException("Refresh token tidak valid atau tidak ditemukan"));
    }

    @Override
    @Transactional
    public String logout(RefreshTokenRequestDto request) {
        refreshTokenService.deleteByToken(request.getRefreshToken());
        return "Logout berhasil, sesi telah dihapus";

    }
}
