package benedicto.learnspring.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nasabah")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NasabahModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 25)
    private String nasabahName;

}
