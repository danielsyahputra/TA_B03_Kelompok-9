package apap.tugas.siretail.controller;

import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabang")
public class CabangController {
    @Autowired
    private CabangService cabangService;

    @GetMapping("/add")
    public String addCabangFormPage(Model model) {
        CabangModel cabang = new CabangModel();
        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }
}
