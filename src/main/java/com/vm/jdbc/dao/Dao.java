package com.vm.jdbc.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {
    boolean update(E e);

    List<E> findAll();

    Optional<E> findById(K id);

    boolean delete(K id);

    E save(E e);
}
