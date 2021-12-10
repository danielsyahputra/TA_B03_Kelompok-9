package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.service.CabangService;
import apap.tugas.siretail.service.ItemCabangService;
import apap.tugas.siretail.service.KuponRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class KuponRestController {
    @Autowired
    private KuponRestService kuponRestService;

    @GetMapping(value = "/kupon")
    private Mono<String> getListKupon() {
        return kuponRestService.getListKupon();
    }
}
