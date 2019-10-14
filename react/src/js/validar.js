import $ from 'jquery';


export const validarSenha = (senha, confirmSenha) =>{
    if(senha.value === confirmSenha.value){
        $(confirmSenha).html('match');
        $(confirmSenha).removeClass("erro");
        $(senha).removeClass("erro");
    }else if(senha === "" || confirmSenha === ""){
        $(confirmSenha).addClass("erro");
        $(senha).addClass("erro");
    }else{
        $(confirmSenha).html('mismatch');
        $(confirmSenha).addClass("erro");
        $(senha).addClass("erro");
    }
}

export const ancora = () =>{
    window.scrollTo(0, 200);
}
