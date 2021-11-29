package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService{
    @Autowired
    private CabangDb cabangDb;


    @Override
    public CabangModel addCabang(CabangModel cabang) {
        return cabangDb.save(cabang);
    }
}
