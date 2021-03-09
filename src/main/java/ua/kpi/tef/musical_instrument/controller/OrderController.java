package ua.kpi.tef.musical_instrument.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.controller.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.controller.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.dto.BowedInstrumentDTO;
import ua.kpi.tef.musical_instrument.model.Order;
import ua.kpi.tef.musical_instrument.model.User;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;
import ua.kpi.tef.musical_instrument.service.OrderService;

@Slf4j
@Controller
public class OrderController {
    private final MusicalInstrumentService instrumentService;
    private final OrderService orderService;

     @Autowired
    public OrderController(MusicalInstrumentService instrumentService, OrderService orderService) {
        this.instrumentService = instrumentService;
        this.orderService = orderService;
    }

    @GetMapping("/make_order")
    public String createTicket(Model model, @ModelAttribute("newTicket") Order newOrder,
                               @ModelAttribute("bowed") BowedInstrumentDTO bowed,
                               User user)
            throws OrderBookingException, InstrumentNotFoundException, UserNotFoundException {
        orderService.makeOrder(newOrder, bowed, user);
        return "make_order";
    }
}
