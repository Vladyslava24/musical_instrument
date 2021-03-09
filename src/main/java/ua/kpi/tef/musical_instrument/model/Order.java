package ua.kpi.tef.musical_instrument.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.tef.musical_instrument.model.enums.OrderStatus;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long orderId;

    @ManyToOne
    private BowedInstrument bowed;

    @ManyToOne
    private PluckedInstrument plucked;

    @ManyToOne
    private User user;

    private String email;

    private String phoneNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private long instrumentPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus otderStatus;

    public Order saveOrder(User user){
        this.setUser(user);
        user.getOrders().add(this);
        return this;
    }

    public Order saveOrder(BowedInstrument instrument){
        this.setBowed(instrument);
        instrument.getOrders().add(this);
        return this;
    }

    public Order saveOrder(PluckedInstrument instrument){
        this.setPlucked(instrument);
        instrument.getOrders().add(this);
        return this;
    }
}
