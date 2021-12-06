package apap.tugas.siretail.repository;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CabangDb extends JpaRepository<CabangModel, Long> {
    List<CabangModel> findAllByPenanggungJawabRole(RoleModel role);
    Optional<CabangModel> findById(Integer idCabang);
}
