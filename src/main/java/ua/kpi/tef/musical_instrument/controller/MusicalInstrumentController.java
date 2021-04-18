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

    public void saveNewMusicalInstrument(MusicalInstrument instrument) throws InstrumentSaveException {
        try {
            instrumentService.createNewMusicalInstrument(instrument);
        } catch (InstrumentSaveException e){
            e.getMessage();
        }

    }

    public void editMusicalInstrument(Long id) throws InstrumentNotFoundException {
        try {
            instrumentService.getMusicalInstrumentById(id);
        } catch (InstrumentNotFoundException e){
            e.getMessage();
        }
    }

    public void editNewInstrument(Long id, MusicalInstrument instrument) {
        instrumentService.editMusicalInstrument(instrument);
    }

    //@GetMapping("delete_bowed{id}")
    public void deleteInstrument(@PathVariable Long id) throws InstrumentNotFoundException {
        instrumentService.deleteMusicalInstrumentById(id);
    }

    public void getSearchedInstruments(String kind, String size) {
        try {
            if (!kind.isEmpty()) {
                if (kind.equals(String.valueOf(Kind.STRINGS)) || kind.equals(String.valueOf(Kind.KEYBOARDS)))
                    instrumentService.getSearchedInstrumentsByKind(kind);
                else {
                    throw new InstrumentNotFoundException("Kind of instrument is wrong");
                }
            } else if (!size.isEmpty()) {
                if (size.equals(String.valueOf(InstrumentSize.SMALL)) || size.equals(String.valueOf(InstrumentSize.MEDIUM)))
                    instrumentService.getSearchedInstrumentsBySize(size);
                else {
                    throw new InstrumentNotFoundException("Size of instrument is wrong");
                }
            }
        } catch (InstrumentNotFoundException e) {
            e.getMessage();
        }
    }

}

