package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.rest.ItemCabangDetail;
import reactor.core.publisher.Mono;

public interface ItemCabangRestService {
    public ItemCabangModel createItemCabang(ItemCabangDetail itemCabang);
    public Mono<String> getAllItem();
}
