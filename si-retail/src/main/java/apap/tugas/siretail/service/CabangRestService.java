package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.rest.AlamatDetail;
import apap.tugas.siretail.rest.CabangDetail;

import java.util.List;

public interface CabangRestService {
    CabangModel createCabang(CabangModel cabang);
    List<AlamatDetail> getListAlamat();
}
