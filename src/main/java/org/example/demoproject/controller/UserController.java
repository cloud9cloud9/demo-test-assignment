package org.example.demoproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.example.demoproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/find")
    public List<UserDto> findUserWithDateOfBirthBetween(@RequestParam("from") LocalDate startDate,
                                                        @RequestParam("to") LocalDate endDate) {
        log.info("Find user with date of birth between: {} and {}", startDate, endDate);
        return userService.findUserWithDateOfBirthBetween(startDate, endDate);
    }

    @PostMapping(path = "/create")
    public UserDto create(@RequestBody UserDto user) {
        log.info("Create user: {}", user);
        return userService.create(user);
    }

    @PutMapping(path = "/update/{id}")
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody UserUpdateDto user) {
        log.info("Update user: {}", user);
        return userService.update(id, user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public UserDto delete(@PathVariable("id") Long id) {
        log.info("Delete user: {}", id);
        return userService.delete(id);
    }
}
