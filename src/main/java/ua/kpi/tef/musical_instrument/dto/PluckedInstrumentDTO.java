package ua.kpi.tef.musical_instrument.dto;

import lombok.*;
import ua.kpi.tef.musical_instrument.model.enums.Resonators;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PluckedInstrumentDTO extends MusicalInstrumentDTO {
    private Long pluckedId;
    private Resonators resonators;
    private String playingWay;
}
