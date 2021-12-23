package apap.tugas.siretail.service;

import apap.tugas.siretail.rest.KuponDetail;
import reactor.core.publisher.Mono;

public interface KuponRestService {
    void terapkanPromoKeItem(String idItem);
    Mono<String> getListKupon();
}
