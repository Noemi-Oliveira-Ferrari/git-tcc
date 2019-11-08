package br.net.daumhelp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status implements Serializable {


    private int idStatusPedido;
    private String status;

    public int getIdStatus() {
        return idStatusPedido;
    }

    public void setIdStatus(int idStatus) {
        this.idStatusPedido = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status{" +
                "idStatusPedido=" + idStatusPedido +
                ", status='" + status + '\'' +
                '}';
    }
}
