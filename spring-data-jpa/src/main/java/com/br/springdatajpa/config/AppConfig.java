package com.br.springdatajpa.config;

import com.br.springdatajpa.model.Endereco;
import com.br.springdatajpa.model.User;
import com.br.springdatajpa.repo.EnderecoRepo;
import com.br.springdatajpa.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;


import java.util.List;

@Configuration
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    CommandLineRunner commandLineRunner(UserRepo repository, EnderecoRepo enderecoRepo) {

        return args -> {

            Endereco endereco = new Endereco();
            endereco.setCep("89041111");
            endereco.setNumero("2000");
            endereco.setRua("Rua XV");
            Endereco endereco1 = new Endereco();
            endereco1.setCep("89120211");
            endereco1.setNumero("301");
            endereco1.setRua("Rua 15");

            enderecoRepo.saveAll(List.of(endereco, endereco1));

            User user1 = new User();
            user1.setNome("Felipe");
            user1.setSobrenome("Arruda");
            user1.setEmail("felipe@gmail.com");
            user1.setEndereco(endereco);

            User user2 = new User();
            user2.setNome("Daniela");
            user2.setSobrenome("Vezaro");
            user2.setEmail("dani@gmail.com");
            user2.setEndereco(endereco1);

            User user3 = new User();
            user3.setNome("Oseias");
            user3.setSobrenome("Arruda");
            user3.setEmail("oseias@gmail.com");
            user3.setEndereco(endereco);

            User user4 = new User();
            user4.setNome("Antonio");
            user4.setSobrenome("Vezaro");
            user4.setEmail("antonio@gmail.com");
            user4.setEndereco(endereco1);

            LOGGER.info("Salvando usuários");
            repository.saveAll(List.of(user1, user2, user3, user4));

            Page<User> users = repository.findAll(PageRequest.of(0, 2));
            users.forEach(user -> LOGGER.info(user.getNome()));

            long qtdArruda = repository.countBySobrenome("Arruda");
            LOGGER.info(String.format("Quantidade de usuários com o sobrenome Arruda: %d", qtdArruda));

            LOGGER.info("Utilizando consultas(query methods):");
            List<User> findByEmailAndSobrenome = repository.findByEmailAndSobrenome("felipe@gmail.com", "Arruda");
            findByEmailAndSobrenome.forEach(user -> LOGGER.info(user.getNome()));
            List<User> distinctByEmailAndSobrenome = repository.findDistinctByEmailAndSobrenome("felipe@gmail.com", "Arruda");
            distinctByEmailAndSobrenome.forEach(user -> LOGGER.info(user.getNome()));

            // Ignore Case
            User felipe = repository.findByEmailAndSobrenomeAllIgnoreCase("FELIPE@GMAIL.COM", "Arruda");
            LOGGER.info("Ignore Case:" + felipe.getNome());

            // Order By
            // Crescente
            List<User> ordenados = repository.findBySobrenomeOrderByNomeAsc("Arruda");
            ordenados.forEach(user -> LOGGER.info(user.getNome() + " " + user.getSobrenome()));
            // Decrescente
            ordenados = repository.findBySobrenomeOrderByNomeDesc("Arruda");
            ordenados.forEach(user -> LOGGER.info(user.getNome() + " " + user.getSobrenome()));

            LOGGER.info("Rua XV");
            List<User> userRuaXV = repository.findByEndereco_Rua("Rua XV");
            userRuaXV.forEach(user -> LOGGER.info(user.getNome() + " " + user.getSobrenome()));



        };
    }

}
