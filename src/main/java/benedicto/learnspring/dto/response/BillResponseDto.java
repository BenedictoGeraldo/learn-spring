package benedicto.learnspring.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BillResponseDto (
    Long id,
    String title,
    BigDecimal taxPercent,
    BigDecimal servicePercent,
    BigDecimal subtotal,
    BigDecimal totalTax,
    BigDecimal totalService,
    BigDecimal totalAmount,
    LocalDateTime createdAt,
    List<ParticipantResponseDto> participants,
    List<BillItemResponseDto> items
) {}
