package br.com.section4.domain.enums.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;
    private boolean admin;

    public Usuario() {
        super();
    }

    public Usuario(Long id, String username, String senha, boolean admin) {
        this.id = id;
        this.username = username;
        this.senha = senha;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
