package com.footballmanager.service.impl;

import com.footballmanager.dto.request.TransactionRequestDto;
import com.footballmanager.exception.TransferSameTeamException;
import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.TeamService;
import com.footballmanager.service.TransactionService;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Override
    @Transactional
    public void doPlayerTransaction(TransactionRequestDto transactionRequestDto) throws Exception {
        if (Objects.equals(transactionRequestDto.getFromTeamId(),
                transactionRequestDto.getToTeamId())) {
            throw new TransferSameTeamException("Can't provide a transfer to the same team");
        }
        Player player = playerService.get(transactionRequestDto.getPlayerId());
        BigDecimal playerPrice = BigDecimal
                .valueOf(player.getExperienceMonths() * 100000 / player.getAge());
        Team teamFrom = teamService.get(transactionRequestDto.getFromTeamId());
        BigDecimal commissionSum = playerPrice
                .multiply(teamFrom.getCommission()).divide(BigDecimal.valueOf(100));
        BigDecimal transferSumValue = playerPrice.add(commissionSum);
        Team teamTo = teamService.get(transactionRequestDto.getToTeamId());
        teamService.addToCount(teamFrom, transferSumValue);
        teamService.subtractFromCount(teamTo, transferSumValue);
        teamService.removePlayerFromTeam(teamFrom, player);
        teamService.addPlayerToTeam(teamTo, player);
    }
}
