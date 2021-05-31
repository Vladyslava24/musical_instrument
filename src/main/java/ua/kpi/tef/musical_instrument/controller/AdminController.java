package ua.kpi.tef.musical_instrument.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.RoleType;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;
import ua.kpi.tef.musical_instrument.service.OrderService;
import ua.kpi.tef.musical_instrument.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final MusicalInstrumentService instrumentService;
    private final OrderService orderService;

    public AdminController(UserService userService, MusicalInstrumentService instrumentService, OrderService orderService) {
        this.userService = userService;
        this.instrumentService = instrumentService;
        this.orderService = orderService;
    }

    @GetMapping("admin-page")
    public String getAdminPage(@AuthenticationPrincipal User user){
        if(user.getRole().equals(RoleType.ROLE_ADMIN))
            return "admin_page";
        else {
            return "Access denied for user" + user.getFirstName();
        }
    }

    @GetMapping("/userslist")
    public String findAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "userlist";
    }

    @GetMapping("/instrumentslist")
    public String findAllInstruments(Model model){
        model.addAttribute("instruments", instrumentService.getAllMusicalInstrument());
        return "instrumentslist";
    }


    @GetMapping("delete_user{id}")
    public String deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return "redirect:/userslist";
    }

    @GetMapping("delete_instrument{id}")
    public String deleteInstrumentById(@PathVariable Long id) throws InstrumentNotFoundException {
        instrumentService.deleteMusicalInstrumentById(id);
        return "redirect:/instrumentslist";
    }

    @GetMapping("edit_user{id}")
    public String getEditUserPage(Model model, @PathVariable Long id) throws UserNotFoundException {
        User user = userService.findUserById(id);
        model.addAttribute("editUser", user);
        model.addAttribute("userId", id);
        return "edit_user";
    }

    @PostMapping("edit_user{id}")
    public String editUser(@PathVariable Long id,
                               @ModelAttribute("editUser") User user,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("email") String email,
                           @RequestParam("username") String username,
                           @RequestParam("role") String role) {
        userService.updateUser(user, firstName, lastName, email, username, role);
        return "redirect:/userslist";
    }

    @GetMapping("create-instrument")
    public String getAdminHotelPage(Model model){
        model.addAttribute("newInstrument", MusicalInstrument.builder().build());
        return "create_instrument";
    }

    @PostMapping("create-instrument")
    public String createNewInstrument(Model model, @ModelAttribute("newInstrument") MusicalInstrument instrument,
                              @RequestParam("availableAmount") String availableAmount,
                              @RequestParam("price") String price,
                              BindingResult bindingResult) throws InstrumentSaveException {
        Long amount = Long.valueOf(availableAmount);
        BigDecimal newPrice = BigDecimal.valueOf(Long.parseLong(price));
        instrument.setAvailableAmount(amount);
        instrument.setPrice(newPrice);
        instrumentService.createNewMusicalInstrument(instrument);
        return "redirect:/instrumentslist";
    }

    @GetMapping("edit_instrument{id}")
    public String getEditInstrumentPage(Model model, @PathVariable Long id) throws InstrumentNotFoundException {
        MusicalInstrument instrument = instrumentService.getMusicalInstrumentById(id);
        model.addAttribute("editInstrument", instrument);
        model.addAttribute("instrumentId", id);
        return "edit_instrument";
    }

    @PostMapping("edit_instrument{id}")
    public String editInstrument(@PathVariable Long id,
                           @ModelAttribute("editInstrument") MusicalInstrument instrument,
                           @RequestParam("availableAmount") String availableAmount,
                           @RequestParam("price") String price) {
        long amount = Long.parseLong(availableAmount);
        BigDecimal newPrice = BigDecimal.valueOf(Long.parseLong(price));
        instrument.setAvailableAmount(amount);
        instrument.setPrice(newPrice);
        instrumentService.editMusicalInstrument(instrument);
        return "redirect:/instrumentslist";
    }

}
