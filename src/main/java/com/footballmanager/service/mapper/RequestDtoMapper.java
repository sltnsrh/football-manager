package com.footballmanager.service.mapper;

public interface RequestDtoMapper <M, D> {
    M mapToModel(D dto);
}
