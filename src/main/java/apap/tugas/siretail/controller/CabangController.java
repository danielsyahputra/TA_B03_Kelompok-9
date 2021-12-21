package apap.tugas.siretail.controller;

import apap.tugas.siretail.additional.CabangDetail;
import apap.tugas.siretail.model.CabangModel;
import apap.tugas.siretail.model.ItemCabangModel;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.service.CabangService;
import apap.tugas.siretail.service.ItemCabangService;
import apap.tugas.siretail.service.ItemCabangRestService;
import apap.tugas.siretail.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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

    @Autowired
    private ItemCabangRestService itemCabangRestService;

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

        List<ItemCabangModel> listItem = itemCabangService.getListItem();
        List<ItemCabangModel> filteredListItem = new ArrayList<>();
        List<Boolean> punyaKuponApaEngga = new ArrayList<>();

        for (ItemCabangModel item : listItem) {
            if (item.getCabang() != null) {
                if (item.getCabang().equals(cabang)) {
                    filteredListItem.add(item);
                }
            }
        }
        listItem = filteredListItem;

        for (int i = 0; i < listItem.size(); i++) {
            if (listItem.get(i).getIdPromo() == null) {
                punyaKuponApaEngga.add(false);
            }else {
                punyaKuponApaEngga.add(true);
            }
        }
        model.addAttribute("cabang", cabang);
        model.addAttribute("listItem", listItem);
        model.addAttribute("punyaKupon", punyaKuponApaEngga);
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
            RedirectAttributes redirAttrs
    ) {
        cabangService.ubahCabang(cabang);
        redirAttrs.addFlashAttribute("success", String.format("Cabang dengan id %d berhasil diubah", cabang.getId()));
        redirAttrs.addFlashAttribute("error", null);
        return "redirect:/cabang/viewall";
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

    @GetMapping("/menunggukonfirmasi")
    public String viewAllMenungguKonfirmasi(Authentication auth, Model model){
        UserModel authUser = userService.findUserbyUsername(auth.getName());
        List<CabangDetail> menungguKonfirmasi = cabangService.getListCabangMenungguKonfirmasi();

        model.addAttribute("user", authUser);
        model.addAttribute("menungguKonfirmasi", menungguKonfirmasi);
        return "viewall-menunggukonfirmasi";

    }

    @GetMapping("/menyetujui/{idCabang}")
    public String menyetujuiCabang(@PathVariable Integer idCabang, Model model){
        CabangModel cabangToAccept = cabangService.getCabangById(idCabang);
        cabangService.acceptCabang(idCabang);

        model.addAttribute("namaCabang", cabangToAccept.getNama());
        return "accept-cabang-success";
    }

    @GetMapping("menolak/{idCabang}")
    public String menolakCabang(@PathVariable Integer idCabang, Model model){
        CabangModel cabangToDecline = cabangService.getCabangById(idCabang);
        cabangService.declineCabang(idCabang);

        model.addAttribute("namaCabang", cabangToDecline.getNama());
        return "decline-cabang-success";
    }

    @GetMapping("getItem/{idCabang}")
    public String getAllItem(
            @PathVariable Integer idCabang,
            Model model
    ){
        String listMonoItem = itemCabangRestService.getAllItem().share().block();

        final JSONObject obj = new JSONObject(listMonoItem);
        final JSONArray listResult = obj.getJSONArray("result");

        List<String> listUUID = new ArrayList<>();
        List<String> listNama = new ArrayList<>();
        List<Integer> listHarga = new ArrayList<>();
        List<Integer> listStok = new ArrayList<>();
        List<String> listKategori = new ArrayList<>();

        final int n = listResult.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject item = listResult.getJSONObject(i);
            listUUID.add(item.getString("uuid"));
            listNama.add(item.getString("nama"));
            listHarga.add(item.getInt("harga"));
            listStok.add(item.getInt("stok"));
            listKategori.add(item.getString("kategori"));
        }

        for (int a = 0; a < listUUID.size(); a++ ) {
            String uuid = listUUID.get(a);
            String nama = listNama.get(a);
            Integer harga = listHarga.get(a);
            Integer stok = listStok.get(a);
            String kategori = listKategori.get(a);

            itemCabangService.createItemCabangModel(uuid, nama, harga, stok, kategori);
        }

        CabangModel cabang = cabangService.getCabangById(idCabang);
        List<ItemCabangModel> listItem = itemCabangService.getListItem();

        model.addAttribute("cabang", cabang);
        model.addAttribute("listItem", listItem);

        for (String nama : listNama) {
            System.out.println(nama);
        }

        return "form-get-all-item";
    }
}
