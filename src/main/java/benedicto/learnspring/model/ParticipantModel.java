package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "participants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "tax_amount", nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "service_amount", nullable = false)
    private BigDecimal serviceAmount = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "total_to_pay", nullable = false)
    private BigDecimal totalToPay = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private BillModel bill;

}
