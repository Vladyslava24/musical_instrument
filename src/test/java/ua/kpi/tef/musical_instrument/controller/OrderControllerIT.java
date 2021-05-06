package ua.kpi.tef.musical_instrument.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.Order;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.*;
import ua.kpi.tef.musical_instrument.repository.OrderRepository;
import ua.kpi.tef.musical_instrument.repository.UserRepository;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class OrderControllerIT {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MusicalInstrumentService instrumentService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderController orderController;


    @Test
    void whenCreateOrderThenReturnNewOrder() throws UserNotFoundException, InstrumentNotFoundException, OrderBookingException, InstrumentSaveException {
        //GIVEN
        MusicalInstrument instrument1 = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        MusicalInstrument instrument2 = new MusicalInstrument(2L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        User user1 = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        User user2 = new User(2L, "Dmytro", "Alexeev",
                "alex_17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        Order order1 = new Order(1L, instrument1, 1, user1, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Order order2 = new Order(2L, instrument2, 1, user2, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        //WHEN
        instrumentService.createNewMusicalInstrument(instrument1);
        userRepository.save(user1);
        Order newOrder1 = orderController.createOrder(order1, instrument1, user1);
        //THEN
        Assert.assertEquals(order1.getId(), newOrder1.getId());
        Assert.assertEquals(order1.getInstrument().getId(), newOrder1.getInstrument().getId());
        Assert.assertEquals(order1.getUser().getId(), newOrder1.getUser().getId());
        Assert.assertEquals(order1.getOrderDate(), newOrder1.getOrderDate());
        Assert.assertEquals(order1.getDeliveryDate(), newOrder1.getDeliveryDate());
        Assert.assertEquals(order1.getDeliveryDate(), newOrder1.getDeliveryDate());
        Assert.assertEquals(order1.getPrice(), newOrder1.getPrice());
        Assert.assertEquals(order1.getOrderStatus(), newOrder1.getOrderStatus());

        //WHEN
        instrumentService.createNewMusicalInstrument(instrument2);
        userRepository.save(user2);
        Order newOrder2 = orderController.createOrder(order2, instrument2, user2);
        //THEN
        Assert.assertEquals(order2.getId(), newOrder2.getId());
        Assert.assertEquals(order2.getInstrument().getId(), newOrder2.getInstrument().getId());
        Assert.assertEquals(order2.getUser().getId(), newOrder2.getUser().getId());
        Assert.assertEquals(order2.getOrderDate(), newOrder2.getOrderDate());
        Assert.assertEquals(order2.getDeliveryDate(), newOrder2.getDeliveryDate());
        Assert.assertEquals(order2.getDeliveryDate(), newOrder2.getDeliveryDate());
        Assert.assertEquals(order2.getPrice(), newOrder2.getPrice());
        Assert.assertEquals(order2.getOrderStatus(), newOrder2.getOrderStatus());
    }

    @Test
    void whenFindAllUserOrdersThenReturnListOfUserOrders() throws InstrumentSaveException, UserNotFoundException, OrderBookingException, InstrumentNotFoundException {
        //GIVEN
        MusicalInstrument instrument1 = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        MusicalInstrument instrument2 = new MusicalInstrument(2L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        User user1 = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        User user2 = new User(2L, "Dmytro", "Alexeev",
                "alex_17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        userRepository.save(user1);
        List<Order> userOrdersList1 = new ArrayList<>();
        Order order1 = new Order(3L, instrument1, 1, user1, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Order order2 = new Order(4L, instrument2, 2, user1, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        userOrdersList1.add(order1);
        userOrdersList1.add(order2);

        userRepository.save(user2);
        List<Order> userOrdersList2 = new ArrayList<>();
        Order order3 = new Order(5L, instrument2, 3, user2, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Order order4 = new Order(6L, instrument1, 4, user2, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Order order5 = new Order(7L, instrument1, 5, user2, LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        userOrdersList2.add(order3);
        userOrdersList2.add(order4);
        userOrdersList2.add(order4);

        instrumentService.createNewMusicalInstrument(instrument1);
        instrumentService.createNewMusicalInstrument(instrument2);
        orderController.createOrder(order1, instrument1, user1);
        orderController.createOrder(order2, instrument2, user1);
        orderController.createOrder(order3, instrument2, user2);
        orderController.createOrder(order4, instrument1, user2);
        orderController.createOrder(order5, instrument1, user2);
        user1.setOrders(userOrdersList1);
        user2.setOrders(userOrdersList2);
        //WHEN
        final  List<Order> actualList1 = orderController.findAllUserOrders(user1);
        //THEN
        Assert.assertEquals(2, userOrdersList1.size());
        Assert.assertEquals(2, actualList1.size());
        Assert.assertEquals(Optional.of(3L), Optional.of(actualList1.get(0).getId()));
        Assert.assertEquals(Optional.of(4L), Optional.of(actualList1.get(1).getId()));
        Assert.assertEquals(2, actualList1.get(1).getQuantity());
        Assert.assertEquals(1, actualList1.get(0).getQuantity());

        //WHEN
        final  List<Order> actualList2 = orderController.findAllUserOrders(user2);
        //THEN
        Assert.assertEquals(3, userOrdersList2.size());
        Assert.assertEquals(3, actualList2.size());
        Assert.assertEquals(Optional.of(5L), Optional.of(actualList2.get(0).getId()));
        Assert.assertEquals(Optional.of(6L), Optional.of(actualList2.get(1).getId()));
        Assert.assertEquals(Optional.of(7L), Optional.of(actualList2.get(2).getId()));
        Assert.assertEquals(3, actualList2.get(0).getQuantity());
        Assert.assertEquals(4, actualList2.get(1).getQuantity());
        Assert.assertEquals(5, actualList2.get(2).getQuantity());
    }

    @After
    public void tearDown() throws InstrumentNotFoundException {
        userRepository.deleteById(1L);
        userRepository.deleteById(2L);
        instrumentService.deleteMusicalInstrumentById(1L);
        instrumentService.deleteMusicalInstrumentById(2L);
        orderRepository.deleteById(1L);
        orderRepository.deleteById(2L);
        orderRepository.deleteById(3L);
        orderRepository.deleteById(4L);
        orderRepository.deleteById(5L);
        orderRepository.deleteById(6L);
        orderRepository.deleteById(7L);
    }
}