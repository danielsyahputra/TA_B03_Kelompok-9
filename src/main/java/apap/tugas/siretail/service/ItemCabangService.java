package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;

import java.util.List;

public interface ItemCabangService {
    public ItemCabangModel createItemCabangModel(String uuid, String nama, Integer harga, Integer stok, String kategori);
    ItemCabangModel getItemByIdItem(Long idItem);
    List<ItemCabangModel> getListItem();
}
