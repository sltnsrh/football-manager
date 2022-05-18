package com.footballmanager.service;

import java.util.List;

public interface CrudService<T, L> {
    T save(T t);

    T get(L id);

    List<T> getAll();

    T update(T t);

    void delete(L id);
}
