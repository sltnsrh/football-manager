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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final TransactionService transactionService;
    private final RequestDtoMapper<Player, PlayerRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Player, PlayerResponseDto> responseDtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponseDto add(@Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = requestDtoMapper.mapToModel(playerRequestDto);
        return responseDtoMapper.mapToDto(playerService.save(player));
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
                                    @Valid @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = requestDtoMapper.mapToModel(playerRequestDto);
        player.setId(id);
        playerService.update(player);
        return responseDtoMapper.mapToDto(player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Player was deleted successfully")
    void delete(@PathVariable Long id) {
        playerService.delete(id);
    }

    @PutMapping("/transaction")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Successful player transfer")
    void transferPlayer(@Valid @RequestBody TransactionRequestDto transactionRequestDto) throws Exception {
        transactionService.doPlayerTransaction(transactionRequestDto);
    }
}
