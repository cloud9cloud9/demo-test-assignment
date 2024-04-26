package org.example.demoproject.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SearchUserRequestDto {

    @Past(message = "from date must be in past")
    private LocalDate from;

    @Past(message = "to date must be in past")
    private LocalDate to;

}
