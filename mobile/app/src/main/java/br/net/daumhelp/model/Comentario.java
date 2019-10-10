package br.net.daumhelp.model;

public class Comentario {

    private String nome;
    private String data;
    private String comentario;
    private int avaliacao;

    public Comentario(String nome, String data, String comentario, int avaliacao) {
        this.nome = nome;
        this.data = data;
        this.comentario = comentario;
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    @Override
    public String toString() {
        return "Comentario{" +
                "nome='" + nome + '\'' +
                ", data='" + data + '\'' +
                ", comentario='" + comentario + '\'' +
                ", avaliacao=" + avaliacao +
                '}';
    }
}
