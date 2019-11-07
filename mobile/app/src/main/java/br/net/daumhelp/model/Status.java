package br.net.daumhelp.model;

import java.io.Serializable;

public class Status implements Serializable {


    private int idStatus;
    private String status;

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
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
                "idStatus=" + idStatus +
                ", status='" + status + '\'' +
                '}';
    }
}
