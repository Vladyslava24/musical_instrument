package ua.kpi.tef.musical_instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kpi.tef.musical_instrument.model.BowedInstrument;

import java.util.Optional;

public interface BowedRepository extends JpaRepository<BowedInstrument, Long> {
    Optional<BowedInstrument> findBowedInstrumentById(Long id);
}
