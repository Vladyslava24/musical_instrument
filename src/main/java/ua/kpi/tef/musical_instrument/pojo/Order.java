package ua.kpi.tef.musical_instrument.pojo;

import lombok.*;
import ua.kpi.tef.musical_instrument.pojo.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private MusicalInstrument instrument;

    private int quantity;

    @ManyToOne
    private User user;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    private BigDecimal totalOrderPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order saveOrder(User user){
        this.setUser(user);
        user.getOrders().add(this);
        return this;
    }

    public Order saveOrder(MusicalInstrument instrument){
        this.setInstrument(instrument);
        instrument.getOrders().add(this);
        return this;
    }

    public BigDecimal getPrice() {
        if (this.instrument != null) {
            return this.instrument.getPrice().multiply(new BigDecimal(this.quantity));
        }
        return BigDecimal.ZERO;
    }

    public Order(MusicalInstrument instrument, int quantity, User user, LocalDate orderDate, LocalDate deliveryDate, BigDecimal totalOrderPrice, OrderStatus orderStatus) {
        this.instrument = instrument;
        this.quantity = quantity;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalOrderPrice = totalOrderPrice;
        this.orderStatus = orderStatus;
    }
}
