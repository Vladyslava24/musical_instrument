package ua.kpi.tef.musical_instrument.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.kpi.tef.musical_instrument.pojo.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class MusicalInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private String soundSource;

    private boolean resonatorPresence;

    @Enumerated(EnumType.STRING)
    private Resonators resonators;

    @Enumerated(EnumType.STRING)
    private Material instrumentMaterial;

    @Enumerated(EnumType.STRING)
    private Coating coating;

    @Enumerated(EnumType.STRING)
    private InstrumentSize instrumentSize;

    @Enumerated(EnumType.STRING)
    private AvailableStatus availableStatus;

    private long availableAmount;

    private BigDecimal price;

    @OneToMany(mappedBy = "instrument")
    private List<Order> orders = new ArrayList<>();

    public MusicalInstrument(Long id, String name, Kind kind, String soundSource, boolean resonatorPresence, Resonators resonators, Material instrumentMaterial, Coating coating, InstrumentSize instrumentSize, AvailableStatus availableStatus, long availableAmount, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.soundSource = soundSource;
        this.resonatorPresence = resonatorPresence;
        this.resonators = resonators;
        this.instrumentMaterial = instrumentMaterial;
        this.coating = coating;
        this.instrumentSize = instrumentSize;
        this.availableStatus = availableStatus;
        this.availableAmount = availableAmount;
        this.price = price;
    }

    public MusicalInstrument(String name, Kind kind, String soundSource, boolean resonatorPresence, Resonators resonators, Material instrumentMaterial, Coating coating, InstrumentSize instrumentSize, AvailableStatus availableStatus, long availableAmount, BigDecimal price) {
        this.name = name;
        this.kind = kind;
        this.soundSource = soundSource;
        this.resonatorPresence = resonatorPresence;
        this.resonators = resonators;
        this.instrumentMaterial = instrumentMaterial;
        this.coating = coating;
        this.instrumentSize = instrumentSize;
        this.availableStatus = availableStatus;
        this.availableAmount = availableAmount;
        this.price = price;
    }
}
