package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.repository.CabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<CabangModel> getListCabang() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel authenticatedUser = userService.findUserbyUsername(auth.getName());
        String userRole = authenticatedUser.getRole().getRole();
        return null;
    }
}
