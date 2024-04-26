package org.example.demoproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demoproject.dto.SearchUserRequestDto;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/find")
    public List<UserDto> findUserWithDateOfBirthBetween(@RequestBody SearchUserRequestDto requestDto) {
        log.info("Find user with date of birth between: {} and {}", requestDto.getFrom(), requestDto.getTo());
        return userService.findUserWithDateOfBirthBetween(requestDto);
    }

    @PostMapping(path = "/create")
    public void create(@RequestBody UserDto user) {
        log.info("Create user: {}", user);
        userService.create(user);
    }

    @PutMapping(path = "/update/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody UserDto user) {
        log.info("Update user: {}", user);
        userService.update(id, user);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("Delete user: {}", id);
        userService.delete(id);
    }
}
