package benedicto.learnspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mobil")
public class MobilModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merk", nullable = false, length = 25)
    private String merk;

    @Column(name = "tipe", nullable = false, length = 25)
    private String tipe;

    @Column(name = "status")
    private String status;

    public MobilModel() {
    }

    public MobilModel(String merk, String tipe, String status){
        this.merk = merk;
        this.tipe = tipe;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
