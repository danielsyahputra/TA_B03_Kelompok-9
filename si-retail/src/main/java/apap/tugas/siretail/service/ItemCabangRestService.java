package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import reactor.core.publisher.Mono;

public interface ItemCabangRestService {
    String createItem(String uuidItem);
    Mono<String> getItem(String uuid);
    Mono<String> getAllItem();
}
