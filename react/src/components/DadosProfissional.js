import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import {InputCadastroPro, SelectCategoriaPro, SelectSubcategoriaPro} from './InputCadastroPro';
import $ from 'jquery';
import axios from 'axios';


export class DadosPessoaisPro extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "", cnpj: "",
            email: "", senha: "", cep: "", logradouro: "",
            bairro: "", cidade: "", uf: "", subcategoria: ""
        }
        this.setCep = this.setCep.bind(this);
        this.popularCampos = this.popularCampos.bind(this);
    }

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

    }


    getEndereco(cep){

        axios.get(`http://localhost:8080/enderecos/cep/${cep}`)
        .then((response)=>{
            console.log(response.data);
            let jsonEndereco = response.data;
            this.popularCampos(jsonEndereco);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("loading");
    }

    popularCampos(jsonEndereco){
        $("#txt-logradouro").val(jsonEndereco.logradouro);
        $("#txt-bairro").val(jsonEndereco.bairro);
        $("#txt-cidade").val(jsonEndereco.cidade.cidade);
        $("#txt-cidade").attr("data-idCidade", jsonEndereco.cidade.idCidade);
        $("#txt-uf").val(jsonEndereco.cidade.microrregiao.uf.uf);
    }

    setCep(event){
        this.tirarErro();
        this.setState({cep: event.target.value});
        let cepSize = $("#txt-cep").val().length;
        if (cepSize >= 8) {
            this.getEndereco($("#txt-cep").val());
        }
    }

    setSubcategoria(event){
        this.setState({subcategoria: event.target.value});
    }

    setSenha(event){
        this.setState({senha: event.target.value});
        console.log(this.state.senha);
        $('#txt-confirmar-senha').get(0).setCustomValidity('As senha não correspondem!');
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
                                onChange={this.tirarErro}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-dataNasc"
                                label="Data de Nascimento:"
                                maxLength="10"
                                id="txt-dataNasc"
                                type="text"
                                name="txt_data_nasc"
                                onChange={this.tirarErro}
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
                                onChange={this.tirarErro}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-email"
                                label="E-mail:"
                                maxLength="150"
                                id="txt-email"
                                type="email"
                                name="txt_email"
                                onChange={this.tirarErro}
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
                                onChange={this.tirarErro}
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
                                onChange={this.tirarErro}
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
                                onChange={this.tirarErro}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-cidade"
                                label="Cidade:"
                                maxLength="120"
                                id="txt-cidade"
                                type="text"
                                name="txt_cidade"
                                data=""
                                onChange={this.tirarErro}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-uf"
                                label="UF:"
                                maxLength="2"
                                id="txt-uf"
                                type="text"
                                name="txt_uf"
                                onChange={this.tirarErro}
                            />
                            
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export class DadosProfissional extends Component{
    
    constructor(){
        super();        
        // this.popularCategorias = this.popularCategorias.bind(this);
        this.state = {
           categorias: [],
           subcategorias: [],
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
            for(let i = 0; i < jsonCategorias.length; i++){
                // console.log(jsonCategorias[i]);
                $("#slt-categoria").append(`<option value="${jsonCategorias[i].idCategoria}">${jsonCategorias[i].categoria}</option>`);
            }
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = $("#slt-categoria").append("<option id='load-cat' value=''>Carregando...</option>");
    }
    getSubcategorias(idCategoria){
        // tirarErro();
        console.log("=-=-=> "+idCategoria);
        if(idCategoria == null || idCategoria == ""){
            idCategoria = 1;
        }

        axios.get(`http://localhost:8080/subcategorias/categoria/${idCategoria}`)
        .then((response)=>{
            let jsonSubcategorias = response.data;
            this.setState({subategorias: jsonSubcategorias});

            $("#load-cat").remove();
            $("#slt-categoria").append("<option value=''>Selecione um Tipo Serviço</option>");
            
            $("#load-subat").remove();
            $("#slt-subcat").empty();
            $("#slt-subcat").append("<option value=''>Selecione um Serviço</option>");
            for(let i = 0; i < jsonSubcategorias.length; i++){
                console.log(jsonSubcategorias[i]);
                $("#slt-subcat").append(`<option value="${jsonSubcategorias[i].idSubcategoria}">${jsonSubcategorias[i].subcategoria}</option>`);
            }
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = $("#slt-subcat").append("<option id='load-subat' value=''>Carregando...</option>");
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
                                />
                            </div>
                            
                            <div className="flex-center container-subcat">
                                <SelectSubcategoriaPro
                                    label="Serviços:"
                                    id="slt-subcat"
                                    name="slt_subcategoria"
                                    classSelectPro="caixa-subcat"
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
                                    <textarea id="txt-qualificacoes" required className="txt-qualificacoes form-control form-input-pro"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}