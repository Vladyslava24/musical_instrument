package ua.kpi.tef.musical_instrument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.kpi.tef.musical_instrument.pojo.MusicalInstrument;
import ua.kpi.tef.musical_instrument.pojo.Order;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.*;
import ua.kpi.tef.musical_instrument.repository.MusicalInstrumentRepository;
import ua.kpi.tef.musical_instrument.repository.UserRepository;
import ua.kpi.tef.musical_instrument.service.MusicalInstrumentService;
import ua.kpi.tef.musical_instrument.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class MusicalInstrumentApplication {
    //private static MusicalInstrumentRepository instrumentRepository;

    public static void main(String[] args) {
        SpringApplication.run(MusicalInstrumentApplication.class, args);
       // MusicalInstrumentService musicalInstrumentService = new MusicalInstrumentService(instrumentRepository);
        //BigDecimal bigDecimal = BigDecimal.valueOf(500);
        //List<Order> orders = new ArrayList<>();
    }

}
