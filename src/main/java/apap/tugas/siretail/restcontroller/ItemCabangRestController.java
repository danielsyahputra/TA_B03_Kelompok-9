package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.service.ItemCabangService;
import apap.tugas.siretail.service.ItemCabangRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ItemCabangRestController {
    @Autowired
    private ItemCabangService itemCabangService;

    @Autowired
    private ItemCabangRestService itemCabangRestService;

    @Autowired
    private ItemCabangDb itemCabangDb;

    @GetMapping(value = "/getAllItem")
    private Mono<String> getAllItem(){
        System.out.println(itemCabangRestService.getAllItem());
        return itemCabangRestService.getAllItem();
    }

    @PutMapping(value = "/item/{idItem}")
    private ItemCabangModel terapkanPromo(@PathVariable Long idItem, @RequestParam(name = "jumlahDiskon") Integer jumlahDiskon,
                                          @RequestParam(name = "idPromo") Integer idPromo) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        item.setIdPromo(idPromo);
        item.setHarga(item.getHarga() - jumlahDiskon);
        return itemCabangDb.save(item);
    }
}
