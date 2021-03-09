package ua.kpi.tef.musical_instrument.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.dto.BowedInstrumentDTO;
import ua.kpi.tef.musical_instrument.dto.PluckedInstrumentDTO;
import ua.kpi.tef.musical_instrument.model.BowedInstrument;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;

import java.util.List;

@Slf4j
@Controller
public class MusicalInstrumentController {
    private final MusicalInstrumentService instrumentService;

    @Autowired
    public MusicalInstrumentController(MusicalInstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("musical_instrument")
    public String getAdminHotelPage(Model model){
        /*List<BowedInstrumentDTO> bowedList = instrumentService.getAllBowedInstrumentDTO();
        List<PluckedInstrumentDTO> pluckedList = instrumentService.getAllPluckedInstrumentDTO();
        model.addAttribute("bowed", bowedList);
        model.addAttribute("plucked", pluckedList);*/
        return "show_all";
    }

    @PostMapping("save_bowed")
    public String saveNewBowed(Model model, @ModelAttribute("newBowed") BowedInstrument bowed) throws InstrumentSaveException {
        instrumentService.createNewBowedInstrument(bowed);
        return "save";
    }

    @GetMapping("edit_bowed{id}")
    public String editBowed(Model model, @PathVariable Long id) throws InstrumentNotFoundException {
        BowedInstrument bowed = instrumentService.getBowedInstrumentById(id);
        model.addAttribute("editBowed", bowed);
        model.addAttribute("bowedId", id);
        return "edit_bowed";
    }


    @PostMapping("edit_bowed{id}")
    public String editNewHotel(Model model,  @PathVariable Long id,
                               @ModelAttribute("editBowed") BowedInstrument bowed) {
        instrumentService.editBowedInstrument(bowed);
        return "edit_bowed";
    }

    @GetMapping("delete_bowed{id}")
    public String deleteTrip(@PathVariable Long id) throws InstrumentNotFoundException {
        instrumentService.deleteBowedInstrumentById(id);
        return "delete_bowed";
    }
}

