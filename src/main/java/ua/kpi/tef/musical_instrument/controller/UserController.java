package ua.kpi.tef.musical_instrument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(User user) {
        return userService.createUser(user);
    }

    public User findUserById(Long id) {
        return userService.findUserById(id);
    }

    public List<User> findAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @RequestMapping("/cab")
    public String getUserPage(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "cab";
    }

    public User updateUser(User user, String email, String lastName, String firstName, String username, String role){
        userService.updateUser(user, email, lastName, firstName, username, role);
        return user;
    }

    public void deleteById(Long id){
        userService.deleteById(id);
    }

}
