package com.footballmanager.service.impl;

import com.footballmanager.dto.request.TransactionRequestDto;
import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.TeamService;
import com.footballmanager.service.TransactionService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService<TransactionRequestDto> {
    private final PlayerService playerService;
    private final TeamService teamService;

    public TransactionServiceImpl(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public void doTransaction(TransactionRequestDto transactionRequestDto) {
        Player player = playerService.get(transactionRequestDto.getPlayerId());
        BigDecimal playerPrice = BigDecimal
                .valueOf(player.getExperienceMonths() * 100000 / player.getAge());
        Team teamFrom = teamService.get(transactionRequestDto.getFromTeamId());
        BigDecimal commissionSum = playerPrice
                .multiply(teamFrom.getCommission()).divide(BigDecimal.valueOf(100));
        BigDecimal transferSumValue = playerPrice.add(commissionSum);
        teamService.addToCount(teamFrom, transferSumValue);
        Team teamTo = teamService.get(transactionRequestDto.getToTeamId());
        teamService.subtractFromCount(teamTo, transferSumValue);
        teamService.removePlayerFromTeam(teamFrom, player);
        teamService.addPlayerToTeam(teamTo, player);
    }
}
