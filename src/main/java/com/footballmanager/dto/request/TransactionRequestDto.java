package com.footballmanager.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class TransactionRequestDto {
    @Positive(message = "From team id must be positive")
    @NotNull(message = "From team id can't be null")
    private Long fromTeamId;
    @Positive(message = "To team id must be positive")
    @NotNull(message = "To team id can't be null")
    private Long toTeamId;
    @Positive(message = "Player id must be positive")
    @NotNull(message = "Player id can't be null")
    private Long playerId;
}
