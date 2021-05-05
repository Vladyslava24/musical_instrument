package ua.kpi.tef.musical_instrument.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.musical_instrument.exception.InstrumentNotFoundException;
import ua.kpi.tef.musical_instrument.exception.InstrumentSaveException;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.enums.*;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class MusicalInstrumentControllerIT {

    private static final Kind KIND_KEYBOARDS = Kind.KEYBOARDS;
    private static final InstrumentSize SIZE_MEDIUM = InstrumentSize.MEDIUM;

    @Autowired
    private MusicalInstrumentService instrumentService;

    @Autowired
    private MusicalInstrumentController instrumentController;

    @Test
    void whenGetAllListOfInstrumentsThenReturnAllList() throws InstrumentSaveException {
        //GIVEN
        final List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentService.createNewMusicalInstrument(instrumentList.get(0));
        instrumentService.createNewMusicalInstrument(instrumentList.get(1));
        instrumentService.createNewMusicalInstrument(instrumentList.get(2));
        //WHEN
        List<MusicalInstrument> instrumentList1 = instrumentController.getAllListOfInstruments();
        //THEN
        Assert.assertEquals(3, instrumentList1.size());
        Assert.assertEquals(Optional.of(1L), Optional.of(instrumentList1.get(0).getId()));
        Assert.assertEquals(Optional.of(2L), Optional.of(instrumentList1.get(1).getId()));
        Assert.assertEquals(Optional.of(3L), Optional.of(instrumentList1.get(2).getId()));
    }

    @Test
    void whenGetSearchedInstrumentsByKindThenReturnSearchedList() throws InstrumentSaveException {
        //GIVEN
        final List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentService.createNewMusicalInstrument(instrumentList.get(0));
        instrumentService.createNewMusicalInstrument(instrumentList.get(1));
        instrumentService.createNewMusicalInstrument(instrumentList.get(2));
        //WHEN
        List<MusicalInstrument> instrumentList1 = instrumentController.getSearchedInstrumentsByKind(KIND_KEYBOARDS);
        //THEN
        Assert.assertEquals(2, instrumentList1.size());
        Assert.assertEquals(Optional.of(1L), Optional.of(instrumentList1.get(0).getId()));
        Assert.assertEquals(Optional.of(2L), Optional.of(instrumentList1.get(1).getId()));
    }

    @Test
    void whenGetSearchedInstrumentsBySizeThenReturnSearchedList() throws InstrumentSaveException {
        //GIVEN
        final List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentService.createNewMusicalInstrument(instrumentList.get(0));
        instrumentService.createNewMusicalInstrument(instrumentList.get(1));
        instrumentService.createNewMusicalInstrument(instrumentList.get(2));
        //WHEN
        List<MusicalInstrument> instrumentList1 = instrumentController.getSearchedInstrumentsBySize(SIZE_MEDIUM);
        //THEN
        Assert.assertEquals(1, instrumentList1.size());
        Assert.assertEquals(Optional.of(3L), Optional.of(instrumentList1.get(0).getId()));
    }

    @Test
    void whenSaveNewMusicalInstrumentThenInvokeCreateNewMusicalInstrument() throws InstrumentSaveException {
        //GIVEN
        MusicalInstrument instrument = new MusicalInstrument(1L, "royal", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        //WHEN
        MusicalInstrument newInstrument = instrumentController.saveNewMusicalInstrument(instrument);

        //THEN
        Assert.assertEquals(instrument.getName(), newInstrument.getName());
        Assert.assertEquals(instrument.getKind(), newInstrument.getKind());
        Assert.assertEquals(instrument.getSoundSource(), newInstrument.getSoundSource());
        Assert.assertEquals(instrument.getResonators(), newInstrument.getResonators());
        Assert.assertEquals(instrument.getInstrumentMaterial(), newInstrument.getInstrumentMaterial());
        Assert.assertEquals(instrument.getCoating(), newInstrument.getCoating());
        Assert.assertEquals(instrument.getInstrumentSize(), newInstrument.getInstrumentSize());
        Assert.assertEquals(instrument.getAvailableStatus(), newInstrument.getAvailableStatus());
        Assert.assertEquals(instrument.getAvailableAmount(), newInstrument.getAvailableAmount());
        Assert.assertEquals(instrument.getPrice(), newInstrument.getPrice());
    }

    @Test
    void whenGetMusicalInstrumentByIdThenInvokeGetMusicalInstrumentById() throws InstrumentNotFoundException, InstrumentSaveException {
        //GIVEN
        final List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "royal", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentService.createNewMusicalInstrument(instrumentList.get(0));
        instrumentService.createNewMusicalInstrument(instrumentList.get(1));
        instrumentService.createNewMusicalInstrument(instrumentList.get(2));

        //WHEN
        MusicalInstrument instrument1 = instrumentController.getMusicalInstrumentById(1L);
        MusicalInstrument instrument2 = instrumentController.getMusicalInstrumentById(2L);
        MusicalInstrument instrument3 = instrumentController.getMusicalInstrumentById(3L);

        //THEN
        Assert.assertEquals(Optional.of(1L), Optional.of(instrument1.getId()));
        Assert.assertEquals("piano", instrument1.getName());
        Assert.assertEquals(Optional.of(2L), Optional.of(instrument2.getId()));
        Assert.assertEquals("royal", instrument2.getName());
        Assert.assertEquals(Optional.of(3L), Optional.of(instrument3.getId()));
        Assert.assertEquals("guitar", instrument3.getName());
    }


    @Test
    void whenEditNewInstrumentThenInvokeEditMusicalInstrument() throws InstrumentSaveException {
        //GIVEN
        MusicalInstrument oldInstrument = new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN);
        MusicalInstrument editInstrument = new MusicalInstrument(1L, "guitar", Kind.STRINGS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 18L, BigDecimal.TEN);

        MusicalInstrument oldTestInstrument = instrumentController.saveNewMusicalInstrument(oldInstrument);
        Assert.assertEquals(Optional.of(1L), Optional.of(editInstrument.getId()));
        Assert.assertEquals(oldInstrument.getName(), oldTestInstrument.getName());
        Assert.assertEquals(oldInstrument.getKind(), oldTestInstrument.getKind());
        Assert.assertEquals(oldInstrument.getSoundSource(), oldTestInstrument.getSoundSource());
        Assert.assertEquals(oldInstrument.getAvailableStatus(), oldTestInstrument.getAvailableStatus());
        Assert.assertEquals(oldInstrument.getAvailableAmount(),oldTestInstrument.getAvailableAmount());
        //WHEN
        MusicalInstrument newInstrument = instrumentController.editNewInstrument(editInstrument);
        Assert.assertEquals(Optional.of(1L), Optional.of(editInstrument.getId()));
        Assert.assertEquals(editInstrument.getName(), newInstrument.getName());
        Assert.assertEquals(editInstrument.getKind(), newInstrument.getKind());
        Assert.assertEquals(editInstrument.getSoundSource(), newInstrument.getSoundSource());
        Assert.assertEquals(editInstrument.getAvailableStatus(), newInstrument.getAvailableStatus());
        Assert.assertEquals(editInstrument.getAvailableAmount(), newInstrument.getAvailableAmount());
    }

    @Test
    void whenDeleteInstrumentThenInvokeDeleteMusicalInstrumentById() throws InstrumentSaveException {
        //GIVEN
        final List<MusicalInstrument> instrumentList = new ArrayList<>();
        instrumentList.add( new MusicalInstrument(1L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(2L, "piano", Kind.KEYBOARDS, "key",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.BIG,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));
        instrumentList.add( new MusicalInstrument(3L, "guitar", Kind.KEYBOARDS, "string",
                true, Resonators.STRINGED_MEDIUM, Material.CEDAR, Coating.OIL, InstrumentSize.MEDIUM,
                AvailableStatus.IN_STOCK, 12L, BigDecimal.TEN));

        instrumentService.createNewMusicalInstrument(instrumentList.get(0));
        instrumentService.createNewMusicalInstrument(instrumentList.get(1));
        instrumentService.createNewMusicalInstrument(instrumentList.get(2));

        List<MusicalInstrument> instrumentList1 = instrumentController.getAllListOfInstruments();
        Assert.assertEquals(3, instrumentList1.size());
        //WHEN
        instrumentController.deleteInstrument(1L);
        List<MusicalInstrument> instrumentList2 = instrumentController.getAllListOfInstruments();
        //THEN
        Assert.assertEquals(2, instrumentList2.size());
        //WHEN
        instrumentController.deleteInstrument(3L);
        List<MusicalInstrument> instrumentList3 = instrumentController.getAllListOfInstruments();
        //THEN
        Assert.assertEquals(1, instrumentList3.size());
    }

    @After
    public void tearDown(){
        instrumentController.deleteInstrument(1L);
        instrumentController.deleteInstrument(2L);
        instrumentController.deleteInstrument(3L);
    }

}