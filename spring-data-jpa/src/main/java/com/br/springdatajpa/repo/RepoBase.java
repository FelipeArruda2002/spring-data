package com.br.springdatajpa.repo;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface RepoBase<T, ID> extends Repository<T, ID> {

    Optional<T> findById(ID id);
    <S extends T> S save(S entity);

    <S extends T> List<S> saveAll(Iterable<S> entities);
}
