package org.example.demoproject.repository;

import org.example.demoproject.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        this.user = User.builder()
                .firstName("Vlad123")
                .lastName("Vladick123")
                .email("p9h6n@example.com")
                .birthDate(LocalDate.of(2000, 2, 1))
                .build();
    }

    @Test
    public void testFindByBirthDateBetween() {
        userRepository.save(user);
        List<User> byBirthDateBetween = userRepository.findByBirthDateBetween(LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 3, 1));

        assertEquals(1, byBirthDateBetween.size());

    }

    @Test
    public void testExistsByUserName() {
        userRepository.save(user);
        assertTrue(userRepository.existsByEmail(user.getEmail()));
    }
}
