package com.br.springdatajpa.repo;

import com.br.springdatajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    long countBySobrenome(String sobrenome);
    List<User> removeBySobrenome(String sobrenome);
}
