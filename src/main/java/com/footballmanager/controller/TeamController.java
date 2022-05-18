package com.footballmanager.controller;

import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.model.Team;
import com.footballmanager.service.TeamService;
import com.footballmanager.service.mapper.RequestDtoMapper;
import com.footballmanager.service.mapper.ResponseDtoMapper;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final RequestDtoMapper<Team, TeamRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Team, TeamResponseDto> responseDtoMapper;

    public TeamController(TeamService teamService,
                          RequestDtoMapper<Team, TeamRequestDto> requestDtoMapper,
                          ResponseDtoMapper<Team, TeamResponseDto> responseDtoMapper) {
        this.teamService = teamService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public TeamResponseDto add(@RequestBody @Valid TeamRequestDto teamRequestDto) {
        Team team = teamService.save(requestDtoMapper.mapToModel(teamRequestDto));
        return responseDtoMapper.mapToDto(team);
    }
}
