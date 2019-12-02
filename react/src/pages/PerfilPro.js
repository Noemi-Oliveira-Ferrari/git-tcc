import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import io from "socket.io-client";
import $ from 'jquery';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';
import Edit from '../img/edit.png';
import Save from '../img/save.png';
import SaveDisabled from '../img/save-disabled.png';
import Cancel from '../img/cancel.png';
import ImgPadrao from '../img/padrao_perfil.png';
import axios from 'axios';
import MenuLateral from '../components/MenuLateral';
import {Inputs, Selects, InputNumber} from '../components/FormElements';
import { verificarLogado, getToken, getUsuarioPro, getUpdatedPro } from '../utils/verificaSessionStrg';
import { Route, browserHistory } from 'react-router';
import { DadosPessoaisPro, DadosProfissional } from '../components/FormularioProfissional';
import { BotaoImg } from '../components/Botao';
import { ModalLoadConst, ModalAlertas} from '../components/ModaisLoad';
import { DOMINIO, DOMINIO_IMG } from '../global';
import { validarConfirmacaoSenha, moveToError, generateHash, withError,
         withoutError, validarCnpj, validarCpfPro, validarEmail,
         validarSenha, validarString, validarVazios, retirarSimbolos,
         formataData, limpaValor, validarIdade} from '../js/validar';

let BUSCAR = false;


class PerfilPro extends Component{


    constructor() {
        super();
        this.state = {
            nomePro: "", servicoPro: "",
            localPro: "", notaPro: "",
            valorPro: "", fotoPro: "",
            iconEdit: Edit, 
            iconSave: SaveDisabled,
            atualizar: false,
            erros: [],
            tipoAlerta: "",
            tituloAlerta: "",
            showModalErro: false,
            loading: false,
        }
        this.enableFields = this.enableFields.bind(this);
        this.colocaDadosNaCapa = this.colocaDadosNaCapa.bind(this);
        this.setPro = this.setPro.bind(this);
        this.validarCampos = this.validarCampos.bind(this);
        this.chamar = this.chamar.bind(this);
        
        this.ModalAlertas = this.ModalAlertas.bind(this);
        this.mostrarAlerta = this.mostrarAlerta.bind(this);
    }


    jaCadastrado(tipo, input, elemento){
        withError(elemento);
        let erros = [];
        erros.push(`${tipo} ${input} ja cadastrado`);
        this.setState({erros: erros});
        setTimeout(()=>{this.mostrarAlerta("alertaAlt", "PROBLEMA NA ATUALIZAÇÃO");}, 500);
    }

