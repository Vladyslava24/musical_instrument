package ua.kpi.tef.musical_instrument.dto;

import lombok.*;
import ua.kpi.tef.musical_instrument.model.enums.Resonators;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BowedInstrumentDTO extends MusicalInstrumentDTO {
    private Long bowedId;
    private Resonators resonators;
    private String playingWay;
    private String additionalSubject;
}
