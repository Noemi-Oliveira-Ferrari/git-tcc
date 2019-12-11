package br.net.daumhelp.model;

import java.io.Serializable;

public class Confirmacao implements Serializable {

    private String nome;
    private String destinatario;
    private String codigoConfirm;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCodigoConfirm() {
        return codigoConfirm;
    }

    public void setCodigoConfirm(String codigoConfirm) {
        this.codigoConfirm = codigoConfirm;
    }


    @Override
    public String toString() {
        return nome + destinatario + codigoConfirm;
    }
}


