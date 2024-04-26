package org.example.demoproject.mapper;

import org.example.demoproject.dto.UserDto;
import org.example.demoproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto mapFrom(User from) {
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
                .map(this::mapFrom)
                .toList();
    }
}
