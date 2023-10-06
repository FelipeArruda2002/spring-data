package com.br.springdatajpa.repo;

import com.br.springdatajpa.model.Aluno;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepo extends RepoBase<Aluno, Long> {

    Aluno findByEmail(String email);

}
