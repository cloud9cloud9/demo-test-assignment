package org.example.demoproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.demoproject.dto.UserDto;
import org.example.demoproject.dto.UserUpdateDto;
import org.example.demoproject.entity.User;
import org.example.demoproject.exception.UserAlreadyExistsException;
import org.example.demoproject.exception.UserNotFoundException;
import org.example.demoproject.mapper.UserMapper;
import org.example.demoproject.repository.UserRepository;
import org.example.demoproject.service.UserService;
import org.example.demoproject.service.UserValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserValidationService validationService;

    @Override
    public UserDto create(UserDto dto) {
        final var email = dto.getEmail();
        final var existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new UserAlreadyExistsException("user with email " + email + " already exists");
        }
        final var birthDate = dto.getBirthDate();
        validationService.validateUserAge(birthDate);
        final var user = userMapper.mapFrom(dto);
        userRepository.save(user);
        return userMapper.mapTo(user);
    }

    @Override
    public UserDto update(Long id,
                          UserUpdateDto dto) {

        final var user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        final var birthDate = dto.getBirthDate();
        if (birthDate != null) {
            validationService.validateUserAge(birthDate);
            user.setBirthDate(birthDate);
        }
        if (dto.getFirstName() != null && !user.getFirstName().equals(dto.getFirstName())) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null && !user.getLastName().equals(dto.getLastName())) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null && !user.getEmail().equals(dto.getEmail())) {
            final var email = dto.getEmail();
            final var existsByEmail = userRepository.existsByEmail(email);
            if (existsByEmail) {
                throw new UserAlreadyExistsException("user with email " + email + " already exists");
            }
            user.setEmail(dto.getEmail());
        }
        User updatedUser = userRepository.save(user);
        return userMapper.mapTo(updatedUser);
    }

    @Override
    public UserDto delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user != null) {
            userRepository.delete(user);
        }
        return userMapper.mapTo(user);
    }


    @Override
    public List<UserDto> findUserWithDateOfBirthBetween(LocalDate from,
                                                        LocalDate to) {
        validationService.validateUserAgeBetween(from, to);
        List<User> listOfUsers = userRepository.findByBirthDateBetween(from, to);
        return userMapper.mapFrom(listOfUsers);
    }
}
