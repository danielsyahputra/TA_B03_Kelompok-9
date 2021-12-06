package apap.tugas.siretail.repository;

import apap.tugas.siretail.model.ItemCabangModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCabangDb extends JpaRepository<ItemCabangModel, Long> {
    Optional<ItemCabangModel> findById(Long idItem);
}
