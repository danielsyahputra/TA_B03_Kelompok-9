package apap.tugas.siretail.service;

import apap.tugas.siretail.additional.CabangDetail;
import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CabangService {
    CabangModel addCabang(CabangModel cabang);
    List<CabangDetail> getListCabang();
    CabangModel getCabangById(Integer idCabang);
    CabangModel ubahCabang(CabangModel cabang);
}
