package com.mvp.parking.repository;

import java.util.Map;
import java.util.Optional;

public interface IRepository<T> {

    void save(T t);
    Optional<T> findByPlate(String key);
    Map<String, T> findAll();
    void update(T t);
}
