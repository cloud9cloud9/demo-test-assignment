package org.example.demoproject.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Setter
@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class UserDto {

    @NotNull(message = "first name must not be empty")
    @Length(min = 3, max = 20, message = "first name must be between 3 and 20 characters")
    private String firstName;

    @NotNull(message = "last name must not be empty")
    @Length(min = 3, max = 20, message = "last name must be between 3 and 20 characters")
    private String lastName;

    @NotNull(message = "password must not be empty")
    @Length(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "email-id must not be empty")
    @Email(message = "email-id must be of valid format")
    private String email;

    @NotNull(message = "birthDate of user must not be empty. birthDate in format yyyy-mm-d")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

}
