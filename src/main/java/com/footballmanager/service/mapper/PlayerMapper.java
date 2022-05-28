package com.footballmanager.service.mapper;

import com.footballmanager.dto.request.PlayerRequestDto;
import com.footballmanager.dto.response.PlayerResponseDto;
import com.footballmanager.model.Player;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlayerMapper implements RequestDtoMapper <Player, PlayerRequestDto>,
ResponseDtoMapper <Player, PlayerResponseDto> {
    private final ModelMapper modelMapper;

    @Override
    public Player mapToModel(PlayerRequestDto dto) {
        return modelMapper.map(dto, Player.class);
    }

    @Override
    public PlayerResponseDto mapToDto(Player model) {
        return modelMapper.map(model, PlayerResponseDto.class);
    }
}
