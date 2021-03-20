package ua.kpi.tef.musical_instrument.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import ua.kpi.tef.musical_instrument.pojo.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Setter

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;

    @ManyToOne
    private MusicalInstrument instrument;

    private int quantity;

    @ManyToOne
    private User user;

    private Date orderDate;

    private Date deliveryDate;

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
}
