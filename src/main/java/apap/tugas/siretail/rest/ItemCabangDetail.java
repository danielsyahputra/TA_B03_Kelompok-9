package apap.tugas.siretail.rest;


import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.service.CabangService;
import apap.tugas.siretail.service.ItemCabangService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemCabangDetail {
    private String id;
    private String nama;
    private Double harga;
    private Integer stok;
    private String kategori;
    private Integer idCabang;
}
