package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.rest.BaseResponse;
import apap.tugas.siretail.rest.ItemCabangDetail;
import apap.tugas.siretail.service.ItemCabangService;
import apap.tugas.siretail.service.ItemRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1")
public class ItemCabangRestController {
    @Autowired
    private ItemCabangService itemCabangService;

    @Autowired
    private ItemCabangDb itemCabangDb;

    @Autowired
    private ItemRestService itemRestService;

    @PutMapping(value = "/item/{idItem}")
    private ItemCabangModel terapkanPromo(@PathVariable Long idItem, @RequestParam(name = "jumlahDiskon") Integer jumlahDiskon,
                                          @RequestParam(name = "idPromo") Integer idPromo) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        item.setIdPromo(idPromo);
        item.setHarga(item.getHarga() - jumlahDiskon);
        return itemCabangDb.save(item);
    }

    @GetMapping("/item")
    private Mono<String> getListItems() {
        return itemRestService.getListItem();
    }

    @PostMapping(value = "/item")
    private BaseResponse<ItemCabangModel> addItem(
            @RequestBody ItemCabangDetail item) {
        BaseResponse<ItemCabangModel> response = new BaseResponse<>();
        System.out.println(item.getNama());
        try {
            ItemCabangModel newItem = itemRestService.createItem(item);
            System.out.println(newItem.getNama());
            response.setStatus(201);
            response.setMessage("created");
            response.setResult(newItem);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.toString());
            response.setResult(null);
        }
        return response;
    }
}
