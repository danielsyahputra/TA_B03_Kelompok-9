package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.rest.ItemCabangDetail;

import apap.tugas.siretail.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemCabangRestServiceImpl implements ItemCabangRestService {
    @Autowired
    private ItemCabangDb itemCabangDb;


    private final WebClient webClient;

    public ItemCabangRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
    }

    @Override
    public ItemCabangModel createItemCabang(ItemCabangDetail itemCabang) {
        ItemCabangModel newItemCabang = itemCabang.convertToItemCabangModel();
        return itemCabangDb.save(newItemCabang);
    }

    @Override
    public Mono<String> getAllItem(){
        return this.webClient.get().uri("/api/item")
                .retrieve()
                .bodyToMono(String.class);
    }
}
