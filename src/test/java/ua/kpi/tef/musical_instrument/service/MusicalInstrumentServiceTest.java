package ua.kpi.tef.musical_instrument.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.*;
import ua.kpi.tef.musical_instrument.repository.MusicalInstrumentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicalInstrumentServiceTest {

    @Autowired
    private MusicalInstrumentService instrumentService;

    @MockBean
    MusicalInstrumentRepository instrumentRepository;

    @Test
    public void whenCreateNewMusicalInstrumentThenSaveInstrument() throws InstrumentSaveException {
        MusicalInstrument instrument = new MusicalInstrument();
        instrumentService.createNewMusicalInstrument(instrument);
        Mockito.verify(instrumentRepository, Mockito.times(1)).save(ArgumentMatchers.any(MusicalInstrument.class));
    }

    @Test
    public void whenGetMusicalInstrumentByIdThenReturnInstrumentByID() throws InstrumentNotFoundException {
        MusicalInstrument instrument = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(instrument))
                .when(instrumentRepository)
                .findMusicalInstrumentById(1L);
    }

    @Test
    public void whenGetAllMusicalInstrumentThenReturnInstrumentList(){
        List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, instrumentList.size());
        Mockito.doReturn(instrumentList).when(instrumentRepository).findAll();
        List<MusicalInstrument> allInstruments = instrumentService.getAllMusicalInstrument();
        assertEquals(3, allInstruments.size());
    }

    @Test
    public void whenEditMusicalInstrumentThenSaveEditedInstrument(){
        MusicalInstrument instrument = new MusicalInstrument();
        instrumentService.editMusicalInstrument(instrument);
        Mockito.verify(instrumentRepository, Mockito.times(1)).save(ArgumentMatchers.any(MusicalInstrument.class));
    }

    @Test
    public void whenDeleteMusicalInstrumentByIdThenInvokeDelete() throws InstrumentNotFoundException {
        MusicalInstrument instrument = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        Mockito.doReturn(Optional.of(instrument))
                .when(instrumentRepository)
                .findMusicalInstrumentById(1L);
        instrumentService.deleteMusicalInstrumentById(1L);
        Mockito.verify(instrumentRepository, Mockito.times(1)).findMusicalInstrumentById(1L);
        Mockito.verify(instrumentRepository, Mockito.times(1)).delete(instrument);
    }

    @Test
    public void whenGetSearchedInstrumentsByKindTHenReturnInstrumentList(){
        List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, instrumentList.size());
        Mockito.doReturn(instrumentList).when(instrumentRepository).findMusicalInstrumentByKind(Kind.KEYBOARDS);
        List<MusicalInstrument> searchedList = instrumentService.getSearchedInstrumentsByKind(Kind.KEYBOARDS);
        assertEquals(2, searchedList.size());
        assertEquals(1L, searchedList.get(0).getId());
        assertEquals(2L, searchedList.get(1).getId());
    }

    @Test
    public void getSearchedInstrumentsBySize(){
        List<MusicalInstrument> instrumentList1 = new ArrayList<>();
        instrumentList1.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList1.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList1.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        assertEquals(3, instrumentList1.size());
        Mockito.doReturn(instrumentList1).when(instrumentRepository).findAll();
        List<MusicalInstrument> searchedList = instrumentService.getSearchedInstrumentsBySize(InstrumentSize.BIG);
        //assertEquals(1, searchedList.size());
        //assertEquals(1L, searchedList.get(0).getId());
        //assertEquals(3L, searchedList.get(1).getId());
    }
}
