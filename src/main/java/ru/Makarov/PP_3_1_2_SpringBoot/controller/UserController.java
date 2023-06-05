package ru.Makarov.PP_3_1_2_SpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.Makarov.PP_3_1_2_SpringBoot.models.User;
import ru.Makarov.PP_3_1_2_SpringBoot.service.UserService;

import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping(value = "/")
    public String showUsers(Model model) {
        List<User> listUsers = userServiceImpl.getUsers();
        model.addAttribute("users", listUsers);
        return "users/showAllUsers";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "users/saveUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/saveUser";
        }
        userServiceImpl.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(id));
        return "users/saveUser";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/";
    }
}
