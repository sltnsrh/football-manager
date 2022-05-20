package com.footballmanager.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PlayerRequestDto {
    @NotNull(message = "First name can't be empty")
    @Size(min = 3, max = 250, message = "First name must be between 3 and 250 characters")
    private String firstName;
    @NotNull(message = "Last name can't be empty")
    @Size(min = 3, max = 250, message = "Last name must be between 3 and 250 characters")
    private String lastName;
    @NotNull(message = "Age can't be empty")
    @Positive(message = "Age must be a positive number")
    private Short age;
    @NotNull(message = "Experience field can't be empty")
    @PositiveOrZero(message = "Playing experience in months must be positive or zero")
    private Short experienceMonths;
}
