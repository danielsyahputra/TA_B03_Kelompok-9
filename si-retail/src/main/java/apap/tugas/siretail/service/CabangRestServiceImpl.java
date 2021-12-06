package apap.tugas.siretail.service;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.repository.CabangDb;
import apap.tugas.siretail.rest.CabangDetail;
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

public class CabangRestServiceImpl implements CabangRestService{
    @Autowired
    private CabangDb cabangDb;

    @Override
    public CabangModel createCabang(CabangModel cabang) {
        return cabangDb.save(cabang);
    }
}
