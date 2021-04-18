package ua.kpi.tef.musical_instrument.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.Order;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.OrderStatus;
import ua.kpi.tef.musical_instrument.repository.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MusicalInstrumentRepository instrumentRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MusicalInstrumentRepository instrumentRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.instrumentRepository = instrumentRepository;
    }

    public void makeOrder(Order order, MusicalInstrument instrument, User user) throws OrderBookingException,
            UserNotFoundException, InstrumentNotFoundException {
        User userToSave = userRepository.findById(user.getId())
                .orElseThrow(()->new UserNotFoundException("no user with id=" + user.getId()));
        MusicalInstrument instrumentToSave = instrumentRepository.findById(instrument.getId())
                .orElseThrow(()->new InstrumentNotFoundException("no instrument with id=" + instrument.getId()));
        order.saveOrder(instrumentToSave);
        if(userToSave != null) {
            order.saveOrder(userToSave);
        }
       // order.setOrderPrice(orderToSave.getPrice());
        order.setTotalOrderPrice(order.getPrice());
        order.setOrderStatus(OrderStatus.RESERVED);
        orderRepository.save(order);
    }

    public List<Order> findAllUserOrders(User user) {
        return orderRepository.findOrderByUser(user).stream()
                .collect(Collectors.toList());
    }
}
