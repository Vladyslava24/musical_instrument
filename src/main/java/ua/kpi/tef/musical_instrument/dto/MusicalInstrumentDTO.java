package ua.kpi.tef.musical_instrument.dto;

import lombok.*;
import ua.kpi.tef.musical_instrument.model.enums.Material;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MusicalInstrumentDTO {
    private String name;
    private String soundSource;
    private boolean resonatorPresence;
    private Material instrumentMaterial;
}
