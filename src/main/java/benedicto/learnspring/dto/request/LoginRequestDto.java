package benedicto.learnspring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Username atau Email tidak boleh kosong")
    private String usernameOrEmail;

    @NotBlank(message = "Password tidak boleh kosong")
    private String password;
}
