package com.repository.rss.service;

import com.repository.rss.domain.Identified;

import java.util.List;

public interface Service<T extends Identified> {

    List<T> getAll();

    T getById(Integer id);

    void save(T t);

    void delete(T t);

    void deleteById(Integer id);
}
