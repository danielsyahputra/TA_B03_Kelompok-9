package apap.tugas.siretail.rest;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.ItemCabangModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)

public class ItemCabangDetail {
    private String nama;
    private Integer harga;
    private Integer stok;
    private String kategori;
    private Integer idPromo;
    private CabangModel cabang;

    public ItemCabangModel convertToItemCabangModel(){
        ItemCabangModel newItemCabang = new ItemCabangModel();

        newItemCabang.setNama(nama);
        newItemCabang.setHarga(harga);
        newItemCabang.setStok(stok);
        newItemCabang.setKategori(kategori);
        newItemCabang.setIdPromo(idPromo);
        newItemCabang.setCabang(null);
        return newItemCabang;
    }
}
