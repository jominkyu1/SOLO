package mkcrud.mkboard.controller;

import mkcrud.mkboard.service.UserCreateForm;
import mkcrud.mkboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserCreateController {

    private final UserService userService;

    @Autowired
    public UserCreateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "boardCreate";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "boardCreate";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다");
            return "boardCreate";
        }

        userService.createUser(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());

        return "redirect:/";
    }
}
