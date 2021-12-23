package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;

import java.util.List;

public interface ItemCabangService {
    ItemCabangModel getItemByIdItem(String idItem);
    List<ItemCabangModel> getListItem();
    void deleteItem(ItemCabangModel item);
}
