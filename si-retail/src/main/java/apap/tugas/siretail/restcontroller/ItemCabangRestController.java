package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.service.ItemCabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ItemCabangRestController {
    @Autowired
    private ItemCabangService itemCabangService;

    @Autowired
    private ItemCabangDb itemCabangDb;

    @GetMapping(value = "/item/{idItem}/{jumlahDiskon}")
    private ItemCabangModel terapkanPromo(@PathVariable Integer jumlahDiskon, @PathVariable Long idItem) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        item.setHarga(item.getHarga() - jumlahDiskon);
        return itemCabangDb.save(item);
    }
}
