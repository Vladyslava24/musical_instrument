package ua.kpi.tef.musical_instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;

import java.util.Optional;

@Repository
public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, Long> {
    Optional<MusicalInstrument> findMusicalInstrumentByInstrumentId(Long id);
}
