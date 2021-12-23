package apap.tugas.siretail.service;

import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class KuponRestServiceImpl implements KuponRestService{
    private final WebClient webClient;

    public KuponRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.kuponUrl).build();
    }

    @Autowired
    ItemCabangService itemCabangService;

    @Override
    public void terapkanPromoKeItem(String idItem) {
        ItemCabangModel item = itemCabangService.getItemByIdItem(idItem);
    }

    @Override
    public Mono<String> getListKupon() {
        return this.webClient.get().uri("/rest/coupon/list")
                .retrieve()
                .bodyToMono(String.class);
    }

}
