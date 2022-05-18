package com.footballmanager.service;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import java.util.List;

public interface TeamService extends CrudService<Team, Long> {
    List<Player> getPlayersByTeam(Long teamId);

    void addPlayerToTeam(Long teamId, Player player);

    void removePlayerFromTeam(Long teamId, Player player);
}
