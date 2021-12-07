package apap.tugas.siretail.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KuponDetail {
    private Long idKupon;
    private String kodeKupon;
    private String namaKupon;
    private Double besarDiskon;
}
