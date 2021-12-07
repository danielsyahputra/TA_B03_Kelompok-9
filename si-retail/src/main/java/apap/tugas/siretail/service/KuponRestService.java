package apap.tugas.siretail.service;

import apap.tugas.siretail.rest.KuponDetail;
import reactor.core.publisher.Mono;

public interface KuponRestService {
    void terapkanPromoKeItem(Long idItem);
    Mono<String> getListKupon();
}
