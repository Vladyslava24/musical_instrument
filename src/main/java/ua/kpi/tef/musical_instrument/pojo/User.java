package ua.kpi.tef.musical_instrument.pojo;

import lombok.Builder;
import lombok.Data;
import ua.kpi.tef.musical_instrument.pojo.enums.RoleType;

import javax.persistence.*;
import java.util.*;

@Data
@Builder

@Entity
@Table( name="user",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}