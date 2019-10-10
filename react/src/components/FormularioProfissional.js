import React,  {Component} from "react";
import {InputCadastroPro, Selects} from './InputCadastroPro';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';


class DadosPessoaisPro extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "", cnpj: "",
            email: "", senha: "", confirmSenha: "",
            
            cep: "", logradouro: "", idCidade: "",
            bairro: "", cidade: "", uf: "", subcategoria: "",
            enderecoPronto: false
        }
        this.setCep = this.setCep.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmSenha = this.setConfirmSenha.bind(this);
        this.validarSenha = this.validarSenha.bind(this);
        this.teste = this.teste.bind(this);
    }
    teste(){
        console.log("ola-----------------------");
    }
    setCep(event){
        // this.tirarErro();
        this.setState({cep: event.target.value});
        let cepSize = $("#txt-cep").val().length;
        if (cepSize > 8) {
            this.getEndereco($("#txt-cep").val());
        }
    }
    getEndereco = (cep) =>{
        axios.get(`http://localhost:8080/enderecos/cep/${cep}`)
        .then((response)=>{
            let jsonEndereco = response.data;
            console.clear();
            console.log(";;;;");
            console.log(jsonEndereco.cep);
            if(jsonEndereco.cep != null){
                this.setState({logradouro: jsonEndereco.logradouro});
                this.setState({bairro: jsonEndereco.bairro});
                this.setState({cidade: jsonEndereco.cidade.cidade});
                this.setState({uf: jsonEndereco.cidade.microrregiao.uf.uf});
                this.setState({idCidade: jsonEndereco.cidade.idCidade});
            }else{
                this.setState({logradouro: "CEP INVÁLIDO"});
                this.setState({bairro: ""});
                this.setState({cidade: ""});
                this.setState({uf: ""});
                this.setState({idCidade: ""});
            }
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("loading");
    }

    setSenha(event){
        this.setState({senha: event.target.value});
        let senha = event.target.value;
        if(senha.length >= 8){
            $('#txt-confirmar-senha').attr("disabled", false);
        }else{
            $('#txt-confirmar-senha').attr("disabled", true);
        }
    }

    setConfirmSenha(event){
        this.setState({confirmSenha: event.target.value});
        let confirmSenha = event.target.value;
        console.log(confirmSenha.length+" c s "+this.state.senha.length);
        this.validarSenha(this.state.senha, confirmSenha);
    }
    
    validarSenha(senha, confirmSenha){
        if(senha == confirmSenha){
            $('#txt-confirmar-senha').html('match');
            $('#txt-confirmar-senha').removeClass("erro");
            $('#txt-senha').removeClass("erro");
        }else if(senha == "" || confirmSenha == ""){
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
        }else{
            $('#txt-confirmar-senha').html('mismatch');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
        }
    }

    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-pessoal">
                    <h3 className="title-card">Dados Pessoais</h3>
                    
                    <div className="float campos-dados">
                        <div className="flex-center container-nome-dataNasc">

                            <InputCadastroPro
                                label="Nome:"
                                id="txt-nome"
                                name="txt_nome"
                                maxLength="100"
                                type="text"
                                classDivInputPro="caixa-nome"
                                classInput="form-control form-input"
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-dataNasc"
                                label="Data de Nascimento:"
                                id="txt-dataNasc"
                                type="text"
                                name="txt_data_nasc"
                                mascara="99/99/9999"
                                classInput="form-control form-input"
                            />
                        
                        </div>
                        <div className="flex-center container-cpfCnpj-email">

                            <InputCadastroPro
                                classDivInputPro="caixa-cpfCnpj"
                                label="CPF/CNPJ:"
                                id="txt-cpfCnpj"
                                type="text"
                                name="txt_cpfCnpj"
                                classInput="form-control form-input"
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-email"
                                label="E-mail:"
                                maxLength="150"
                                id="txt-email"
                                type="email"
                                name="txt_email"
                                
                                classInput="form-control form-input"
                            />

                            
                        </div>
                        <div className="flex-center container-senha">

                            <InputCadastroPro
                                classDivInputPro="caixa-senha"
                                label="Senha:"
                                maxLength="130"
                                id="txt-senha"
                                type="password"
                                name="txt_senha"
                                onChange={this.setSenha}
                                classInput="form-control form-input"
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-confirmar-senha"
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

                            <InputCadastroPro
                                classDivInputPro="caixa-cep"
                                label="CEP:"
                                id="txt-cep"
                                type="text"
                                name="txt_cep"
                                onChange={this.setCep}
                                classInput="form-control form-input"
                                mascara="99999-999"
                                valueInput={this.state.cep || ""}
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-logradouro"
                                label="Logradouro:"
                                maxLength="120"
                                id="txt-logradouro"
                                type="text"
                                name="txt_logradouro"
                                valueInput={this.state.logradouro}
                                readOnly
                                classInput="form-control form-input"
                            />
                            
                        </div>
                        <div className="flex-center container-bairro-cidade-uf">

                            <InputCadastroPro
                                classDivInputPro="caixa-bairro"
                                label="Bairro:"
                                maxLength="120"
                                id="txt-bairro"
                                type="text"
                                name="txt_bairro"
                                valueInput={this.state.bairro}
                                disabled
                                
                                classInput="form-control form-input"
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-cidade"
                                label="Cidade:"
                                maxLength="120"
                                id="txt-cidade"
                                type="text"
                                name="txt_cidade"
                                data={this.state.idCidade}
                                valueInput={this.state.cidade}
                                readOnly
                                
                                classInput="form-control form-input"
                            />

                            <InputCadastroPro
                                classDivInputPro="caixa-uf"
                                label="UF:"
                                maxLength="2"
                                id="txt-uf"
                                type="text"
                                name="txt_uf"
                                valueInput={this.state.uf}
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

class DadosProfissional extends Component{
    
    constructor(){
        super();        
        // this.popularCategorias = this.popularCategorias.bind(this);
        this.state = {
           categorias: [],
           subcategorias: []        
        }
        this.getCategorias = this.getCategorias.bind(this);
        this.getSubcategorias = this.getSubcategorias.bind(this);
    }

    componentDidMount(){
        this.getCategorias(this.getSubcategorias());
    }

    getCategorias(){
        axios.get(`http://localhost:8080/categorias`)
        .then((response)=>{
            let jsonCategorias = response.data;
            this.setState({categorias: jsonCategorias});
            this.getSubcategorias(jsonCategorias[0].idCategoria);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload =  console.log("CARREGANDO CATS...")
    }

    getSubcategorias(idCategoria){

        if(idCategoria == null || idCategoria == ""){
            idCategoria = 1;
        }

        axios.get(`http://localhost:8080/subcategorias/categoria/${idCategoria}`)
        .then((response)=>{
            let jsonSubcategorias = response.data;
            this.setState({subcategorias: jsonSubcategorias});
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("CARREGANDO SUBS...")
    }

    
    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-servico">
                    <h3 className="title-card">Dados Profissionais</h3>
                    <div className="flex-center campos-servicos">
                        <div className="container-servico-pro">
                            <div className="flex-center container-categoria">
                                <Selects
                                    labelSelect="Tipos de Serviços:"
                                    idSelect="slt-categoria"
                                    nameSelect="slt_categoria"
                                    classDivSelect="caixa-categoria"
                                    onChangeSelect={()=>(this.getSubcategorias($("#slt-categoria").find(":selected").val()))}
                                    optionsSelect={this.state.categorias.map(categoria=>(
                                                <option key={categoria.idCategoria} value={categoria.idCategoria}>
                                                    {categoria.categoria}
                                                </option>
                                            ))}
                                    firstOption="Selecione um Tipo de serviço"
                                />
                            </div>
                            
                            <div className="flex-center container-subcat">
                                <Selects
                                    labelSelect="Serviços:"
                                    idSelect="slt-subcat"
                                    nameSelect="slt_subcategoria"
                                    classDivSelect="caixa-subcat"
                                    optionsSelect={this.state.subcategorias.map(subcategoria=>(
                                                <option key={subcategoria.idSubcategoria} value={subcategoria.idSubategoria}>
                                                    {subcategoria.subcategoria}
                                                </option>
                                            ))}
                                    firstOption="Selecione um serviço"
                                />
                            </div>
                            
                            <div className="flex-center container-valor-hora">
                                <InputCadastroPro
                                    label="Valor/Hora:"
                                    id="txt-valor-hora"
                                    classInput="form-control form-input"
                                    name="txt_valor_hora"
                                    type="text"
                                    classDivInputPro="caixa-valor-hora"
                                />
                            </div>
                        </div>
                        <div className="container-servico-pro">
                            <div className="container-qualificacoes">
                                <div className="float caixa-qualificacoes">
                                    <label className="form-label">Resumo de Qualificações:</label>
                                    <textarea id="txt-qualificacoes" className="txt-qualificacoes form-control form-input-pro"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default class FormularioProfissional extends Component{
    
    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);  
    }

    validarCampos(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email], select, textarea");
        console.log(campos);
        let notError = false;
        // console.log($("#txt-confirmar-senha").val());
        
        campos.forEach(campo =>{
            if(campo.value === ""){
                $(campo).addClass("erro");
                notError = false;
            }else{
                $(campo).removeClass("erro");
                notError = true;
            }
        });

        if($('#txt-senha').val() == $('#txt-confirmar-senha').val() &&
        ($('#txt-senha').val() != "" || $('#txt-confirmar-senha').val() != "")){
            $('#txt-confirmar-senha').html('match');
            $('#txt-confirmar-senha').removeClass("erro");
            $('#txt-senha').removeClass("erro");
            notError = true;
        }else if($('#txt-senha').val() == "" || $('#txt-confirmar-senha').val() == ""){
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            notError = false;
        }else{
            $('#txt-confirmar-senha').html('mismatch');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            notError = false;
        }

        return notError;
    }

    realizarCadastro(event){
        event.preventDefault();
        console.clear();
        console.log("Enviando dados ao banco...");
            let cpfCnpj = $("#txt-cpfCnpj").val().replace(/[.-]/g, "");
            let cpf;
            let cnpj;
            if(cpfCnpj.length <= 11){
                cpf = cpfCnpj;
                cnpj = null;
            }else{
                cnpj = cpfCnpj;
                cpf = null;
            }        
            
            console.log("validarCampos"+this.validarCampos());
            if(this.validarCampos() && $("#chk-termos").is(":checked")){
                console.log("validarCampos TRUE"+this.validarCampos());
                
                let endereco = {
                    cep: $("#txt-cep").val(),
                    logradouro: $("#txt-logradouro").val(),
                    bairro: $("#txt-bairro").val(),
                    cidade: {
                        idCidade: $("#txt-cidade").attr("data-idCidade")
                    }
                };
                let profissional = {
                    nome: $("#txt-nome").val(),
                    dataNasc: $("#txt-dataNasc").val(),
                    cpf: cpf,
                    cnpj: cnpj,
                    email: $("#txt-cep").val(),
                    senha: $("#txt-cep").val(),
                    endereco: {
                        idEndereco: null
                    },
                    subcategoria: {
                        idSubcategoria: $("#slt-subcat").val(),
                    },
                    valorHora: $("#txt-valor-hora").val(),
                    resumoQualificacoes: $("#txt-qualificacoes").val()
                };
                sessionStorage.setItem("endereco", JSON.stringify(endereco));
                sessionStorage.setItem("profissional", JSON.stringify(profissional));
                browserHistory.push("/profissional/cadastro/confirmacao");
            }
            // console.log($("#chk-termos").is(":checked"));
    
        // }else{
        //     console.log("ERRO");
        //     console.log(`${$("#txt-senha").val()} e ${$("#txt-confirmar-senha").val()}`);
        //     $('#txt-confirmar-senha').html('mismatch');
        //     $('#txt-confirmar-senha').get(0).setCustomValidity('As senha não correspondem!');
            
        // }

    }

    render(){
        return(
            <form className="form-pro" name="form_profissional" method="GET" onSubmit={this.realizarCadastro}>
                <DadosPessoaisPro/>
                <DadosProfissional/>
                <TermosDeUso link="/profissional/cadastro/confirmacao"/>
            </form>
        )
    }
    
}