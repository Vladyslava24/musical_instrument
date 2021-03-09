package ua.kpi.tef.musical_instrument.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.musical_instrument.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUserId(Long userId);

    List<Order> findOrdertByMusicalInstrumentId(Long ticketId);
}
