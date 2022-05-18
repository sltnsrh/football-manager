package com.footballmanager.controller;

import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.TeamService;
import com.footballmanager.service.mapper.RequestDtoMapper;
import com.footballmanager.service.mapper.ResponseDtoMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final PlayerService playerService;
    private final RequestDtoMapper<Team, TeamRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Team, TeamResponseDto> responseDtoMapper;

    public TeamController(TeamService teamService,
                          PlayerService playerService,
                          RequestDtoMapper<Team, TeamRequestDto> requestDtoMapper,
                          ResponseDtoMapper<Team, TeamResponseDto> responseDtoMapper) {
        this.teamService = teamService;
        this.playerService = playerService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public TeamResponseDto add(@RequestBody @Valid TeamRequestDto teamRequestDto) {
        Team team = teamService.save(requestDtoMapper.mapToModel(teamRequestDto));
        return responseDtoMapper.mapToDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto getById(@PathVariable Long id) {
        Team team = teamService.get(id);
        return responseDtoMapper.mapToDto(team);
    }

    @GetMapping
    public List<TeamResponseDto> getAll() {
        return teamService.getAll().stream()
                .map(responseDtoMapper::mapToDto)
                .toList();
    }

    @PutMapping("/{id}")
    public TeamResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid TeamRequestDto teamRequestDto) {
        Team team = requestDtoMapper.mapToModel(teamRequestDto);
        List<Player> players = teamService.get(id).getPlayers();
        team.setId(id);
        team.setPlayers(players);
        teamService.update(team);
        return responseDtoMapper.mapToDto(team);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        teamService.delete(id);
    }

    @GetMapping("/{id}/players")
    public List<Player> getPlayers(@PathVariable Long id) {
        return teamService.getPlayersByTeam(id);
    }

    @PutMapping("/{teamId}/players/{playerId}")
    public void addPlayerToTeam(@PathVariable Long teamId, @PathVariable Long playerId) {
        Player player = playerService.get(playerId);
        teamService.addPlayerToTeam(teamId, player);
    }
}
