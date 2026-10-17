package benedicto.learnspring.dto.response;

import java.math.BigDecimal;

public record ParticipantResponseDto (
    Long id,
    String name,
    BigDecimal subtotal,
    BigDecimal taxAmount,
    BigDecimal serviceAmount,
    BigDecimal totalToPay,
    Boolean isPaid
) {}
