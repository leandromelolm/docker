package com.company.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nome;
    private int idade;
    private int posicao;

    public Person() {
    }

    public Person(Long id, String nome, int idade, int posicao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
    }

    public Person(String nome) {
        this.nome = nome;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}