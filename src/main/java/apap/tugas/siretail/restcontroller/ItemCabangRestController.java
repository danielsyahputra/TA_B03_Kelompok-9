package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.rest.BaseResponse;
import apap.tugas.siretail.rest.ItemCabangDetail;
import apap.tugas.siretail.rest.PostItemDetail;
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
    private ItemCabangModel terapkanPromo(@PathVariable String idItem, @RequestParam(name = "jumlahDiskon") Double jumlahDiskon,
                                          @RequestParam(name = "idPromo") Integer idPromo) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
        item.setIdPromo(idPromo);
        item.setHarga(item.getHarga() * ((100 - jumlahDiskon) * 0.01));
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

    @DeleteMapping(value = "/item/delete/{id}")
    private BaseResponse<ItemCabangModel> deleteItem(@PathVariable String id) {
        BaseResponse<ItemCabangModel> response = new BaseResponse<>();
        try {
            ItemCabangModel item = itemCabangService.getItemByIdItem(id);
            itemCabangService.deleteItem(item);
            response.setStatus(201);
            response.setMessage("deleted");
            response.setResult(item);
        } catch (Exception e) {
            response.setStatus(400);
            response.setMessage(e.toString());
            response.setResult(null);
        }
        return response;
    }


//
//    @PutMapping(value = "/item/{id}/{stok}")
//    private Mono<String> updateStokItem(
//            @PathVariable String id,
//            @PathVariable Integer stok) {
//        return itemRestService.updateStokItem(id, stok);
//    }

//    @PostMapping(value="/item/{idItem}")
//    private ItemCabangModel tambahStok(@PathVariable Long idItem, @RequestParam(name="idBarang") Integer idBarang
//
//    ){
//
//        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
//        return itemCabangDb.save(item);
//    }

}
