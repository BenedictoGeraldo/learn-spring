package benedicto.learnspring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RekeningResponseDto {

    private Long id;

    private String noRekening;

    private Long idNasabah;

    private Long idBank;
}
