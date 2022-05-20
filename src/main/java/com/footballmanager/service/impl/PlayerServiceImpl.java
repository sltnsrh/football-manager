package com.footballmanager.service.impl;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import com.footballmanager.repository.PlayerRepository;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.TeamService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player get(Long id) {
        return playerRepository.getById(id);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Player player = playerRepository.getById(id);
        Team team = teamService.getTeamByPlayer(player);
        teamService.removePlayerFromTeam(team, player);
        playerRepository.deleteById(id);
    }
}
