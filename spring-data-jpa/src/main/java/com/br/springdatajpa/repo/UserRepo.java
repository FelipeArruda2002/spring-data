package com.br.springdatajpa.repo;

import com.br.springdatajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    long countBySobrenome(String sobrenome);
    List<User> removeBySobrenome(String sobrenome);

    List<User> findByEmailAndSobrenome(String email, String sobrenome);

    List<User> findDistinctByEmailAndSobrenome(String email, String sobrenome);

    User findByEmailAndSobrenomeAllIgnoreCase(String email, String sobrenome);

    List<User> findBySobrenomeOrderByNomeAsc(String sobrenome);
    List<User> findBySobrenomeOrderByNomeDesc(String sobrenome);

    List<User> findByEndereco_Rua(String rua);

}
