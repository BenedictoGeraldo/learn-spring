package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Builder.Default
    @Column(name = "price", nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", nullable = false)
    private BillModel bill;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "bill_item_participants",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<ParticipantModel> participants = new ArrayList<>();
}
