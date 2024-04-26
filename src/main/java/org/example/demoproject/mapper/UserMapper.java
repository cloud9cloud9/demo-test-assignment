package org.example.demoproject.mapper;

import org.example.demoproject.dto.UserDto;
import org.example.demoproject.entity.User;
import org.springframework.stereotype.Component;

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
}
