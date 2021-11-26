package apap.tugas.siretail.controller;

import apap.tugas.siretail.repository.UserDb;
import apap.tugas.siretail.model.UserModel;
import apap.tugas.siretail.model.RoleModel;
import apap.tugas.siretail.repository.RoleDb;
import apap.tugas.siretail.service.UserService;
import apap.tugas.siretail.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    UserDb userDb;

    @GetMapping("/add")
    public String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value="/add")
    public String addUserSubmit(@ModelAttribute UserModel user, Model model, RedirectAttributes redirAttrs) {
        List<UserModel> listUser = userService.getListUser();
        for (UserModel x : listUser){
            if(x.getEmail().equals(user.getEmail())){
                redirAttrs.addFlashAttribute("error", "Email sudah digunakan.");
                redirAttrs.addFlashAttribute("success", null);
                return "redirect:/user/add";
            }else {
                userService.addUser(user);
                model.addAttribute("user", user);
                redirAttrs.addFlashAttribute("success", "User berhasil ditambahkan.");
                redirAttrs.addFlashAttribute("error", null);
            }
        }
        return "redirect:/user/add";
    }

    @GetMapping(value = "/viewall")
    public String viewAllUser(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }

    @GetMapping("/changeuser/{usernameUser}")
    public String changeUser(
            @PathVariable String usernameUser,
            Model model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel userSession = userService.findUserbyUsername(auth.getName());
        UserModel userDiubah = userService.findUserbyUsername(usernameUser);

        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();

        if (userSession.getRole().getRole().equals("Kepala Retail")) {
            model.addAttribute("user", userDiubah);
            model.addAttribute("listRole", listRole);
            model.addAttribute("kepalaRetail", 1);
            return "form-change-user";
        }

        else if (userSession.getRole().getRole().equals("Manager Cabang")) {

            if (userDiubah.getRole().getRole().equals("Kepala Retail")) {
                return "redirect:/";
            }

            else {
                model.addAttribute("user", userDiubah);
                model.addAttribute("listRole", listRole);
                model.addAttribute("kepalaRetail", 0);
                return "form-change-user";
            }
        }

        else {
            return "redirect:/";
        }
    }

    @PostMapping("/changeuser")
    public String changeUserSubmitPage(
            @ModelAttribute UserModel user,
            Model model
    ){
        UserModel updatedUser = userService.changeUser(user);
        model.addAttribute("user", updatedUser);
        return "view-berhasil-change-user";
    }

}
