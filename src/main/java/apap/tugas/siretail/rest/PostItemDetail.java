package apap.tugas.siretail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostItemDetail {

    @JsonProperty("idItem")
    private String idItem;
    @JsonProperty("idKategori")
    private int idKategori;
    @JsonProperty("tambahanStok")
    private int tambahanStok;
    @JsonIgnoreProperties("idCabang")
    private int idCabang;
}
