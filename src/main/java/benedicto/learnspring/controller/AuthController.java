package benedicto.learnspring.controller;


import benedicto.learnspring.dto.request.LoginRequestDto;
import benedicto.learnspring.dto.request.RefreshTokenRequestDto;
import benedicto.learnspring.dto.request.RegisterRequestDto;
import benedicto.learnspring.dto.response.AuthResponseDto;
import benedicto.learnspring.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    //Injek objek AuthServiceImpl dari spring boot ke controller melalui constructor yg dibikin di auth impl
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //Endpoint register user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDto request) {
        String result = authService.registerUser(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //endpoint login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    //endpoint refresh token untuk tukar refresh token lama dengan yang baru
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@Valid @RequestBody RefreshTokenRequestDto request) {
        AuthResponseDto response = authService.refreshtoken(request);
        return ResponseEntity.ok(response);
    }

    //endpoint untuk logout dan kill access token
    @PostMapping("/logout")
    public ResponseEntity<String> logout (@Valid @RequestBody RefreshTokenRequestDto request) {
        String response = authService.logout(request);
        return ResponseEntity.ok(response);
    }
}
