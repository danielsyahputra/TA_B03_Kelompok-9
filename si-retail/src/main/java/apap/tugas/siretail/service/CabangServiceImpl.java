package apap.tugas.siretail.service;

import apap.tugas.siretail.additional.CabangDetail;
import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.RoleModel;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CabangServiceImpl implements CabangService{
    @Autowired
    private CabangDb cabangDb;

    @Autowired
    private UserService userService;


    @Override
    public CabangModel addCabang(CabangModel cabang) {
        return cabangDb.save(cabang);
    }

    @Override
    public List<CabangDetail> getListCabang() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel authenticatedUser = userService.findUserbyUsername(auth.getName());
        RoleModel userRole = authenticatedUser.getRole();
        List<CabangModel> listCabang = new ArrayList<>();
        if (userRole.getRole().equals("Manager Cabang")) {
            listCabang = cabangDb.findAllByPenanggungJawabRole(userRole);
        } else {
            listCabang = cabangDb.findAll();
        }
        List<CabangDetail> returnedListCabang = new ArrayList<>();
        for (CabangModel cabang : listCabang) {
            Integer id = cabang.getId();
            String nama = cabang.getNama();
            String noTelepon = cabang.getNomorTelepon();
            Integer ukuran = cabang.getUkuran();
            Integer jumlahItem = cabang.getListItem().size();
            Integer status = cabang.getStatus();
            CabangDetail detail = new CabangDetail(id, nama, noTelepon, ukuran, jumlahItem, status);
            returnedListCabang.add(detail);
        }
        return returnedListCabang;
    }

    @Override
    public CabangModel getCabangById(Integer idCabang) {
        Optional<CabangModel> cabang = cabangDb.findById(idCabang);
        if (cabang.isPresent()) return cabang.get();
        return null;
    }

    @Override
    public CabangModel ubahCabang(CabangModel cabang) {
        return cabangDb.save(cabang);
    }
}
