package com.footballmanager.service.mapper;

public interface ResponseDtoMapper <M, D> {
    D mapToDto(M model);
}
