package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rekening")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RekeningModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no_rekening", nullable = false, length = 12)
    private String noRekening;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nasabah", nullable = false)
    private NasabahModel nasabah;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bank", nullable = false)
    private BankModel bank;
}
