package com.footballmanager.service.mapper;

import com.footballmanager.dto.request.TeamRequestDto;
import com.footballmanager.dto.response.TeamResponseDto;
import com.footballmanager.model.Team;
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
        return modelMapper.map(model, TeamResponseDto.class);
    }
}
