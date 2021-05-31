package ua.kpi.tef.musical_instrument.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.RoleType;
import ua.kpi.tef.musical_instrument.service.RegistrationFormService;
import ua.kpi.tef.musical_instrument.service.UserService;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class RegistrationFormController {
    private final RegistrationFormService registrationFormService;
    private final UserService userService;

    @Autowired
    public RegistrationFormController(RegistrationFormService registrationFormService,
                                      UserService userService) {
        this.registrationFormService = registrationFormService;
        this.userService = userService;
    }

    @GetMapping("register")
    public String regForm(@ModelAttribute("newUser") User user){
        return "register";
    }

    @ResponseStatus( HttpStatus.CREATED)
    @PostMapping(value = "register")
    public String regFormController(Model model, @ModelAttribute("newUser") User user) {
        registrationFormService.saveNewUser(User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .role(RoleType.ROLE_USER)
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .build());
        log.info("{}", user);
        return "redirect:/login";
    }

    /*@RequestMapping(value = "user", method = RequestMethod.GET)
    public UsersDTO getAllUser(){
        //log.info("{}",userService.getAllUsers());
        return userService.getAllUsers();
    }*/
}
