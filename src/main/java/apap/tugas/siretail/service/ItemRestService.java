package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.rest.ItemCabangDetail;
import apap.tugas.siretail.rest.PostItemDetail;
import reactor.core.publisher.Mono;

public interface ItemRestService {
    Mono<String> getListItem();
    ItemCabangModel createItem(ItemCabangDetail item);
    String postIncreaseItem(PostItemDetail item);
//    Mono<String> updateStokItem(String id, Integer stok);
}
