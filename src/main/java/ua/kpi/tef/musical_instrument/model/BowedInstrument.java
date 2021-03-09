package ua.kpi.tef.musical_instrument.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.tef.musical_instrument.model.enums.Resonators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BowedInstrument extends MusicalInstrument{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long bowedId;

    @Column
    @Enumerated(EnumType.STRING)
    private Resonators resonators;
    private String playingWay;
    private String additionalSubject;

    @OneToMany(mappedBy = "bowed")
    private List<Order> orders = new ArrayList<>();

}
