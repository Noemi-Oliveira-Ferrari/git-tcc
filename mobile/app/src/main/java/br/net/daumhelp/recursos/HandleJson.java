package br.net.daumhelp.recursos;

import com.google.gson.Gson;

import br.net.daumhelp.model.TokenBodyCliente;
import br.net.daumhelp.model.TokenBodyProfissional;

public class HandleJson {

    public static TokenBodyCliente getJsonTokenBodyCliente(String tokenBody){
        Gson gson = new Gson();
        TokenBodyCliente body = gson.fromJson(tokenBody, TokenBodyCliente.class);
        return body;
    }
    public static TokenBodyProfissional getJsonTokenBodyProfissional(String tokenBody){
        Gson gson = new Gson();
        TokenBodyProfissional body = gson.fromJson(tokenBody, TokenBodyProfissional.class);
        return body;
    }

}
