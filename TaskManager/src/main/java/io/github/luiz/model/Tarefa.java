package io.github.luiz.model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private int usuarioId;

    public Tarefa(String titulo, String descricao, int usuarioId) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
    }

    public Tarefa(int id, String titulo, String descricao, boolean concluida, int usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.usuarioId = usuarioId;
    }
    

    public int getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public boolean isConcluida() {
        return concluida;
    }
    public int getUsuarioId() {
        return usuarioId;
    }

    
    public void setId(int id) {
        this.id = id;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
