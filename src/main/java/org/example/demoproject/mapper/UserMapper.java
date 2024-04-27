package org.example.demoproject.mapper;

import org.example.demoproject.dto.UserDto;
import org.example.demoproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User mapFrom(UserDto from) {
        return User.builder()
                .email(from.getEmail())
                .firstName(from.getFirstName())
                .birthDate(from.getBirthDate())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .build();
    }


    @Override
    public UserDto mapTo(User from) {
        return UserDto.builder()
                .email(from.getEmail())
                .firstName(from.getFirstName())
                .birthDate(from.getBirthDate())
                .firstName(from.getFirstName())
                .lastName(from.getLastName())
                .build();
    }

    public List<UserDto> mapFrom(List<User> from) {
        return from.stream()
                .map(this::mapTo)
                .toList();
    }
}
