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

    @PutMapping(value = "/item/{idItem}")
    private ItemCabangModel terapkanPromo(@PathVariable Long idItem, @RequestParam(name = "jumlahDiskon") Integer jumlahDiskon,
                                          @RequestParam(name = "idPromo") Integer idPromo) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        item.setIdPromo(idPromo);
        item.setHarga(item.getHarga() - jumlahDiskon);
        return itemCabangDb.save(item);
    }

    @PostMapping(value="/item/{idItem}")
    private ItemCabangModel tambahStok(@PathVariable Long idItem, @RequestParam(name="idBarang") Integer idBarang

    ){

        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        return itemCabangDb.save(item);
    }
}
