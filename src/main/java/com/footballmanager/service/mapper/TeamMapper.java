package com.footballmanager.service.mapper;

import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.model.Player;
import com.footballmanager.model.Team;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements ResponseDtoMapper<Team, TeamResponseDto>,
        RequestDtoMapper<Team, TeamRequestDto> {
    private final ModelMapper modelMapper;

    public TeamMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Team mapToModel(TeamRequestDto dto) {
        return modelMapper.map(dto, Team.class);
    }

    @Override
    public TeamResponseDto mapToDto(Team model) {
        TeamResponseDto teamResponseDto = modelMapper.map(model, TeamResponseDto.class);
        List<Long> playerIds = model.getPlayers().stream()
                .map(Player::getId)
                .toList();
        teamResponseDto.setPlayerIds(playerIds);
        return teamResponseDto;
    }
}
