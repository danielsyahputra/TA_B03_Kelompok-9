package apap.tugas.siretail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cabang")
public class CabangModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 100)
    @Column(name = "alamat", nullable = false)
    private String alamat;

    @NotNull
    @Size(max = 20)
    @Column(name = "nomor_telepon", nullable = false)
    private String nomorTelepon;

    @NotNull
    @Column(name = "ukuran", nullable = false)
    private Integer ukuran;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penanggung_jawab", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel penanggungJawab;
}
