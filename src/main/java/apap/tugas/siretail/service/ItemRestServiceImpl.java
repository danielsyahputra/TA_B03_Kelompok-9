package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.repository.CabangDb;
import apap.tugas.siretail.repository.ItemCabangDb;
import apap.tugas.siretail.rest.ItemCabangDetail;
import apap.tugas.siretail.rest.PostItemDetail;
import apap.tugas.siretail.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
@Transactional
public class ItemRestServiceImpl implements ItemRestService{
    @Autowired
    private ItemCabangDb itemCabangDb;

    @Autowired
    private CabangService cabangService;

    private final WebClient webClient;
    private final WebClient webClientBuild;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.itemUrl).build();
        this.webClientBuild =  webClientBuilder.baseUrl(Setting.factoryUrl).build();
    }

    @Override
    public Mono<String> getListItem() {
        return this.webClient.get().uri("/api/item")
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public ItemCabangModel createItem(ItemCabangDetail item) {
        ItemCabangModel newItem = new ItemCabangModel();
        CabangModel cabang = cabangService.getCabangById(item.getIdCabang());
        newItem.setCabang(cabang);
        newItem.setStok(item.getStok());
        newItem.setNama(item.getNama());
        newItem.setKategori(item.getKategori());
        newItem.setHarga(item.getHarga());
        newItem.setId(item.getId());
        return itemCabangDb.save(newItem);
    }
//
//    @Override
//    public Mono<String> updateStokItem(String id, Integer stok) {
//        return this.webClient.put().uri("/api/item/" + id + "?stok=" + stok)
//                .retrieve()
//                .bodyToMono(String.class);
//    }

    @Override
    public String postIncreaseItem(PostItemDetail item) {
        HashMap<String, Object> x =  this.webClientBuild.post().uri("/api/req-update-item/").syncBody(item).retrieve().bodyToMono(HashMap.class).block();
        int status = (Integer) x.get("status");
        if ( !(status >= 200 && status < 300) || x == null) return null;
        HashMap<String, Object> res = (HashMap<String, Object>) x.get("result");
        return (String) res.get("id_Item");
    }
}
