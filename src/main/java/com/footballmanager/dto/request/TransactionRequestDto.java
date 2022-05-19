package com.footballmanager.dto.request;

import lombok.Getter;

@Getter
public class TransactionRequestDto {
    private Long fromTeamId;
    private Long toTeamId;
    private Long playerId;
}
