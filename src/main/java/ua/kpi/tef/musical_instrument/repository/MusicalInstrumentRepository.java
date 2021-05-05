package ua.kpi.tef.musical_instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.enums.InstrumentSize;
import ua.kpi.tef.musical_instrument.pojo.enums.Kind;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, Long> {
    Optional<MusicalInstrument> findMusicalInstrumentById(Long id);
    List<MusicalInstrument> findMusicalInstrumentByKind(Kind kind);
    List<MusicalInstrument> findMusicalInstrumentByInstrumentSize(InstrumentSize size);
}
