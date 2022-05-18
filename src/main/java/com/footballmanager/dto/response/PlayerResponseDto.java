package com.footballmanager.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Short age;
    private Short experienceMonths;
}
