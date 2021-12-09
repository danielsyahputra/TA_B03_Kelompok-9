package apap.tugas.siretail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlamatDetail {
    private Integer id;
    private String nama;
    private String alamat;
    private String no_telp;
}
