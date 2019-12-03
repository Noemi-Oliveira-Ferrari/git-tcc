import React from 'react';
import axios from 'axios';
import { browserHistory } from 'react-router';
import { DOMINIO } from '../global';


export const verificarLogado = () =>{

    console.log("_________→→→→");
    console.log(localStorage.getItem("token") === "");

    const token = getToken();
    if(token === "" || token === null || token === undefined){
        console.log("ver se logado");
        browserHistory.push("/");
        return false;
    }else{
        return true;
    }
}

export const getUpdatedPro = (profissional) =>{

    axios({
        method: "GET",
        url: `${DOMINIO}profissionais/id/${profissional.idProfissional}`,
        timeout: 30000,
        headers: {"token": getToken()}
    })
    .then(response=>{
        console.log("---------------------9999");
        console.log(response.data);
        let jsonPro = JSON.stringify(response.data);
        localStorage.setItem("profissional", jsonPro);
        return jsonPro;
    })
    .catch(error=>{
        console.log(error);
        return "erro";
    });

}

export const getToken = () =>{
    const token = localStorage.getItem("token");
    return token;
}

export const getTipoLogado = () =>{
    const tipo = localStorage.getItem("app");
    return tipo;
}

export const getUsuarioPro = () =>{
    const pro = JSON.parse(localStorage.getItem("profissional"));
    return pro;
}

export const getUsuario = () =>{
    let user;
    if(getTipoLogado() === "cliente"){
        user = JSON.parse(localStorage.getItem("cliente"));
    }else{
        user = JSON.parse(localStorage.getItem("profissional"))
    }
    return user;
}

export const setUsuarioPro = (profissional) =>{
    console.log("*************")
    console.log(profissional);
    localStorage.setItem("profissional", JSON.stringify(profissional));
}

export const getUsuarioCliente = () =>{
    const cliente = localStorage.getItem("cliente");
    return cliente;
}