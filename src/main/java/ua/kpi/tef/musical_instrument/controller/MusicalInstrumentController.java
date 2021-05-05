package ua.kpi.tef.musical_instrument.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.enums.InstrumentSize;
import ua.kpi.tef.musical_instrument.pojo.enums.Kind;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;

import java.util.List;


@Controller
public class MusicalInstrumentController {
    private final MusicalInstrumentService instrumentService;

    @Autowired
    public MusicalInstrumentController(MusicalInstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    public List<MusicalInstrument> getAllListOfInstruments(){
        return instrumentService.getAllMusicalInstrument();
    }

    public MusicalInstrument saveNewMusicalInstrument(MusicalInstrument instrument) {
        try {
            instrumentService.createNewMusicalInstrument(instrument);
        } catch (InstrumentSaveException e){
            e.getMessage();
        }
        return instrument;
    }

    public MusicalInstrument getMusicalInstrumentById(Long id) throws InstrumentNotFoundException {
        try {
            instrumentService.getMusicalInstrumentById(id);
        } catch (InstrumentNotFoundException e){
            e.getMessage();
        }
        return instrumentService.getMusicalInstrumentById(id);
    }

    public MusicalInstrument editNewInstrument(MusicalInstrument instrument) {
        return instrumentService.editMusicalInstrument(instrument);
    }

    public void deleteInstrument(Long id) {
        try {
            instrumentService.deleteMusicalInstrumentById(id);
        } catch (InstrumentNotFoundException e){
            e.getMessage();
        }
    }

    public List<MusicalInstrument> getSearchedInstrumentsByKind(Kind kind) {
        return instrumentService.getSearchedInstrumentsByKind(kind);
    }

    public List<MusicalInstrument> getSearchedInstrumentsBySize(InstrumentSize size) {
        return instrumentService.getSearchedInstrumentsBySize(size);
    }

}

