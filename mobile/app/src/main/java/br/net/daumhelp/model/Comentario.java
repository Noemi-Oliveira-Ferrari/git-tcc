package br.net.daumhelp.model;

import java.io.Serializable;

public class Comentario implements Serializable {

    private String data;
    private String comentario;
    private int avaliacao;
    private Profissional profissional;
    private Cliente cliente;

    /*public Comentario(String nome, String data, String comentario, int avaliacao) {
        this.nome = nome;
        this.data = data;
        this.comentario = comentario;
        this.avaliacao = avaliacao;
    }*/


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "data='" + data + '\'' +
                ", comentario='" + comentario + '\'' +
                ", avaliacao=" + avaliacao +
                ", profissional=" + profissional +
                ", cliente=" + cliente +
                '}';
    }
}
