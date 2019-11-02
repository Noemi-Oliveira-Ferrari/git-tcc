import React,  {Component, Fragment} from "react";
import {Inputs, InputNumber} from './FormElements';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import { DOMINIO } from '../global';
import {ModalLoadConst, ModalAlertas} from './ModaisLoad';
import {browserHistory} from 'react-router';
import { validarConfirmacaoSenha, moveToError, generateHash, withError,
         withoutError, validarCpfCliente, validarEmail,
         validarSenha, validarString, validarVazios, retirarSimbolos,
         formataData, validarIdade} from '../js/validar';




export class DadosPessoaisCliente extends Component{

    constructor(){
        super();

        this.state = {
            nome: "", dataNasc: "", cpf: "",
            email: "", senha: "", confirmSenha: "",
            loading: false,
            showModalErro: false,
            erros: [],
            
            cep: "", logradouro: "", numero:"", idCidade: "",
            bairro: "", cidade: "", uf: "",
            cliente: [], endereco: []
        }
        this.modalLoad = this.modalLoad.bind(this);
        this.ModalAlertas = this.ModalAlertas.bind(this);
        this.noConnection = this.noConnection.bind(this);

        this.setNome = this.setNome.bind(this);
        this.setData = this.setData.bind(this);
        this.setCpf = this.setCpf.bind(this);
        this.setEmail = this.setEmail.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmSenha = this.setConfirmSenha.bind(this);
        this.setCep = this.setCep.bind(this);
        this.setNumero = this.setNumero.bind(this);

        this.getCpf = this.getCpf.bind(this);
        this.getEmail = this.getEmail.bind(this);
    }

