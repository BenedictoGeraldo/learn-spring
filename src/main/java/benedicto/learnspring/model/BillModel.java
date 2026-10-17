package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50 )
    private String title;

    @Builder.Default
    @Column(name = "tax_percent", nullable = false)
    private BigDecimal taxPercent = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "service_percent", nullable = false)
    private BigDecimal servicePercent = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "total_tax", nullable = false)
    private BigDecimal totalTax = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "total_service", nullable = false)
    private BigDecimal totalService = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantModel> participants = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <BillItemModel> items = new ArrayList<>();

    public void addParticipant(ParticipantModel participant) {
        participants.add(participant);
        participant.setBill(this);
    }

    public void addItem(BillItemModel item) {
        items.add(item);
        item.setBill(this);
    }
}
