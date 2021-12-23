package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.rest.ItemCabangDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<String> getListItem();
    ItemCabangModel createItem(ItemCabangDetail item);
}
