package ua.kpi.tef.musical_instrument.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import ua.kpi.tef.musical_instrument.exception.UserNotFoundException;
import ua.kpi.tef.musical_instrument.pojo.User;
import ua.kpi.tef.musical_instrument.pojo.enums.RoleType;
import ua.kpi.tef.musical_instrument.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

///@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService  userService;

    @MockBean
    UserRepository userRepository;


    @Test
    void whenCreateUserThenReturnUser() {
        User user = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        assertEquals(user.getFirstName(), userService.createUser(user).getFirstName());
        assertEquals(user.getLastName(), userService.createUser(user).getLastName());
        assertEquals(user.getEmail(), userService.createUser(user).getEmail());
        assertEquals(user.getRole(), userService.createUser(user).getRole());
    }

    @Test
    void whenFindUserByIdThenReturnUserById() {
        Mockito.doReturn(Optional.of(new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER)))
                .when(userRepository)
                .findUserById(1L);
    }

    @Test
    void whenFindAllThenReturnUserList() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER));
        list.add(new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER));
        Mockito.doReturn(list).when(userRepository).findAll();
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    void whenUpdateUserThenReturnUpdatedUser() {
        User user = new User(1L, "Vlada", "Lila",
                "vlada.lada.17@gmail.com", "qwerty", "qwerty", RoleType.ROLE_USER);
        userService.updateUser(user, "ann_shevh.17@gmail.com", "Ann", "Shevchenko", "Ann123");
        assertEquals("ann_shevh.17@gmail.com", user.getEmail());
        assertEquals("Ann", user.getLastName());
        assertEquals("Shevchenko", user.getFirstName());
        assertEquals("Ann123", user.getUsername());
    }

    @Test
    void whenDeleteByIdThenInvokeDelete() {
        userService.deleteById(1L);
        userService.deleteById(5L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(5L);
    }
}