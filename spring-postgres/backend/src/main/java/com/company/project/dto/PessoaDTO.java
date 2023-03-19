package com.company.project.dto;

import com.company.project.entity.Person;

public class PessoaDTO {

    private Long id;
    private String nome;
    private int idade;
    private int posicao;

    public PessoaDTO() {
    }

    public PessoaDTO (Person p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.idade = p.getIdade();
        this.posicao = p.getPosicao();
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
