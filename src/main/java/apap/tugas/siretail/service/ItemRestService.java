package apap.tugas.siretail.service;

import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<String> getListItem();
}