    //MOSTRA ALERTA SE ÃO HOUVER CONEXAÃO COM O SERVIDOR
    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        setTimeout(()=>{this.mostrarAlerta("erroAlt", "ERRO");}, 500);
    }

    //CONTROLA CAIXA DE LOAD
    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            // $("body").css("padding-right", "35px");
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
    }

    mostrarAlerta(tipoAlerta, tituloAlerta){
        this.setState({tipoAlerta: tipoAlerta});
        this.setState({tituloAlerta: tituloAlerta});
        setTimeout(()=>{this.ModalAlertas()}, 200);
    }

    ModalAlertas = () =>{
        if(!this.state.showModalErro){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({showModalErro: !this.state.showModalErro});
    }

    componentDidMount(){
        // this.preencherCampos();
        verificarLogado();
        console.log("vai setar os dados");
        this.enableFields(false);
        console.log("********")
        console.log(this.state.atualizar);
    }

    colocaDadosNaCapa(pro){
        let decimal;
        let valorHora;
        let localPro = `${pro.endereco.cidade.cidade} - ${pro.endereco.cidade.microrregiao.uf.estado}`;
        let valor = pro.valorHora.toString();

        if(valor.includes(".")){
            valorHora = valor.split(".")[0];
            decimal = valor.split(".")[1];
            decimal = decimal.length < 2 ? decimal+"0" : decimal;
            valorHora += ","+decimal;
        }else{
            valorHora = valor+",00";
        }
        // valorHora = valorHora.includes(".") ? valorHora.replace(".", ",") : valorHora+",00";
        valorHora += "/h"

        if(pro !== null && pro !== ""){
            this.setState({nomePro: pro.nome});
            this.setState({servicoPro: pro.subcategoria.subcategoria});
            this.setState({localPro: localPro});
            this.setState({valorPro: valorHora});
            // this.setState({fotoPro: ImgPadrao});
            this.setState({fotoPro: `url(${DOMINIO_IMG}duh/imagens/padrao_perfil.png)`});
        }
    }

    enableFields(cancelar){
        let continuar;
        if(this.state.atualizar){
            console.log("a partir de agora SALVA");
            this.setState({iconEdit: Cancel});
            this.setState({iconSave: Save});
            $("input, select, textarea").removeAttr("disabled");
            // $("input, select, textarea").css("background-color", "#88ffcc");
            $("input, select, textarea").css("background-color", "#CCE5E9");
            $("#text-atualizar-perfil").css("background-color", "#ebebeb");
            $("#text-atualizar-perfil").removeAttr("disabled");
            $("#img-editar-perfil").attr("alt", "Cancelar");
            $("#img-editar-perfil").attr("title", "Cancelar");
        }else{

            if(cancelar)
                continuar = window.confirm("Deseja mesmo cancelar a operação?");
            else
                continuar = false;

            if(continuar){              
                browserHistory.push("/app/profissional/servicos");
                console.log("cancelou");
            }
            console.log("a partir de agora NAO SALVA");
            this.setState({iconEdit: Edit});
            // $("input, select, textarea").attr("disabled", "disabled");
            $("input, select, textarea").css("background-color", "#d5d5d5");
            $("#text-atualizar-perfil").css("background-color", "#d2d2d2");
            $("#text-atualizar-perfil").attr("disabled", "disabled");
            this.colocaDadosNaCapa(getUsuarioPro());
        }
        this.setState({atualizar: !this.state.atualizar});
    }

    chamar(){
        // if(BUSCAR === true){
            console.log("----------");
            withoutError($("input, select"));
            this.enableFields(true);
            // this.preencherCampos();
        // }else if(BUSCAR === false){
        //     this.enableFields(false);
        // }
        BUSCAR = !BUSCAR;

    }

    validarCampos(){
        let campos = document.querySelectorAll("input[type=InputNumber], input[type=text], input[type=email], select");
        let semErro = true;
        let erros = [];

        if(!validarVazios(campos)){
            semErro = false;
            erros.push("Há campos não preenchidos!\n");
            console.log("validarVazios "+semErro);
        }

        if(!validarString($('#txt-nome').get(0))){
            semErro = false;
            erros.push("O Nome digitado é inválido!\n");
            console.log("validarString nome "+semErro);
        }

        if(!validarIdade($('#txt-dataNasc').get(0))){
            semErro = false;
            erros.push("Para ser cadastrar é necessário ter no mínimo 18 anos!\n");
            console.log("validarIdade "+semErro);
        }

        if($('.caixa-cpfCnpj').text() === "CPF"){
            if(!validarCpfPro($('#txt-cpfCnpj').val())){
                semErro = false;
            erros.push("CPF Inválido\n");
                console.log("validarCpfPro "+semErro);
            }
        }else{
            if(!validarCnpj($('#txt-cpfCnpj').val())){
                semErro = false;
                erros.push("CNPJ Inválido\n");
                console.log("validarCnpj "+semErro);
            } 
        }

        if(!validarEmail($('#txt-email').get(0))){
            semErro = false;
            erros.push("E-mail digitado não é válido\n");
            console.log("validarEmail "+semErro);
        }

        // if(!validarSenha($('#txt-senha').val())){
        //     semErro = false;
        //     erros.push("A senha deve ter letras maiúsculas e minúsculas, números, símbolos(@#$...), ter no mínimo 8 caractéres e não pode conter espaços");
        //     console.log("validarSenha "+semErro);
        // }

        // if(!validarConfirmacaoSenha($('#txt-senha').get(0), $('#txt-confirmar-senha').get(0))){
        //     semErro = false;
        //     erros.push("As senhas não correspondem!\n");
        //     console.log("validarConfirmacaoSenha "+semErro);
        // }

        this.setState({erros: erros});
        erros = [];
        return semErro;
    }
    
    setPro(event){
        event.preventDefault();
        let cpfCnpj = $("#txt-cpfCnpj").val().replace(/[.-]/g, "");
        let cpf;
        let cnpj;
        if(cpfCnpj.length <= 14){
            cpf = retirarSimbolos(cpfCnpj);
            cnpj = null;
        }else{
            cnpj = retirarSimbolos(cpfCnpj);
            cpf = null;
        }
        console.log(limpaValor($("#txt-valor-hora").val()));
        if(this.validarCampos()){
            let endereco = {
                idEndereco: getUsuarioPro().endereco.idEndereco,
                cep: retirarSimbolos($("#txt-cep").val()),
                logradouro: $("#txt-logradouro").val(),
                bairro: $("#txt-bairro").val(),
                numero: null,
                cidade: {
                    idCidade: $("#txt-cidade").attr("data-idCidade")
                }
            };
            let profissional = {
                idProfissional: getUsuarioPro().idProfissional,
                nome: $("#txt-nome").val(),
                dataNasc: formataData($("#txt-dataNasc").val(), "/", "-"),
                cpf: cpf,
                cnpj: cnpj,
                email: $("#txt-email").val(),
                senha: generateHash($("#txt-senha").val()),
                tipoUsuario: {
                    idTipoUsuario: 1
                },
                endereco: {
                    idEndereco: null
                },
                subcategoria: {
                    idSubcategoria: $("#slt-subcat").val(),
                },
                valorHora: limpaValor($("#txt-valor-hora").val()),
                resumoQualificacoes: $("#txt-qualificacoes").val()
            };
            console.log(endereco);
            console.log(profissional);
            // this.enableFields(false);
            this.atualizarDadosPro(profissional, endereco);
        }else{
            setTimeout(() => {
                this.mostrarAlerta("alertaAlt", "FALHA NA GRAVAÇÃO");
            }, 200);
        }
    }

    atualizarDadosPro(profissional, endereco){
        let alerta = [];
        const atualizarPro = (profissional) =>{
            axios({
                method: 'PUT',
                url: `${DOMINIO}profissionais/atualizar/id/${profissional.idProfissional}`,
                type: "application/json",
                header: {"token": getToken()},
                timeout: 30000,
                data: 
                {
                    idProfissional: profissional.idProfissional,
                    cnpj: profissional.cnpj,
                    cpf: profissional.cpf,
                    dataNasc: profissional.dataNasc,
                    email: profissional.email,
                    endereco:{
                        idEndereco: endereco.idEndereco
                    },
                    nome: profissional.nome,
                    resumoQualificacoes: profissional.resumoQualificacoes,
                    senha: profissional.senha,
                    subcategoria:{
                        idSubcategoria: profissional.subcategoria.idSubcategoria
                    },
                    tipoUsuario:{
                        idTipoUsuario: profissional.tipoUsuario.idTipoUsuario
                    },
                    valorHora: profissional.valorHora
                }
            })
            .then((response)=>{
                alerta.push(`${profissional.nome}, seus dados foram atualizados com sucesso!`);
                console.log("profissional");
                console.log(response);
                getUpdatedPro(profissional);
                this.setState({erros: alerta});
                this.mostrarAlerta("msgAlt", "SUCESSO");

                setTimeout(() => {
                    this.enableFields(false);                 
                }, 1500);
                this.modalLoad();
            })
            .catch((error)=>{
                alerta = [];
                alerta.push("Não foi possível gravar seus dados!");
                alerta.push(error);
                this.setState({erros: alerta})
                this.mostrarAlerta("erroAlt", "ERRO");
                console.error(error);
                this.modalLoad();
            })
            // .onload = this.modalLoad();
        }
        
        axios({
            method: 'PUT',
            url: `${DOMINIO}enderecos/atualizar/id/${endereco.idEndereco}`,
            type: "application/json",
            header: {"token": getToken()},
            timeout: 30000,
            data: {
                idEndereco: endereco.idEndereco,
                bairro: endereco.bairro,
                cep: endereco.cep,
                cidade: {
                    idCidade: endereco.cidade.idCidade
                },
                logradouro: endereco.logradouro,
                numero: null
            }
        })
        .then((response)=>{
            let retorno = response.data;
            console.log("endereco");
            console.log(retorno);
            atualizarPro(profissional);
            
        })
        .catch((error)=>{
            alerta.push("Houve um erro ao tentar gravar seus dados");
            alerta.push(error);
            this.mostrarAlerta("erroAlt", "ERRO");
            console.error(error);
        })
        .onload = this.modalLoad();

    }    

    render(){
        return(
            <Fragment>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <ModalAlertas tipoAlerta={this.state.tipoAlerta} titulo={this.state.tituloAlerta} erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                {/* <MenuLateral/> */}
                <CapaPerfilPro
                    nome={this.state.nomePro}
                    texto1={this.state.servicoPro}
                    texto2={this.state.localPro}
                    texto3="Média Geral: 4.9"
                    texto4={`R$ ${this.state.valorPro}`}
                    foto={this.state.fotoPro}
                />
                <div className="caixa-conteudo-perfil-pro">
                    <div className="container-flex">
                        <form method="POST" name="frm_atualizar_pro" onSubmit={this.setPro}>
                            <div className="caixa-control-perfil flex-center">
                                <BotaoImg 
                                    classBotao="text-editar-perfil flex-center"
                                    idBotao="text-editar-perfil"
                                    typeBotao="button"
                                    clickBotao={this.chamar}
                                    altImg="Editar"
                                    titleImg="Editar"
                                    idImgBotao="img-editar-perfil"
                                    imgBotao={this.state.iconEdit}
                                    />
                                <BotaoImg
                                    classBotao="text-atualizar-perfil flex-center"
                                    idBotao="text-atualizar-perfil"
                                    typeBotao="submit"
                                    // clickBotao={!this.state.atualizar === true ? this.enableFields : ()=>{console.log("ola")}}
                                    altImg="Salvar Dados"
                                    titleImg="Salvar Dados"
                                    idImgBotao="img-atualizar-perfil"
                                    imgBotao={this.state.iconSave}
                                />
                            </div>
                            <DadosPessoaisPro/>
                            <DadosProfissional/>
                        </form>
                    </div>
                    <AvaliacaoPro/>
                </div>
            </Fragment>
        );
    }

}

export default PerfilPro;