package br.net.daumhelp.model;

import java.io.Serializable;

public class JwtToken implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
