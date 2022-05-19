package com.footballmanager.controller;

import com.footballmanager.dto.request.PlayerRequestDto;
import com.footballmanager.dto.response.PlayerResponseDto;
import com.footballmanager.model.Player;
import com.footballmanager.dto.request.TransactionRequestDto;
import com.footballmanager.service.PlayerService;
import com.footballmanager.service.TransactionService;
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
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final TransactionService<TransactionRequestDto> transactionService;
    private final RequestDtoMapper<Player, PlayerRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Player, PlayerResponseDto> responseDtoMapper;

    public PlayerController(PlayerService playerService,
                            TransactionService<TransactionRequestDto> transactionService,
                            RequestDtoMapper<Player, PlayerRequestDto> requestDtoMapper,
                            ResponseDtoMapper<Player, PlayerResponseDto> responseDtoMapper) {
        this.playerService = playerService;
        this.transactionService = transactionService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping
    public PlayerResponseDto add(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
        Player player = playerService.save(requestDtoMapper.mapToModel(playerRequestDto));
        return responseDtoMapper.mapToDto(player);
    }

    @GetMapping
    public List<PlayerResponseDto> getAll() {
        return playerService.getAll().stream()
                .map(responseDtoMapper::mapToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PlayerResponseDto getById(@PathVariable Long id) {
        Player player = playerService.get(id);
        return responseDtoMapper.mapToDto(player);
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid PlayerRequestDto playerRequestDto) {
        Player player = requestDtoMapper.mapToModel(playerRequestDto);
        player.setId(id);
        playerService.update(player);
        return responseDtoMapper.mapToDto(player);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        playerService.delete(id);
    }

    @PutMapping("/transaction")
    void transferPlayer(@RequestBody TransactionRequestDto transactionRequestDto) {
        transactionService.doTransaction(transactionRequestDto);
    }
}
