import $ from 'jquery';
import sha512 from 'sha512';
import { validate } from 'cnpj';



export const validarSenha = (senha, confirmSenha) =>{
    if((senha.value === confirmSenha.value) && (senha.value !== "" && confirmSenha.value !== "")){
        withoutError(confirmSenha);
        withoutError(senha);
        return true;
    }else if(senha.value === "" || confirmSenha.value === ""){
        withError(confirmSenha);
        withError(senha);
        return false;
    }else{
        withError(confirmSenha);
        withError(senha);
        return false;
    }
}

export const ancora = () =>{
    window.scrollTo(0, 200);
}

export const generateHash = (input) =>{
    let hash = sha512(input);
    return hash.toString("hex");
}


export const validarCnpj =() =>{
    if(!validate($("#txt-cpfCnpj").val())){
        return withError($('#txt-cpfCnpj'));
    }else{
        return withoutError($('#txt-cpfCnpj'));
    }
}


export const withError = (input) =>{
    $(input).addClass("erro");
    $(input).addClass("erro");
    return false;
}

export const withoutError = (input) =>{
    $(input).removeClass("erro");
    $(input).removeClass("erro");
    return true;
}