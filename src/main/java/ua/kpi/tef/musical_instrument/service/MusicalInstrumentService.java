package ua.kpi.tef.musical_instrument.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.repository.MusicalInstrumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MusicalInstrumentService {
    private final MusicalInstrumentRepository instrumentRepository;

    @Autowired
    public MusicalInstrumentService(MusicalInstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public void createNewMusicalInstrument(MusicalInstrument instrument) throws InstrumentSaveException{
        try {
            instrumentRepository.save(instrument);
        }catch (DataIntegrityViolationException e) {
            throw new InstrumentSaveException("Can not instrument with  id=" + instrument.getId());
        }
    }

    public MusicalInstrument getMusicalInstrumentById(Long id) throws InstrumentNotFoundException {
        return  instrumentRepository.findById(id)
                .orElseThrow(() -> new InstrumentNotFoundException("Instrument id=" + id + " not found"));
    }

    public List<MusicalInstrument> getAllMusicalInstrument() {
        return instrumentRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public void editMusicalInstrument(MusicalInstrument instrument){
        instrumentRepository.save(instrument);
        log.info("editing musical instrument instrument");
    }

    public void deleteMusicalInstrumentById(Long instrumentId) throws InstrumentNotFoundException {
        MusicalInstrument instrument = instrumentRepository.findMusicalInstrumentById(instrumentId).orElseThrow(()
                -> new InstrumentNotFoundException("instrument" + instrumentId + " not found"));
        instrumentRepository.delete(instrument);
        log.info("deleting instrument");
    }

    public List<MusicalInstrument> getSearchedInstrumentsByKind(String kind) {
        return instrumentRepository.findAll().stream()
                .filter(p->p.getKind().toString().equals(kind))
                .collect(Collectors.toList());
    }

    public List<MusicalInstrument> getSearchedInstrumentsBySize(String size) {
        return instrumentRepository.findAll().stream()
                .filter(p->p.getKind().toString().equals(size))
                .collect(Collectors.toList());
    }
}
