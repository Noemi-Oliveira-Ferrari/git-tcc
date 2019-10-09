import React,  {Component} from "react";
import {InputCadastroPro, SelectCategoriaPro, SelectSubcategoriaPro} from './InputCadastroPro';
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
/* 
    tirarErro(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email], select, textarea");
        campos.forEach(campo =>{
            if($(campo).hasClass("erro") ){
                $(campo).on("input", function(){
                    if(campo.value.length > 0){
                        $(campo).removeClass("erro");
                    }
                })
            }
        })

    } */

    teste(){
        console.log("ola-----------------------");
    }
    setCep(event){
        // this.tirarErro();
        this.setState({cep: event.target.value});
        let cepSize = $("#txt-cep").val().length;
        if (cepSize >= 8) {
            this.getEndereco($("#txt-cep").val());
        }
    }
    getEndereco = (cep) =>{
        axios.get(`http://localhost:8080/enderecos/cep/${cep}`)
        .then((response)=>{
            let jsonEndereco = response.data;
            this.setState({logradouro: jsonEndereco.logradouro});
            this.setState({bairro: jsonEndereco.bairro});
            this.setState({cidade: jsonEndereco.cidade.cidade});
            this.setState({uf: jsonEndereco.cidade.microrregiao.uf.uf});
            this.setState({idCidade: jsonEndereco.cidade.idCidade});
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("loading");
    }

    setSenha(event){
        this.setState({senha: event.target.value});
        console.log(this.state.senha);
    }

    setConfirmSenha(event){
        this.setState({confirmSenha: event.target.value});
        console.log($('#txt-confirmar-senha').val());
        console.log($('#txt-confirmar-senha').val().length+"  "+$('#txt-senha').val().length);
        if($('#txt-confirmar-senha').val().length == $('#txt-senha').val().length){
            this.validarSenha();
        }
    }
    
    validarSenha(){
        if($('#txt-senha').val() == $('#txt-confirmar-senha').val()){
            $('#txt-confirmar-senha').html('match');
            $('#txt-confirmar-senha').removeClass("erro");
            $('#txt-senha').removeClass("erro");
            // error = false;
        }else if($('#txt-senha').val() == "" || $('#txt-confirmar-senha').val() == ""){
            console.log(`${$('#txt-senha').val()} vazio ${$('#txt-confirmar-senha').val()}`);
            $('#txt-confirmar-senha').html('mismatch');
            alert('nao era pra cair aqui');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            // error = true
        }else{
            console.log(`${$('#txt-senha').val()} != ${$('#txt-confirmar-senha').val()}`);
            $('#txt-confirmar-senha').html('mismatch');
            // alert('As senha não correspondem!');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            // error = true;
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
                                classInputPro="caixa-nome"
                            />

                            <InputCadastroPro
                                classInputPro="caixa-dataNasc"
                                label="Data de Nascimento:"
                                maxLength="10"
                                id="txt-dataNasc"
                                type="text"
                                name="txt_data_nasc"
                                
                            />
                        
                        </div>
                        <div className="flex-center container-cpfCnpj-email">

                            <InputCadastroPro
                                classInputPro="caixa-cpfCnpj"
                                label="CPF/CNPJ:"
                                maxLength="20"
                                id="txt-cpfCnpj"
                                type="text"
                                name="txt_cpfCnpj"
                            />

                            <InputCadastroPro
                                classInputPro="caixa-email"
                                label="E-mail:"
                                maxLength="150"
                                id="txt-email"
                                type="email"
                                name="txt_email"
                                
                            />

                            
                        </div>
                        <div className="flex-center container-senha">

                            <InputCadastroPro
                                classInputPro="caixa-senha"
                                label="Senha:"
                                maxLength="130"
                                id="txt-senha"
                                type="password"
                                name="txt_senha"
                                onChange={this.setSenha}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-confirmar-senha"
                                label="Confirmar Senha:"
                                maxLength="130"
                                id="txt-confirmar-senha"
                                type="password"
                                name="txt_confirmar_senha"
                                onChange={this.setConfirmSenha}                                
                            />

                        </div>

                        <div className="flex-center container-cep-logradouro">

                            <InputCadastroPro
                                classInputPro="caixa-cep"
                                label="CEP:"
                                maxLength="10"
                                id="txt-cep"
                                type="text"
                                name="txt_cep"
                                onChange={this.setCep}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-logradouro"
                                label="Logradouro:"
                                maxLength="120"
                                id="txt-logradouro"
                                type="text"
                                name="txt_logradouro"
                                valorInput={this.state.logradouro}
                                readOnly
                            />
                            
                        </div>
                        <div className="flex-center container-bairro-cidade-uf">

                            <InputCadastroPro
                                classInputPro="caixa-bairro"
                                label="Bairro:"
                                maxLength="120"
                                id="txt-bairro"
                                type="text"
                                name="txt_bairro"
                                valorInput={this.state.bairro}
                                readOnly
                                
                            />

                            <InputCadastroPro
                                classInputPro="caixa-cidade"
                                label="Cidade:"
                                maxLength="120"
                                id="txt-cidade"
                                type="text"
                                name="txt_cidade"
                                data={this.state.idCidade}
                                valorInput={this.state.cidade}
                                readOnly
                                
                            />

                            <InputCadastroPro
                                classInputPro="caixa-uf"
                                label="UF:"
                                maxLength="2"
                                id="txt-uf"
                                type="text"
                                name="txt_uf"
                                valorInput={this.state.uf}
                                readOnly
                                
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
                                <SelectCategoriaPro
                                    label="Tipos de Serviços:"
                                    id="slt-categoria"
                                    name="slt_categoria"
                                    classSelectPro="caixa-categoria"
                                    onChange={()=>(this.getSubcategorias($("#slt-categoria").find(":selected").val()))}
                                    options={this.state.categorias.map(categoria=>(
                                                <option key={categoria.idCategoria} value={categoria.idCategoria}>
                                                    {categoria.categoria}
                                                </option>
                                            ))}
                                />
                            </div>
                            
                            <div className="flex-center container-subcat">
                                <SelectSubcategoriaPro
                                    label="Serviços:"
                                    id="slt-subcat"
                                    name="slt_subcategoria"
                                    classSelectPro="caixa-subcat"
                                    options={this.state.subcategorias.map(subcategoria=>(
                                                <option key={subcategoria.idSubcategoria} value={subcategoria.idSubategoria}>
                                                    {subcategoria.subcategoria}
                                                </option>
                                            ))}
                                />
                            </div>
                            
                            <div className="flex-center container-valor-hora">
                                <InputCadastroPro
                                    label="Valor/Hora:"
                                    id="txt-valor-hora"
                                    name="txt_valor_hora"
                                    maxLength="10"
                                    type="text"
                                    classInputPro="caixa-valor-hora"
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
        let error = false;
        // console.log($("#txt-confirmar-senha").val());
        
        campos.forEach(campo =>{
            if(campo.value === ""){
                $(campo).addClass("erro");
                error = true;
            }else{
                $(campo).removeClass("erro");
                error = false;
            }
        });

        console.log(`${$("#txt-senha").val()} > ${$("#txt-confirmar-senha").val()}`);

        if($("#txt-senha").val() == $("#txt-confirmar-senha").val()){
            $('#txt-confirmar-senha').html('match');
            $('#txt-confirmar-senha').removeClass("erro");
            $('#txt-senha').removeClass("erro");
            error = false;
        }else if($("#txt-senha").val() == "" || $("#txt-confirmar-senha").val() == ""){
            console.log(`${$("#txt-senha").val()} vazio ${$("#txt-confirmar-senha").val()}`);
            $('#txt-confirmar-senha').html('mismatch');
            alert('As senha não correspondem!');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            error = true
        }else{
            console.log(`${$("#txt-senha").val()} != ${$("#txt-confirmar-senha").val()}`);
            $('#txt-confirmar-senha').html('mismatch');
            alert('As senha não correspondem!');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            error = true;
        }
        
        return error;
    }

    realizarCadastro(event){
        event.preventDefault();
        console.clear();
        console.log("Enviando dados ao banco...");
        // $('#txt-confirmar-senha').get(0).setCustomValidity('As senha não correspondem!');
        
        // if($("#txt-senha").val() == $("#txt-confirmar-senha").val()){
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
    
            if(this.validarCampos() && $("#chk-termos").is(":checked")){
    
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
                console.log($("#chk-termos").is(":checked"));
                // sessionStorage.setItem("endereco", JSON.stringify(endereco));
                // sessionStorage.setItem("profissional", JSON.stringify(profissional));
                // browserHistory.push("/profissional/cadastro/confirmacao");
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