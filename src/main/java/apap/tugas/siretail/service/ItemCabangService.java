package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;

import java.util.List;

public interface ItemCabangService {
    ItemCabangModel getItemByIdItem(Long idItem);
    List<ItemCabangModel> getListItem();
}
