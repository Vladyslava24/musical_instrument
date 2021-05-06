package ua.kpi.tef.musical_instrument.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.Order;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;
import ua.kpi.tef.musical_instrument.service.OrderService;

import java.util.List;


@Controller
public class OrderController {
    private final OrderService orderService;

     @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(Order newOrder, MusicalInstrument instrument, User user) throws UserNotFoundException, InstrumentNotFoundException, OrderBookingException {
            return orderService.makeOrder(newOrder, instrument, user);
    }

    public List<Order> findAllUserOrders(User user){
         return orderService.findAllUserOrders(user);
    }
}
