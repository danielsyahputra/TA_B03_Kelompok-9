package apap.tugas.siretail.controller;

import apap.tugas.siretail.additional.CabangDetail;
import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.service.CabangService;
import apap.tugas.siretail.service.ItemCabangService;
import apap.tugas.siretail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cabang")
public class CabangController {
    @Autowired
    private CabangService cabangService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCabangService itemCabangService;

    @GetMapping("/add")
    public String addCabangFormPage(Model model) {
        CabangModel cabang = new CabangModel();
        model.addAttribute("cabang", cabang);
        return "form-add-cabang";
    }

    @PostMapping("/add")
    public String addCabangSubmitPage(@ModelAttribute CabangModel cabang, Model model, Authentication auth) {
        UserModel authenticatedUser = userService.findUserbyUsername(auth.getName());
        cabang.setPenanggungJawab(authenticatedUser);
        cabangService.addCabang(cabang);
        model.addAttribute("namaCabang", cabang.getNama());
        return "add-cabang-success";
    }

    @GetMapping("/viewall")
    public String viewAllCabang(Authentication auth, Model model) {
        UserModel authUser = userService.findUserbyUsername(auth.getName());
        List<CabangDetail> listCabang = cabangService.getListCabang();
        model.addAttribute("user", authUser);
        model.addAttribute("listCabang", listCabang);
        return "viewall-cabang";
    }

    @GetMapping("/{idCabang}")
    public String detailCabang(@PathVariable Integer idCabang, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        UserModel userModel = userService.findUserbyUsername(username);
        model.addAttribute("user", userModel);
        CabangModel cabang = cabangService.getCabangById(idCabang);
        model.addAttribute("cabang", cabang);
        model.addAttribute("listItem", itemCabangService.getListItem());
        return "detail-cabang";
    }

    @GetMapping("ubah/{idCabang}")
    public String updateAgensiFormPage(
            @PathVariable Integer idCabang,
            Model model
    ) {

        CabangModel cabang = cabangService.getCabangById(idCabang);
        model.addAttribute("cabang", cabang);
        return "form-ubah-cabang";
    }

    @PostMapping("/ubah")
    public String ubahCabangSubmitPage(
            @ModelAttribute CabangModel cabang,
            Model model
    ) {

        cabangService.ubahCabang(cabang);
        model.addAttribute("namaCabang", cabang.getNama());
        return "ubah-cabang-success";
    }

    @GetMapping("/delete/{idCabang}")
    public String deleteCabang(@PathVariable Integer idCabang, Model model) {
        CabangModel cabangToDelete = cabangService.getCabangById(idCabang);

        if (cabangToDelete.getStatus() == 0 || cabangToDelete.getListItem().size() == 0) {
            if (cabangToDelete.getStatus() == 0 || cabangToDelete.getStatus() == 1) {
                cabangService.deleteCabangById(idCabang);
                model.addAttribute("idCabang", idCabang);
                return "delete-cabang-success";
            } else {
                return "delete-cabang-not-allowed";
            }
        }
        return "";
    }
}
