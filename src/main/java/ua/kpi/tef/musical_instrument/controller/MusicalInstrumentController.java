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

    @PostMapping("find-all")
    public String getAllInstruments(Model model) {
        model.addAttribute("instruments", instrumentService.getAllMusicalInstrument());
        return "searchedlist";
    }

    @PostMapping("find-by-kind")
    public String getSearchedInstrumentsByKind(Model model, @RequestParam String kind) {
        Kind searchedKind = Kind.valueOf(kind);
        model.addAttribute("instruments", instrumentService.getSearchedInstrumentsByKind(searchedKind));
        return "searchedlist";
    }

    @PostMapping("find-by-size")
    public String getSearchedInstrumentsBySize(Model model, @RequestParam String instrumentSize) {
        InstrumentSize searchedSize = InstrumentSize.valueOf(instrumentSize);
        model.addAttribute("instruments",instrumentService.getSearchedInstrumentsBySize(searchedSize));
        return "searchedlist";
    }

}

