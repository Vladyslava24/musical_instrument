package ua.kpi.tef.musical_instrument.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.OrderBookingException;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.Order;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.*;
import ua.kpi.tef.musical_instrument.repository.MusicalInstrumentRepository;
import ua.kpi.tef.musical_instrument.repository.OrderRepository;
import ua.kpi.tef.musical_instrument.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    MusicalInstrumentRepository instrumentRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    OrderRepository orderRepository;


    @Test
    public void whenMakeOrderThenReturnOrder() throws UserNotFoundException, InstrumentNotFoundException, OrderBookingException {
        User user = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);

        MusicalInstrument instrument = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(instrument))
                .when(instrumentRepository)
                .findMusicalInstrumentById(1L);
        Order order = new Order(1L, instrument, 1, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED);
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findUserById(1L);
        orderService.makeOrder(order, instrument, user);
        Mockito.verify(userRepository, Mockito.times(1)).findUserById(1L);
        Mockito.verify(instrumentRepository, Mockito.times(1)).findMusicalInstrumentById(1L);
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void makeOrderFail(){

    }

    @Test
    public void whenFindAllUserOrdersThenReturnAllUserOrders(){
        MusicalInstrument instrument1 = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        MusicalInstrument instrument2 = new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);

        List<Order> userOrdersList1 = new ArrayList<>();
        userOrdersList1.add(new Order(1L, instrument1, 1, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList1.add(new Order(2L, instrument2, 2, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList1.add(new Order(3L, instrument2, 3, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        List<Order> userOrdersList2= new ArrayList<>();
        userOrdersList2.add(new Order(4L, instrument2, 3, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        userOrdersList2.add(new Order(5L, instrument2, 2, new User(), LocalDate.EPOCH, LocalDate.EPOCH, BigDecimal.TEN, OrderStatus.RESERVED));
        User user1 = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER, userOrdersList1);
        User user2 = new User(1L, "Denis", "Lila",
                "den__@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER, userOrdersList2);

        Mockito.doReturn(userOrdersList1).when(orderRepository).findOrderByUser(user1);
        List<Order> getOrderList1 = orderService.findAllUserOrders(user1);
        assertEquals(3, getOrderList1.size());
        assertEquals(1L, getOrderList1.get(0).getId());
        assertEquals(2L, getOrderList1.get(1).getId());
        assertEquals(3L, getOrderList1.get(2).getId());

        Mockito.doReturn(userOrdersList2).when(orderRepository).findOrderByUser(user2);
        List<Order> getOrderList2 = orderService.findAllUserOrders(user2);
        assertEquals(2, getOrderList2.size());
        assertEquals(4L, getOrderList2.get(0).getId());
        assertEquals(5L, getOrderList2.get(1).getId());
    }

}
