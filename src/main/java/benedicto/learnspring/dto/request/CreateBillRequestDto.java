package benedicto.learnspring.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record CreateBillRequestDto (
    String title,
    BigDecimal taxPercent,
    BigDecimal servicePercent,
    List<String> participantNames,
    List<BillItemRequestDto> items
) {}