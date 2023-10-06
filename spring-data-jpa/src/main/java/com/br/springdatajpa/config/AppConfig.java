package com.br.springdatajpa.config;

import com.br.springdatajpa.model.Aluno;
import com.br.springdatajpa.model.User;
import com.br.springdatajpa.repo.AlunoRepo;
import com.br.springdatajpa.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;
import java.util.ListResourceBundle;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(UserRepo repository, AlunoRepo alunoRepo) {

        return args -> {

            User user1 = new User();
            user1.setNome("Felipe");
            user1.setSobrenome("Arruda");
            user1.setEmail("felipe@gmail.com");

            User user2 = new User();
            user2.setNome("Daniela");
            user2.setSobrenome("Vezaro");
            user2.setEmail("dani@gmail.com");

            User user3 = new User();
            user3.setNome("Oseias");
            user3.setSobrenome("Arruda");
            user3.setEmail("oseias@gmail.com");

            LOGGER.info("Salvando usuários");
            repository.saveAll(List.of(user1, user2, user3));

            Page<User> users = repository.findAll(PageRequest.of(1, 2));
            users.forEach(user -> LOGGER.info(user.getNome()));

            long qtdArruda = repository.countBySobrenome("Arruda");
            LOGGER.info(String.format("Quantidade de usuários com o sobrenome Arruda: %d", qtdArruda));

            Aluno aluno = new Aluno();
            aluno.setNome("Felipe");
            aluno.setEmail("felipe@gmail.com");
            aluno.setMatricula("1000");

            LOGGER.info("Salvando aluno");
            aluno = alunoRepo.save(aluno);
            long id = alunoRepo.findById(aluno.getId()).get().getId();
            LOGGER.info("ID:" + id);
            aluno = alunoRepo.findByEmail("felipe@gmail.com");
            LOGGER.info("Aluno:" + aluno.getNome() + " " +  aluno.getEmail());

        };
    }

}
