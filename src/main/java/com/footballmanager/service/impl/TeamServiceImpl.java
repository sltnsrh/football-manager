package com.footballmanager.service.impl;

import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import com.footballmanager.repository.TeamRepository;
import com.footballmanager.service.TeamService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private  final TeamRepository teamRepository;

    @Override
    public Team save(Team team) {
        List<Player> players = new ArrayList<>();
        team.setPlayers(players);
        return teamRepository.save(team);
    }

    @Override
    public Team get(Long id) {
        return teamRepository.getById(id);
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<Player> getPlayersByTeam(Long teamId) {
        Team team = teamRepository.getById(teamId);
        return team.getPlayers();
    }

    @Override
    public void addPlayerToTeam(Team team, Player player) {
        team.getPlayers().add(player);
        teamRepository.save(team);
    }

    @Override
    public void removePlayerFromTeam(Team team, Player player) {
        team.getPlayers().remove(player);
        teamRepository.save(team);
    }

    @Override
    public void addToCount(Team team, BigDecimal incomeSum) {
        team.setCount(team.getCount().add(incomeSum));
        teamRepository.save(team);
    }

    @Override
    public void subtractFromCount(Team team, BigDecimal outSum) {
        team.setCount(team.getCount().subtract(outSum));
        teamRepository.save(team);
    }

    @Override
    public Team getTeamByPlayer(Player player) {
        return teamRepository.getTeamByPlayer(player);
    }
}
