package org.example.demoproject.service;

import org.example.demoproject.dto.SearchUserRequestDto;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UserService {

    void create(UserDto entity);

    void update(Long id,
                UserDto entity);

    void delete(Long id);

    List<UserDto> findUserWithDateOfBirthBetween(SearchUserRequestDto requestDto);

}
