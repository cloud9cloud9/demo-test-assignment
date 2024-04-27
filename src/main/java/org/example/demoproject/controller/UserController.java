package org.example.demoproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demoproject.constant.ApiConstant;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.example.demoproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ApiConstant.BASE_URL)
public class UserController {

    private final UserService userService;

    @GetMapping(path = ApiConstant.FIND_ALL_USER_WITH_DATE_OF_BIRTH_BETWEEN)
    public List<UserDto> findUserWithDateOfBirthBetween(@RequestParam("from") LocalDate startDate,
                                                        @RequestParam("to") LocalDate endDate) {
        log.info("Find user with date of birth between: {} and {}", startDate, endDate);
        return userService.findUserWithDateOfBirthBetween(startDate, endDate);
    }

    @PostMapping(path = ApiConstant.CREATE_USER)
    public UserDto create(@RequestBody UserDto user) {
        log.info("Create user: {}", user);
        return userService.create(user);
    }

    @PutMapping(path = ApiConstant.UPDATE_USER)
    public UserDto update(@PathVariable("id") Long id,
                          @RequestBody UserUpdateDto user) {
        log.info("Update user: {}", user);
        return userService.update(id, user);
    }

    @DeleteMapping(path = ApiConstant.DELETE_USER)
    public UserDto delete(@PathVariable("id") Long id) {
        log.info("Delete user: {}", id);
        return userService.delete(id);
    }
}
