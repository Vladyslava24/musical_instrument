package ua.kpi.tef.musical_instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.tef.musical_instrument.model.PluckedInstrument;

import java.util.Optional;

public interface PluckedRepository extends JpaRepository<PluckedInstrument, Long> {
    Optional<PluckedInstrument> findBowedInstrumentById(Long id);
}
