package org.example.demoproject.service;


import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.example.demoproject.entity.User;
import org.example.demoproject.mapper.UserMapper;
import org.example.demoproject.repository.UserRepository;
import org.example.demoproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserValidationService validationService;

    @InjectMocks
    @Autowired
    private UserService userService;

    private UserDto dto;

    private User user;

    private UserUpdateDto updateDto;

    @BeforeEach
    public void setUp() {
        userMapper = Mockito.mock(UserMapper.class);
        validationService = Mockito.mock(UserValidationService.class);
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, userMapper, validationService);
        this.dto = UserDto
                .builder()
                .email("testuser@example.com")
                .firstName("test")
                .lastName("user")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
        this.user = User.builder()
                .firstName("test")
                .lastName("user")
                .email("testuser@example.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();

        this.updateDto = UserUpdateDto
                .builder()
                .email("testuser@example.com")
                .firstName("test")
                .lastName("user")
                .birthDate(LocalDate.of(1990, 1, 1))
                .build();
    }

    @Test
    public void testCreateNewUser() {
        when(userRepository.existsByEmail("testuser@example.com")).thenReturn(false);
        validationService.validateUserAge(dto.getBirthDate());
        when(userMapper.mapFrom(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapTo(user)).thenReturn(dto);

        UserDto createdUser = userService.create(dto);

        assertEquals(dto, createdUser);
        assertEquals("testuser@example.com", createdUser.getEmail());
    }

    @Test
    public void testDeleteUser_Success() {

        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.delete(1L);

        verify(userRepository, Mockito.times(1)).delete(user);

    }

    @Test
    public void testFindUserWithDateOfBirthBetween_UsersFound() {
        LocalDate from = LocalDate.of(1989, 1, 1);
        LocalDate to = LocalDate.of(2002, 1, 1);
        List<User> users = Arrays.asList(user);
        when(userRepository.findByBirthDateBetween(from, to)).thenReturn(users);
        List<UserDto> expectedDtos = Arrays.asList(dto);
        when(userMapper.mapFrom(users)).thenReturn(expectedDtos);

        List<UserDto> result = userService.findUserWithDateOfBirthBetween(from, to);

        assertEquals(expectedDtos, result);
    }
}
