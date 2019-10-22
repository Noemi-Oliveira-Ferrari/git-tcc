import $ from 'jquery';
import sha512 from 'sha512';
import { validate } from 'cnpj';
import { isValid } from 'cpf';
import EmailValidator from 'email-validator';
import passwordValidator from 'password-validator';


export const validarConfirmacaoSenha = (senha, confirmSenha) =>{
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

export const moveToError = () =>{
    window.scrollTo(0, 200);
}

export const generateHash = (input) =>{
    let hash = sha512(input);
    return hash.toString("hex");
}


export const validarCnpj = (cnpj) =>{
    console.log("============");
    console.log(validate(cnpj));
    if(!validate(cnpj)){
        return withError($('#txt-cpfCnpj'));
    }else{
        return withoutError($('#txt-cpfCnpj'));
    }
}

export const validarCpfPro = (cpf) =>{
    if(!isValid(cpf)){
        return withError($('#txt-cpfCnpj'));
    }else{
        return withoutError($('#txt-cpfCnpj'));
    }
}
export const validarCpfCliente = (cpf) =>{
    if(!isValid(cpf)){
        return withError($('#txt-cpf'));
    }else{
        return withoutError($('#txt-cpf'));
    }
}

export const validarEmail = (email) =>{
    console.log(EmailValidator.validate(email.value));

    if(EmailValidator.validate(email.value)){
        console.log(".");
        return withoutError($('#txt-email'));
    }else{
        console.log("`");
        return withError($('#txt-email'));
    }
}

export const validarSenha = (input) =>{
    let regras = new passwordValidator();
    
    regras
    .is().min(8)
    .is().max(100)
    .has().letters()
    .has().lowercase()
    .has().digits()
    .has().not().spaces();

    if(!regras.validate(input)){
        withError($('#txt-senha'));
        return false;
    }else{
        withoutError($('#txt-senha'));
        return true;
    }
}
export const validarString = (input) =>{
    let regras = new passwordValidator();
    // console.log(input);
    regras
    .is().min(8)
    .is().max(150)
    .has().letters()
    .has().lowercase()
    .has().uppercase()
    .has().digits()
    .has().symbols()
    .is().not().oneOf([""], null);

    if(regras.validate(input.value)){
        withoutError($('#txt-nome'));
        return true;
    }else{
        withError($('#txt-nome'));
        return false;
    }
}

export const validarVazios = (campos) =>{
    let semErro = [];


    for(let i = 0; i< campos.length; i++){
        if(campos[i].value === ""){
            console.log("ERRO vazio "+$(campos[i]).attr("id").replace(/(txt)\-/g, ""));
            semErro.push(withError(campos[i]));
        }else{
            console.log("SHOW vazio "+$(campos[i]).attr("id").replace(/(txt)\-/g, ""));
            semErro.push(withoutError(campos[i]));
        }
    }
    // campos.forEach(campo =>{
    //     if(campo.value === ""){
    //         // console.log($(campo).attr("id").replace(/(txt)\-/g, ""));
    //         withError(campo);
    //         semErro = false;
    //         break;
    //     }else{
    //         // console.log($(campo).attr("id").replace(/(txt)\-/g, ""));
    //         withoutError(campo);
    //         semErro = true;
    //         break;
    //     }
    // });
    console.log(semErro);
    if(semErro.includes(false)){
        return false;
    }else{
        return true;
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

export const retirarSimbolos = (texto) =>{
    let textoLimpo = texto.replace(/[\'\"\!\@\#\$\%\¨\&\*\(\)\_\+\-\=\/\*\+\.\,]/g, "");
    return textoLimpo;
}

export const limpaValor = (valor) =>{
    valor = valor.replace(/(R\$)/g, "");
    let num;
    let decimal = valor.substring(valor.length-3, valor.length);
    // array = array.replace(/./g, "");
    if(valor.includes(",")){
        valor = valor.substring(0, valor.length-3);
    }
    valor = valor.replace(/\./g, "");
    decimal = decimal.replace(/\,/g, ".");
    num = parseFloat(valor+decimal);
    return num;
}

export const formataData = (data, delimitarAtual, novoDelimitador) =>{
    let arrayData = data.split(delimitarAtual);
    let novaData = arrayData[2]+novoDelimitador+arrayData[1]+novoDelimitador+arrayData[0];
    return novaData;
}

export const validarIdade = (dataNasc) =>{
    let data = new Date(formataData(dataNasc.value, "/", "-"));
    let hoje = new Date();
    let maioridadeMs = 568036800000;
    console.log(data);

    if(hoje - data > maioridadeMs){
        return true;
    }else{
        return false;
    }
}