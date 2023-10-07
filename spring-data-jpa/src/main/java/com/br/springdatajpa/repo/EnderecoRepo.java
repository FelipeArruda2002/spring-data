package com.br.springdatajpa.repo;

import com.br.springdatajpa.model.Endereco;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnderecoRepo extends RepoBase<Endereco, Long> {

}
