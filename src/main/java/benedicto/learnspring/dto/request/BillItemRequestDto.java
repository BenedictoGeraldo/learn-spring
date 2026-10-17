package benedicto.learnspring.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record BillItemRequestDto (
    String name,
    BigDecimal price,
    Integer quantity,
    List<String> participantsNames
) {}
