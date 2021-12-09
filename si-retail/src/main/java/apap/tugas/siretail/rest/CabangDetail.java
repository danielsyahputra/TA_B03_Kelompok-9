package apap.tugas.siretail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class CabangDetail {
    private Integer id;
    private String nama;
    private String alamat;
    private String ukuran;
    private Integer status;
    private String no_telp;
}
