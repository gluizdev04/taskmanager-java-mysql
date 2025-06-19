package io.github.luiz.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId(){ return id; }
    public String getNome(){ return nome; }
    public String getEmail(){ return email; }
    public String getSenha(){ return senha; }

    public void setId(int id){ this.id = id;}
    public void setNome(){ this.nome = nome; }
    public void setEmail(){ this.email = email; }
    public void setSenha(){ this.senha = senha; }

}
