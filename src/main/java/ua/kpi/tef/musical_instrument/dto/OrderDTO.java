package ua.kpi.tef.musical_instrument.dto;

import lombok.*;
import ua.kpi.tef.musical_instrument.model.BowedInstrument;
import ua.kpi.tef.musical_instrument.model.PluckedInstrument;
import ua.kpi.tef.musical_instrument.model.enums.OrderStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private Long id;

    private BowedInstrument bowedDto;

    private PluckedInstrument pluckedDto;

    //private User user;

    private int place;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private long orderPrice;

    private OrderStatus orderStatus = OrderStatus.AVAILABLE;
}
