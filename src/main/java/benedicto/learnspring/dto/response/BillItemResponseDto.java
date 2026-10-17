package benedicto.learnspring.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record BillItemResponseDto (
    Long id,
    String name,
    BigDecimal price,
    Integer quantity,
    List<String> participantNames
) {}
