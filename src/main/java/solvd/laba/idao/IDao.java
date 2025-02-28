package solvd.laba.idao;

import java.util.List;

public interface IDao<T> {
    T create(T entity);
    T read(Long id);
    List<T> readAll();
    T update(T entity);
    Long remove(Long id);
}
