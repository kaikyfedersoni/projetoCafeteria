package com.example.backend.model;
import jakarta.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "funcionario_tipo")
@Entity
public class Funcionario {

    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String cpf;
    private String login;
    private String senha;

    public Funcionario() {}

    public Funcionario( String cpf, String nome, String senha, String login) {
        this.senha = senha;
        this.login = login;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
