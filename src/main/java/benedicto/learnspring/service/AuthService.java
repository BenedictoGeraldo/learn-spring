package benedicto.learnspring.service;

import benedicto.learnspring.dto.request.LoginRequestDto;
import benedicto.learnspring.dto.request.RefreshTokenRequestDto;
import benedicto.learnspring.dto.request.RegisterRequestDto;
import benedicto.learnspring.dto.response.AuthResponseDto;

public interface AuthService {
    String registerUser(RegisterRequestDto request);

    AuthResponseDto login(LoginRequestDto request);

    AuthResponseDto refreshtoken(RefreshTokenRequestDto request);

    String logout(RefreshTokenRequestDto request);
}
