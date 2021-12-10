package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.service.ItemCabangRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class ItemSiItemRestController {

    @Autowired
    private ItemCabangRestService itemCabangRestService;

    @GetMapping(value = "/getAllItem")
    private Mono<String> getAllItem(){
        return itemCabangRestService.getAllItem();
    }

    @GetMapping(value = "/saveitem/{uuidItem}/")
    private String saveItem(@PathVariable String uuidItem) {
        String item = itemCabangRestService.createItem(uuidItem);
        return item;
    }



}
