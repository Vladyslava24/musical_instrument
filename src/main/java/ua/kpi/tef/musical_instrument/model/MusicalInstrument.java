package ua.kpi.tef.musical_instrument.model;

import ua.kpi.tef.musical_instrument.model.enums.Material;

import javax.persistence.Entity;

@Entity
public class MusicalInstrument {
    protected String name;
    protected String soundSource;
    protected boolean resonatorPresence;
    protected Material instrumentMaterial;

    public MusicalInstrument() {
    }

    public MusicalInstrument(String name, String soundSource, boolean resonatorPresence, Material instrumentMaterial) {
        this.name = name;
        this.soundSource = soundSource;
        this.resonatorPresence = resonatorPresence;
        this.instrumentMaterial = instrumentMaterial;
    }

}
