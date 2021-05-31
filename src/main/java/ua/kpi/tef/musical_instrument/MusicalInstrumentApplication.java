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


@SpringBootApplication  //(scanBasePackages = "ua.kpi.tef.musical_instrument")
public class MusicalInstrumentApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicalInstrumentApplication.class, args);
    }

}
