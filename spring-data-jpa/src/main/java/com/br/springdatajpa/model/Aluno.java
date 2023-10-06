package com.br.springdatajpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @SequenceGenerator(
            name = "aluno_sequence",
            sequenceName = "aluno_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "aluno_sequence"
    )
    private Long id;
    private String nome;
    private String email;
    private String matricula;

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
