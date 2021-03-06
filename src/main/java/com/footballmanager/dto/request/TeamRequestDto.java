package com.footballmanager.dto.request;

import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TeamRequestDto {
    @NotNull(message = "Team name can't be empty")
    @Size(min = 4, max = 300, message = "Team name must be between 4 and 300 characters")
    private String name;
    @NotNull(message = "Team count can't be null")
    @PositiveOrZero(message = "Count value must be positive or zero")
    private BigDecimal count;
    @NotNull(message = "Team commission can't be null")
    @Min(value = 0, message = "Team commission must be minimum 0")
    @Max(value = 10, message = "Team commission must be maximum 10")
    private BigDecimal commission;
}
