package apap.tugas.siretail.rest;

import apap.tugas.siretail.model.CabangModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class CabangDetail {
    private String nama;
    private String alamat;
    private Integer ukuran;
    private String nomorTelepon;

    public CabangModel convertToCabangModel(){
        CabangModel newCabang = new CabangModel();

        newCabang.setNama(nama);
        newCabang.setAlamat(alamat);
        newCabang.setUkuran(ukuran);
        newCabang.setNomorTelepon(nomorTelepon);
        newCabang.setStatus(0);
        newCabang.setPenanggungJawab(null);
        return newCabang;
    }
}
