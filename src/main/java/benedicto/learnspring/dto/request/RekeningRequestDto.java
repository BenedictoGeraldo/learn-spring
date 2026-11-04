package benedicto.learnspring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RekeningRequestDto {

    @NotBlank(message = "No Rekening cannot be null")
    @Size(min = 12, max = 12, message = "No Rekening min and max 12 digit")
    private String noRekening;

    @NotNull(message = "Id Nasabah cannot be null")
    private Long idNasabah;

    @NotNull(message = "Id Bank cannot be null")
    private Long idBank;
}
