package br.net.daumhelp.model;


import java.io.Serializable;

public class Categoria implements Serializable {

    public Long idCategoria;

    public String categoria;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
