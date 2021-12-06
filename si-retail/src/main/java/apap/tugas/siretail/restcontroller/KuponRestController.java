package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.service.KuponRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class KuponRestController {
    @Autowired
    private KuponRestService kuponRestService;

    @GetMapping(value = "/test")
    private Mono<String> getListKupon() {
        return kuponRestService.getListKupon();
    }
}
