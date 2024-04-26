package org.example.demoproject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.demoproject.dto.SearchUserRequestDto;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.entity.User;
import org.example.demoproject.exception.DateException;
import org.example.demoproject.exception.UserAgeException;
import org.example.demoproject.exception.UserAlreadyExistsException;
import org.example.demoproject.exception.UserNotFoundException;
import org.example.demoproject.mapper.UserDtoMapper;
import org.example.demoproject.mapper.UserMapper;
import org.example.demoproject.repository.UserRepository;
import org.example.demoproject.service.UserService;
import org.example.demoproject.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserDtoMapper dtoMapper;

    private final DateUtil dateUtil;

    @Override
    public void create(UserDto dto) {
        final var email = dto.getEmail();
        final var existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new UserAlreadyExistsException("user with email " + email + " already exists");
        }
        final var birthDate = dto.getBirthDate();
        final var isAdult = dateUtil.isAdult(birthDate);
        if (!isAdult) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
        final var user = userMapper.mapFrom(dto);
        //TODO: ENCODE PASSWORD BEFORE SAVING
        userRepository.save(user);
    }

    @Override
    public void update(Long id,
                       UserDto dto) {
        final var user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        final var birthDate = dto.getBirthDate();
        final var isAdult = dateUtil.isAdult(birthDate);
        if (!isAdult) {
            throw new UserAgeException();
        }
        user.setBirthDate(birthDate);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> findUserWithDateOfBirthBetween(SearchUserRequestDto requestDto) {
        final var fromDate = requestDto.getFrom();
        final var toDate = requestDto.getTo();
        boolean dateBefore = dateUtil.isDateBefore(fromDate, toDate);
        if (!dateBefore) {
            throw new DateException();
        }
        List<User> listOfUsers = userRepository.findByBirthDateBetween(fromDate, toDate);
        return dtoMapper.mapFrom(listOfUsers);
    }
}
