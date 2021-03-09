package ua.kpi.tef.musical_instrument.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.controller.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.controller.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.dto.BowedInstrumentDTO;
import ua.kpi.tef.musical_instrument.model.BowedInstrument;
import ua.kpi.tef.musical_instrument.model.Order;
import ua.kpi.tef.musical_instrument.model.User;
import ua.kpi.tef.musical_instrument.model.enums.OrderStatus;
import ua.kpi.tef.musical_instrument.repository.BowedRepository;
import ua.kpi.tef.musical_instrument.repository.OrderRepository;
import ua.kpi.tef.musical_instrument.repository.PluckedRepository;
import ua.kpi.tef.musical_instrument.repository.UserRepository;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BowedRepository bowedRepository;
    private final PluckedRepository pluckedRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BowedRepository bowedRepository, PluckedRepository pluckedRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bowedRepository = bowedRepository;
        this.pluckedRepository = pluckedRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = OrderBookingException.class)
    public void makeOrder(Order order, BowedInstrumentDTO bowedDto, User user) throws OrderBookingException,
            UserNotFoundException, InstrumentNotFoundException {
        User userToSave = userRepository.findById(user.getUserId())
                .orElseThrow(()->new UserNotFoundException("no user with id=" + user.getUserId()));
        BowedInstrument bowedToSave = bowedRepository.findById(bowedDto.getBowedId())
                .orElseThrow(()->new InstrumentNotFoundException("no instrument with id=" + bowedDto.getBowedId()));
        order.saveOrder(bowedToSave);
        if(userToSave != null) {
            order.saveOrder(userToSave);
        }
       // order.setOrderPrice(orderToSave.getPrice());
        order.setOtderStatus(OrderStatus.RESERVED);
        orderRepository.save(order);
    }

    /*public List<OrderDTO> findAllUserOrders(Long userId) {
        return orderRepository.findOrderByUserId(userId).stream()
                .map(m -> getLocalizedDTO().map(m))
                .collect(Collectors.toList());
    }*/
}
