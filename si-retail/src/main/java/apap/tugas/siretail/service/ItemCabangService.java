package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemCabangService {
    ItemCabangModel createItem(ItemCabangModel item);
    ItemCabangModel getItemByIdItem(Long idItem);
    List<ItemCabangModel> getListItem();
}
