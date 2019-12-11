package br.net.daumhelp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TipoUsuario implements Serializable {

    @JsonAlias("idTipoUsuario")
    private int idTipoUsuario;

    @JsonAlias("tipoUsuario")
    private Character tipoDeUsuario;

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Character getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(Character tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
                "idTipoUsuario=" + idTipoUsuario +
                ", tipoDeUsuario=" + tipoDeUsuario +
                '}';
    }
}