    ModalAlertas = () =>{
        this.setState({showModalErro: !this.state.showModalErro});
    }

    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
    }
    
    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        setTimeout(()=>{this.ModalAlertas();}, 500);
    }
    

    componentDidMount(){

        let cliente = JSON.parse(sessionStorage.getItem("cliente"));
        let endereco = JSON.parse(sessionStorage.getItem("endereco"));

        if(cliente !== null){
            this.setState({nome: cliente.nome});
            this.setState({dataNasc: formataData(cliente.dataNasc, "-", "/")});
            this.setState({cpf: cliente.cpf});
            this.setState({email: cliente.email});
        }        
        if(endereco !== null){
            this.setState({endereco: endereco});
            this.setState({cep: endereco.cep});
            this.getEndereco(endereco.cep);
            this.setState({numero: endereco.numero});
            this.setState({idCidade: endereco.cidade.idCidade});
        }
    } 

    setNome(event){
        this.setState({nome: event.target.value});
        validarString(event.target);
    }

    setData(event){
        this.setState({dataNasc: event.target.value});
        let value = event.target.value;
        // console.log(value.
        if(!value.includes("_") && validarIdade(event.target)){
            withoutError($("#txt-dataNasc"))
        }else{
            withError($("#txt-dataNasc"))    
        }
    }

    setCpf(event){
        this.setState({cpf: event.target.value});
        let cpf = event.target.value;
        validarCpfCliente(cpf);
        
        if(!cpf.includes("_") && cpf.length === 14){
            this.getCpf(retirarSimbolos(cpf));
        }
    }
    
    getCpf(cpf){
        let erros = [];
        axios({
            method:"GET",
            url: `${DOMINIO}clientes/cpf/${cpf}`,
            timeout: 30000
        })
        .then((response)=>{
            let jsonCliente = response.data;
            console.log(jsonCliente);
            if(jsonCliente !== null && jsonCliente !== ''){
                withError($("#txt-cpf"));
                erros.push(`CPF ${cpf} ja cadastrado`);
                this.setState({erros: erros});
                setTimeout(()=>{this.ModalAlertas();}, 500);
                this.setState({cpf: ""});
            }
            setTimeout(()=>{this.modalLoad();}, 200);
        })
        .catch((error)=>{
            this.noConnection();
            console.log(error);
        })
        .onload = this.modalLoad();
    }

    setNumero(event){
        this.setState({numero: event.target.value});
    }

    setEmail(event){
        this.setState({email: event.target.value});
        // this.getEmail(event.target.value);
        validarEmail(event.target);
    }

    getEmail(event){
        let erros = [];
        this.setState({email: event.target.value});
        let email = event.target.value;
        console.log(email);
        if(email.length > 5){

            axios({
                method: "GET",
                url: `${DOMINIO}clientes/email/${email}`,
                timeout: 30000
            })
            .then((response)=>{
                let jsonCliente = response.data;
                console.log(jsonCliente);
                if(jsonCliente !== null && jsonCliente !== ''){
                    withError($("#txt-email"));
                    erros.push(`E-mail ${email} ja cadastrado`);
                    this.setState({erros: erros});
                    setTimeout(()=>{this.ModalAlertas();}, 500);
                    this.setState({email: ""});
                    setTimeout(() => {
                        $("#txt-email").focus();
                    }, 200);                
                }
                setTimeout(()=>{this.modalLoad();}, 200);
            })
            .catch((error)=>{
                this.noConnection();
                console.log(error);
            })
            .onload = this.modalLoad();
        }   
    }

    setSenha(event){
        this.setState({senha: event.target.value});
        let senha = event.target.value;
        validarSenha(senha);
    }

    setConfirmSenha(event){
        this.setState({confirmSenha: event.target.value});
        let confirmSenha = event.target;
        validarConfirmacaoSenha($('#txt-senha').get(0), confirmSenha);
    }
    
    setCep(event){
        this.setState({cep: event.target.value});
        let cepSize = event.target.value.length;
        if (cepSize > 8) {
            this.getEndereco(event.target.value);
        }
    }

    getEndereco = (cep) =>{
        let erros = [];
        // axios.get(`${DOMINIO}enderecos/cep/${cep}`)
        axios({
            method: "GET",
            url: `https://viacep.com.br/ws/${cep}/json/`,
            type: "application/json",
            timeout: 30000,
        })
        .then((response)=>{
            let jsonEndereco = response.data;
            let cepError = jsonEndereco.erro;
            if(jsonEndereco === null || jsonEndereco === "" || cepError === true){
                this.setState({logradouro: ""});
                this.setState({bairro: ""});
                this.setState({cidade: ""});
                this.setState({uf: ""});
                this.setState({idCidade: ""});
                
                withError($('#txt-cep'));
                withError($('#txt-logradouro'));
                withError($('#txt-cidade'));
                withError($('#txt-bairro'));
                withError($('#txt-uf'));
                this.modalLoad();
                erros.push(`O CEP ${cep} é invalido`);
                this.setState({erros: erros});
                setTimeout(()=>{this.ModalAlertas();}, 500);
            }else if(cepError !== true){
                this.setState({logradouro: jsonEndereco.logradouro});
                this.setState({bairro: jsonEndereco.bairro});
                this.setState({cidade: jsonEndereco.localidade});
                this.setState({uf: jsonEndereco.uf});
                this.setState({idCidade: jsonEndereco.ibge});

                withoutError($('#txt-cep'));
                withoutError($('#txt-logradouro'));
                withoutError($('#txt-cidade'));
                withoutError($('#txt-bairro'));
                withoutError($('#txt-uf'));
                setTimeout(()=>{this.modalLoad();}, 200);
            }
        })
        .catch((error)=>{
            this.noConnection();

            this.modalLoad();
        })
        .onload = this.modalLoad();
    }
        

    render(){
        return(
            <Fragment>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <ModalAlertas tipoAlerta="erroAlt" titulo="ERRO NO CADASTRO" erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <div className="flex-center">
                    <div className="card-formulario-pessoal">
                        <div className="caixa-title-card">
                            <h3 className="title-card-pro">Dados Pessoais</h3>
                        </div>
                        
                        <div className="float campos-dados">
                            <div className="flex-center container-nome-dataNasc">

                                <Inputs
                                    label="Nome:"
                                    id="txt-nome"
                                    name="txt_nome"
                                    maxLength="100"
                                    type="text"
                                    classDivInput="caixa-nome"
                                    classInput="form-control form-input"
                                    onChange={this.setNome}
                                    valueInput={this.state.nome || ""}
                                />

                                <InputNumber
                                    classDivInput="caixa-dataNasc"
                                    label="Data de Nascimento:"
                                    id="txt-dataNasc"
                                    type="text"
                                    name="txt_data_nasc"
                                    mascara="##/##/####"
                                    classInput="form-control form-input"
                                    onChange={this.setData}
                                    valueInput={this.state.dataNasc || ""}
                                />
                            
                            </div>
                            <div className="flex-center container-cpf-email">

                                <InputNumber
                                    classDivInput="caixa-cpf"
                                    label="CPF:"
                                    id="txt-cpf"
                                    type="text"
                                    name="txt_cpf"
                                    classInput="form-control form-input"
                                    onChange={this.setCpf}
                                    mascara="###.###.###-##"
                                    valueInput={this.state.cpf || ""}
                                />

                                <Inputs
                                    classDivInput="caixa-email"
                                    label="E-mail:"
                                    maxLength="150"
                                    id="txt-email"
                                    type="email"
                                    name="txt_email"
                                    onChange={this.setEmail}
                                    onBlur={this.getEmail}
                                    classInput="form-control form-input"
                                    valueInput={this.state.email || ""}
                                />

                                
                            </div>
                            <div className="flex-center container-senha">

                                <Inputs
                                    classDivInput="caixa-senha"
                                    label="Senha:"
                                    maxLength="130"
                                    id="txt-senha"
                                    type="password"
                                    name="txt_senha"
                                    onChange={this.setSenha}
                                    classInput="form-control form-input"
                                />

                                <Inputs
                                    classDivInput="caixa-confirmar-senha"
                                    label="Confirmar Senha:"
                                    maxLength="130"
                                    id="txt-confirmar-senha"
                                    type="password"
                                    name="txt_confirmar_senha"
                                    onChange={this.setConfirmSenha}                           
                                    classInput="form-control form-input"
                                />

                            </div>

                            <div className="flex-center container-cep-logradouro">

                                <Inputs
                                    classDivInput="caixa-cep"
                                    label="CEP:"
                                    id="txt-cep"
                                    type="text"
                                    name="txt_cep"
                                    onChange={this.setCep}
                                    classInput="form-control form-input"
                                    mascara="99999-999"
                                    valueInput={this.state.cep || ""}
                                />

                                <Inputs
                                    classDivInput="caixa-logradouro"
                                    label="Logradouro:"
                                    maxLength="120"
                                    id="txt-logradouro"
                                    type="text"
                                    name="txt_logradouro"
                                    valueInput={this.state.logradouro || ""}
                                    readOnly
                                    classInput="form-control form-input"
                                />

                                <InputNumber
                                    classDivInput="caixa-numero"
                                    label="Número:"
                                    id="txt-numero"
                                    type="text"
                                    name="txt_numero"
                                    classInput="form-control form-input"
                                    maxLengthInput="10"
                                    separadorMilhar="."
                                    separadorDecimal=","
                                    qtdDecimal="1"
                                    permitirNegativo="false"
                                    onChange={this.setNumero}
                                    valueInput={this.state.numero || ""}
                                />
                                
                            </div>
                            <div className="flex-center container-bairro-cidade-uf">

                                <Inputs
                                    classDivInput="caixa-bairro"
                                    label="Bairro:"
                                    maxLength="120"
                                    id="txt-bairro"
                                    type="text"
                                    name="txt_bairro"
                                    valueInput={this.state.bairro || ""}
                                    disabled
                                    
                                    classInput="form-control form-input"
                                />

                                <Inputs
                                    classDivInput="caixa-cidade"
                                    label="Cidade:"
                                    maxLength="120"
                                    id="txt-cidade"
                                    type="text"
                                    name="txt_cidade"
                                    data={this.state.idCidade || ""}
                                    valueInput={this.state.cidade || ""}
                                    readOnly
                                    
                                    classInput="form-control form-input"
                                />
        
                                <Inputs
                                    classDivInput="caixa-uf"
                                    label="UF:"
                                    maxLength="2"
                                    id="txt-uf"
                                    type="text"
                                    name="txt_uf"
                                    valueInput={this.state.uf || ""}
                                    readOnly
                                    
                                    classInput="form-control form-input"
                                />
                                
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}



export default class FormularioCliente extends Component{
    
    constructor(){
        super();
        this.state = {
            erros: [],
            showModalErro: false
        }
        this.ModalAlertas = this.ModalAlertas.bind(this);
        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);
    }

    ModalAlertas = () =>{
        if(!this.state.showModalErro){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({showModalErro: !this.state.showModalErro});
    }


    validarCampos(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email]");
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

        if(!validarCpfCliente($('#txt-cpf').val())){
            semErro = false;
            erros.push("CPF Inválido\n");
            console.log("validarCpfCliente "+semErro);
        }

        if(!validarEmail($('#txt-email').get(0))){
            semErro = false;
            erros.push("E-mail digitado não é válido\n");
            console.log("validarEmail "+semErro);
        }

        if(!validarSenha($('#txt-senha').val())){
            semErro = false;
            erros.push("A senha deve ter letras maiúsculas e minúsculas, números, símbolos(@#$...), ter no mínimo 8 caractéres e não pode conter espaços");
            console.log("validarSenha "+semErro);
        }

        if(!validarConfirmacaoSenha($('#txt-senha').get(0), $('#txt-confirmar-senha').get(0))){
            semErro = false;
            erros.push("As senhas não correspondem!\n");
            console.log("validarConfirmacaoSenha "+semErro);
        }
        this.setState({erros: erros});
        erros = [];
        return semErro;
    }

    realizarCadastro(event){
        event.preventDefault();
        // console.clear();
        // console.log("Enviando dados ao banco...");
        let cpf = $("#txt-cpf").val().replace(/[.-]/g, "");
            cpf = retirarSimbolos(cpf);
        
        // console.log("validarCampos "+this.validarCampos());
        if(this.validarCampos() && $("#chk-termos").is(":checked")){
            // console.log("validarCampos TRUE"+this.validarCampos());
            
            let endereco = {
                cep: retirarSimbolos($("#txt-cep").val()),
                logradouro: $("#txt-logradouro").val(),
                bairro: $("#txt-bairro").val(),
                numero: $("#txt-numero").val(),
                cidade: {
                    idCidade: $("#txt-cidade").attr("data-idCidade")
                }
            };
            let cliente = {
                nome: $("#txt-nome").val(),
                dataNasc: formataData($("#txt-dataNasc").val(), "/", "-"),
                cpf: cpf,
                email: $("#txt-email").val(),
                senha: generateHash($("#txt-senha").val()),
                tipoUsuario: {
                    idTipoUsuario: 2
                },
                endereco: {
                    idEndereco: null
                }
            };
            sessionStorage.setItem("endereco", JSON.stringify(endereco));
            sessionStorage.setItem("cliente", JSON.stringify(cliente));
            browserHistory.push("/cadastro/confirmacao");
        }else{
            setTimeout(() => {
                this.ModalAlertas();
            }, 200);
            moveToError();
        }
    }

    render(){
        return(
            <Fragment>
                <ModalAlertas tipoAlerta="erroAlt" titulo="ERRO NO CADASTRO" erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <form className="form-cliente" name="form_cliente" method="GET" onSubmit={this.realizarCadastro}>
                    <DadosPessoaisCliente/>
                    <TermosDeUso link="/cadastro/confirmacao"/>
                </form>
            </Fragment>
        )
    }
    
}