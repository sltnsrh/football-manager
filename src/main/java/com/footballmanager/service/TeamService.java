package com.footballmanager.service;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import java.math.BigDecimal;
import java.util.List;

public interface TeamService extends CrudService<Team, Long> {
    List<Player> getPlayersByTeam(Long teamId);

    void addPlayerToTeam(Team team, Player player);

    void removePlayerFromTeam(Team team, Player player);

    void addToCount(Team team, BigDecimal incomeSum);

    void subtractFromCount(Team team, BigDecimal outSum);
}
