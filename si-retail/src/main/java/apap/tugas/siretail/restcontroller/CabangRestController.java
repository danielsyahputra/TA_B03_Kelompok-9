package apap.tugas.siretail.restcontroller;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.repository.CabangDb;
import apap.tugas.siretail.rest.AlamatDetail;
import apap.tugas.siretail.rest.CabangDetail;
import apap.tugas.siretail.service.CabangRestService;
import apap.tugas.siretail.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")

public class CabangRestController {
    @Autowired
    private CabangService cabangService;

    @Autowired
    private CabangRestService cabangRestService;

    @PostMapping(value="/cabang")
    private CabangModel createCabang(@Valid @RequestBody CabangModel cabang, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request Body has invalid or missing field"
            );
        } else {
            return cabangService.addCabang(cabang);
        }
    }

    @GetMapping(value="/cabang/list-alamat")
    private List<AlamatDetail> getListAlamat(){
        return cabangRestService.getListAlamat();
    }
}
