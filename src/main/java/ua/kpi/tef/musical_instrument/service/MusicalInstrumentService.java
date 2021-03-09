package ua.kpi.tef.musical_instrument.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.controller.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.dto.BowedInstrumentDTO;
import ua.kpi.tef.musical_instrument.dto.PluckedInstrumentDTO;
import ua.kpi.tef.musical_instrument.model.BowedInstrument;
import ua.kpi.tef.musical_instrument.model.PluckedInstrument;
import ua.kpi.tef.musical_instrument.repository.BowedRepository;
import ua.kpi.tef.musical_instrument.repository.PluckedRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MusicalInstrumentService {
    private final BowedRepository bowedRepository;
    private final PluckedRepository pluckedRepository;

    @Autowired
    public MusicalInstrumentService(BowedRepository bowedRepository, PluckedRepository pluckedRepository) {
        this.bowedRepository = bowedRepository;
        this.pluckedRepository = pluckedRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = InstrumentSaveException.class)
    public void createNewBowedInstrument(BowedInstrument bowed) throws InstrumentSaveException{
        try {
            bowedRepository.save(bowed);
        }catch (DataIntegrityViolationException e) {
            throw new InstrumentSaveException("Can not instrument with  id=" + bowed.getBowedId());
        }
    }

    public BowedInstrument getBowedInstrumentById(Long id) throws InstrumentNotFoundException {
        return  bowedRepository.findById(id)
                .orElseThrow(() -> new InstrumentNotFoundException("Instrument id=" + id + " not found"));
    }

    /*public List<BowedInstrumentDTO> getAllBowedInstrumentDTO() {
        return bowedRepository.findAll().stream()
                //.map(m -> getLocalizedDTO().map(m))
                .collect(Collectors.toList());
    }

    public List<PluckedInstrumentDTO> getAllPluckedInstrumentDTO() {
        return pluckedRepository.findAll().stream()
                //.map(m -> getLocalizedDTO().map(m))
                .collect(Collectors.toList());
    }*/

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = InstrumentSaveException.class)
    public void createNewPluckedInstrument(PluckedInstrument plucked) throws InstrumentSaveException{
        try {
            pluckedRepository.save(plucked);
        }catch (DataIntegrityViolationException e) {
            throw new InstrumentSaveException("Can not instrument with  id=" + plucked.getPluckedId());
        }
    }

    public void editBowedInstrument(BowedInstrument bowed){
        bowedRepository.save(bowed);
        log.info("editing bowed instrument");
    }

    public void deleteBowedInstrumentById(Long bowedId) throws InstrumentNotFoundException {
        BowedInstrument bowed = bowedRepository.findBowedInstrumentById(bowedId).orElseThrow(()
                -> new InstrumentNotFoundException("instrument" + bowedId + " not found"));
        bowedRepository.delete(bowed);
        log.info("deleting instrument");
    }
}
