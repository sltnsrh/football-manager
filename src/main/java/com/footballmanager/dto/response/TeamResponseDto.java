package com.footballmanager.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeamResponseDto {
    private Long id;
    private String name;
    private List<Long> playerIds;
    private BigDecimal count;
    private BigDecimal commission;
}
