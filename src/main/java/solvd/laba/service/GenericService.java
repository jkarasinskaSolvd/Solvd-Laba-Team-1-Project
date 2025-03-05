package solvd.laba.service;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);
    T read(Long id);
    List<T> readAll();
    T update(T entity);
    Long remove(Long id);
}
