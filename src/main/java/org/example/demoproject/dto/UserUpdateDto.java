package org.example.demoproject.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class UserUpdateDto {

    @Length(min = 3, max = 20,
            message = "first name must be between 3 and 20 characters")
    private String firstName;

    @Length(min = 3, max = 20,
            message = "last name must be between 3 and 20 characters")
    private String lastName;

    @Email(message = "email-id must be of valid format")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
