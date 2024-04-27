package org.example.demoproject.service;

import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {

    UserDto create(UserDto entity);

    UserDto update(Long id,
                   UserUpdateDto entity);

    UserDto delete(Long id);

    List<UserDto> findUserWithDateOfBirthBetween(LocalDate from,
                                                 LocalDate to);

}
