import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import {InputCadastroPro, SelectCategoriaPro, SelectSubcategoriaPro} from './InputCadastroPro';


export class DadosPessoaisPro extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "", cnpj: "",
            email: "", senha: "", cep: "", logradouro: "",
            bairro: "", cidade: "", uf: "",
        }
        this.setNome = this.setNome.bind(this);
        this.setDataNasc = this.setDataNasc.bind(this);
        this.setCpf = this.setCpf.bind(this);
        this.setCnpj = this.setCnpj.bind(this);
        this.setEmail = this.setEmail.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setCep = this.setCep.bind(this);
        this.setLogradouro = this.setLogradouro.bind(this);
        this.setBairro = this.setBairro.bind(this);
        this.setCidade = this.setCidade.bind(this);
        this.setUf = this.setUf.bind(this);
    }
    setNome(event){
        this.setState({nome: event.target.value});
        console.log(this.state.nome);
    }
    setDataNasc(event){
        this.setState({dataNasc: event.target.value});
        console.log(this.state.dataNasc);
    }
    setCpf(event){
        this.setState({cpf: event.target.value});
        console.log(this.state.cpf);
    }
    setCnpj(event){
        this.setState({cnpj: event.target.value});
        console.log(this.state.cnpj);
    }
    setEmail(event){
        this.setState({email: event.target.value});
        console.log(this.state.email);
    }
    setSenha(event){
        this.setState({senha: event.target.value});
        console.log(this.state.senha);
    }
    setCep(event){
        this.setState({cep: event.target.value});
        console.log(this.state.cep);
    }
    setLogradouro(event){
        this.setState({logradouro: event.target.value});
        console.log(this.state.logradouro);
    }
    setBairro(event){
        this.setState({bairro: event.target.value});
        console.log(this.state.bairro);
    }
    setCidade(event){
        this.setState({cidade: event.target.value});
        console.log(this.state.cidade);
    }
    setUf(event){
        this.setState({uf: event.target.value});
        console.log(this.state.uf);
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
                                onChange={this.setNome}
                            />

                            <InputCadastroPro
                                classInputPro="caixa-dataNasc"
                                label="Data Nasc:."
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
                            />

                            <InputCadastroPro
                                classInputPro="caixa-confirmar-senha"
                                label="Confirmar Senha:"
                                maxLength="130"
                                id="txt-confirmar-senha"
                                type="password"
                                name="txt_confirmar_senha"
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
                            />

                            <InputCadastroPro
                                classInputPro="caixa-logradouro"
                                label="Logradouro:"
                                maxLength="120"
                                id="txt-logradouro"
                                type="text"
                                name="txt_logradouro"
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
                            />

                            <InputCadastroPro
                                classInputPro="caixa-cidade"
                                label="Cidade:"
                                maxLength="120"
                                id="txt-cidade"
                                type="text"
                                name="txt_cidade"
                            />

                            <InputCadastroPro
                                classInputPro="caixa-uf"
                                label="UF:"
                                maxLength="2"
                                id="txt-uf"
                                type="text"
                                name="txt_uf"
                            />
                            
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export class DadosProfissional extends Component{
    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-servico">
                    <h3 className="title-card">Dados Profissionais</h3>
                    <div className="flex-center campos-servicos">
                        <div className="container-servico-pro">

                            <div className="flex-center container-categoria">
                                <SelectCategoriaPro
                                    label="Categoria:"
                                    id="txt-categoria"
                                    name="slt_categoria"
                                    classSelectPro="caixa-categoria"
                                />
                            </div>
                            
                            <div className="flex-center container-subcat">
                                <SelectSubcategoriaPro
                                    label="Subcategoria:"
                                    id="txt-subcat"
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