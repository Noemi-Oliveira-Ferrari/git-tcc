import React,  {Component} from "react";
import {Inputs, Selects, InputNumber} from './FormElements';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';
import { validarConfirmacaoSenha, moveToError, generateHash, withError,
         withoutError, validarCnpj, validarCpfCliente, validarEmail,
         validarSenha, validarString, validarVazios, retirarSimbolos,
         formataData, limpaValor} from '../js/validar';

class DadosPessoaisCliente extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "",
            email: "", senha: "", confirmSenha: "",
            
            cep: "", logradouro: "", numero:"", idCidade: "",
            bairro: "", cidade: "", uf: ""
        }
           
        this.setNome = this.setNome.bind(this);
        this.setData = this.setData.bind(this);
        this.setCpf = this.setCpf.bind(this);
        this.setEmail = this.setEmail.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmSenha = this.setConfirmSenha.bind(this);
        this.setCep = this.setCep.bind(this);
    }

    componentDidMount(){
        let usuario;
        let profissional;
        let cliente;
    }

    setNome(event){
        this.setState({nome: event.target.value});
        validarString(event.target);
    }

    setData(event){
        this.setState({dataNasc: event.target.value});
        if(retirarSimbolos(event.target.value).length === 8){
            console.log(formataData(event.target.value));
        }
    }

    setCpf(event){
        this.setState({cpf: event.target.value});
        let cpf = event.target.value;
        validarCpfCliente(cpf);
        console.log(cpf);
    }
    
    setEmail(event){
        this.setState({email: event.target.value});
        validarEmail(event.target);
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
        // axios.get(`http://3.220.68.195:8080/enderecos/cep/${cep}`)
        axios.get(`http://localhost:8080/enderecos/cep/${cep}`)
        .then((response)=>{
            let jsonEndereco = response.data;
            // console.clear();
            console.log(response);
            if(jsonEndereco.cep != null){
                this.setState({logradouro: jsonEndereco.logradouro});
                this.setState({bairro: jsonEndereco.bairro});
                this.setState({cidade: jsonEndereco.cidade.cidade});
                this.setState({uf: jsonEndereco.cidade.microrregiao.uf.uf});
                this.setState({idCidade: jsonEndereco.cidade.idCidade});

                $('#txt-cep').removeClass("erro");
                $('#txt-logradouro').removeClass("erro");
                $('#txt-cidade').removeClass("erro");
                $('#txt-bairro').removeClass("erro");
                $('#txt-uf').removeClass("erro");
            }
        })
        .catch((error)=>{
            this.setState({logradouro: "CEP INVÁLIDO"});
            this.setState({bairro: ""});
            this.setState({cidade: ""});
            this.setState({uf: ""});
            this.setState({idCidade: ""});

            $('#txt-cep').addClass("erro");
            $('#txt-logradouro').addClass("erro");
            $('#txt-cidade').addClass("erro");
            $('#txt-bairro').addClass("erro");
            $('#txt-uf').addClass("erro");
        })
        .onload = console.log("loading");
    }
    
    

    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-pessoal">
                    <div className="caixa-title-card">
                        <div className="title-card-pro">Dados Pessoais</div>
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
                            />

                            <Inputs
                                classDivInput="caixa-email"
                                label="E-mail:"
                                maxLength="150"
                                id="txt-email"
                                type="email"
                                name="txt_email"
                                onChange={this.setEmail}
                                classInput="form-control form-input"
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
                                maxLength="10"
                                id="txt-numero"
                                type="text"
                                name="txt_numero"
                                valueInput={this.state.numero || ""}
                                classInput="form-control form-input"
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
                                data={this.state.idCidade}
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

        );
    }
}



export default class FormularioCliente extends Component{
    
    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);  
    }



    validarCampos(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email]");
        let semErro = true;
        

        if(!validarVazios(campos)){
            semErro = false;
            console.log("validarVazios "+semErro);
        }

        if(!validarString($('#txt-nome').get(0))){
            semErro = false;
            console.log("validarString nome "+semErro);
        }

        if(!validarCpfCliente($('#txt-cpf').val())){
            semErro = false;
            console.log("validarCpfCliente "+semErro);
        }

        if(!validarEmail($('#txt-email').get(0))){
            semErro = false;
            console.log("validarEmail "+semErro);
        }

        if(!validarSenha($('#txt-senha').val())){
            semErro = false;
            console.log("validarSenha "+semErro);
        }

        if(!validarConfirmacaoSenha($('#txt-senha').get(0), $('#txt-confirmar-senha').get(0))){
            semErro = false;
            console.log("validarConfirmacaoSenha "+semErro);
        }
        
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
                dataNasc: formataData($("#txt-dataNasc").val()),
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
            moveToError();
        }
    }

    render(){
        return(
            <form className="form-cliente" name="form_cliente" method="GET" onSubmit={this.realizarCadastro}>
                <DadosPessoaisCliente/>
                <TermosDeUso link="/cadastro/confirmacao"/>
            </form>
        )
    }
    
}