package org.example.demoproject.controller;

import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.example.demoproject.entity.User;
import org.example.demoproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Captor
    private ArgumentCaptor<LocalDate> startDateCaptor;

    @Captor
    private ArgumentCaptor<LocalDate> endDateCaptor;

    private UserDto userDto;

    private UserUpdateDto userUpdateDto;

    public void setUp() {
        userService = Mockito.mock(UserService.class);
        userController = new UserController(userService);

        this.userUpdateDto = UserUpdateDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();
        this.userDto = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    public void testFindUserWithDateOfBirthBetween() {
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(1995, 12, 31);

        List<UserDto> expectedUsers = Arrays.asList(UserDto.builder().firstName("John").build(),
                UserDto.builder().firstName("Jane").build());

        when(userService.findUserWithDateOfBirthBetween(any(), any())).thenReturn(expectedUsers);

        List<UserDto> actualUsers = userController.findUserWithDateOfBirthBetween(startDate, endDate);

        verify(userService).findUserWithDateOfBirthBetween(startDateCaptor.capture(), endDateCaptor.capture());

        assertEquals(startDate, startDateCaptor.getValue());
        assertEquals(endDate, endDateCaptor.getValue());
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testCreateUser_ValidInput() {
        Mockito.when(userService.create(userDto)).thenReturn(userDto);

        userController.create(userDto);

        Mockito.verify(userService).create(userDto);
    }

    @Test
    public void testUpdateWithValidInput() {
        Long userId = 1L;
        when(userService.update(userId, userUpdateDto)).thenReturn(userDto);

        UserDto updatedUser = userController.update(userId, userUpdateDto);

        assertEquals("John", updatedUser.getFirstName());
        assertEquals("john.doe@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUserWithValidId() {
        Long validId = 1L;

        when(userService.delete(validId)).thenReturn(userDto);

        UserDto result = userController.delete(validId);

        assertEquals(userDto, result);
    }
}
