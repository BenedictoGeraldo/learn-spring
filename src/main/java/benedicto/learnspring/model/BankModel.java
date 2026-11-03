package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "bank")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;
}
