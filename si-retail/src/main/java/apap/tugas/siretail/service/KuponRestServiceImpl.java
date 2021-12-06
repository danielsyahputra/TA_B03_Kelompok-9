package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class KuponRestServiceImpl implements KuponRestService{
    @Autowired
    ItemCabangService itemCabangService;

    @Autowired
    KuponRestService kuponRestService;

    @Override
    public void terapkanPromoKeItem(Long idItem) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
    }
}
