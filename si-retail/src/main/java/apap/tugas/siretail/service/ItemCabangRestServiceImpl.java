package apap.tugas.siretail.service;

import apap.tugas.siretail.additional.ResponseHandler;
import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class ItemCabangRestServiceImpl implements ItemCabangRestService{
    @Autowired
    private ItemCabangDb itemCabangDb;

    private final WebClient webClient;

    public ItemCabangRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
    }

    @Override
    public Mono<String> getAllItem(){
        return this.webClient.get().uri("/api/item")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> getItem(String uuid){
        return this.webClient.get().uri("/api/item/" + uuid)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public String createItem(String uuidItem) {
        getItem(uuidItem);
        System.out.println();
        return "";
    }
}
