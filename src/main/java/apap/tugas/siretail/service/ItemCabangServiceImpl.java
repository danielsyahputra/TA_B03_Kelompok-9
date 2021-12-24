package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemCabangServiceImpl implements ItemCabangService{
    @Autowired
    private ItemCabangDb itemCabangDb;

    @Override
    public ItemCabangModel createTempItemCabangModel(String uuid, String nama, Integer harga, Integer stok, String kategori) {
        ItemCabangModel suatuItem = new ItemCabangModel();

        suatuItem.setUuid(uuid);
        suatuItem.setNama(nama);
        suatuItem.setHarga(harga);
        suatuItem.setStok(stok);
        suatuItem.setKategori(kategori);
        suatuItem.setCabang(null);
        suatuItem.setIdPromo(0);
        return suatuItem;
    }

    @Override
    public ItemCabangModel addItem(ItemCabangModel item) {
        itemCabangDb.save(item);
        return item;
    }

    @Override
    public ItemCabangModel getItemByIdItem(Long idItem) {
        Optional<ItemCabangModel> item = itemCabangDb.findById(idItem);
        if (item.isPresent()) return item.get();
        return null;
    }

    @Override
    public List<ItemCabangModel> getListItem() {
        return itemCabangDb.findAll();
    }
}
