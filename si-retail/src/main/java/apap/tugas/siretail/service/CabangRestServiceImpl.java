package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.RoleModel;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.repository.CabangDb;
import apap.tugas.siretail.rest.AlamatDetail;
import apap.tugas.siretail.rest.CabangDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional

public class CabangRestServiceImpl implements CabangRestService{
    @Autowired
    private CabangDb cabangDb;

    private UserService userService;

    @Override
    public CabangModel createCabang(CabangDetail cabang) {
        CabangModel newCabang = cabang.convertToCabangModel();
        return cabangDb.save(newCabang);
    }

    @Override
    public List<AlamatDetail> getListAlamat() {
        List<CabangModel> listCabang = cabangDb.findAll();
        List<AlamatDetail> returnedListCabang = new ArrayList<>();
        for (CabangModel cabang : listCabang) {
            Integer id = cabang.getId();
            String nama = cabang.getNama();
            String alamat = cabang.getAlamat();
            String noTelepon = cabang.getNomorTelepon();
            AlamatDetail detail = new AlamatDetail(id, nama, alamat, noTelepon);
            returnedListCabang.add(detail);
        }

        return returnedListCabang;
    }
}
