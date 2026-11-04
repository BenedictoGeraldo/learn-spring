package benedicto.learnspring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NasabahRequestDto {
    @NotBlank (message = "Nasabah name cannot be null")
    private String nasabahName;
}
