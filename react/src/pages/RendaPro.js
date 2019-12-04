import React, { Component, Fragment } from 'react';
// css
import '../css/renda.css';
import '../css/padroes.css';
// components
import CaixaTexto from '../components/CaixaTexto';
import CaixaImagem from '../components/CaixaImagem';
// img
import Valor from '../img/value.png'
import Missao from '../img/target.png'
import Visao from '../img/flag.png'
import CardServico from '../components/CardServico';
import CapaPerfilPro from '../components/CapaPerfilPro';
import axios from 'axios';
import { DOMINIO, DOMINIO_IMG } from '../global';
import { getTipoLogado, getToken, getUsuario } from '../utils/verificaSessionStrg';
import {SOLICITADO, ORCADO, ACEITO, REJEITADO, CANCELADO_CLIENTE, CANCELADO_PROFISSIONAL, CONCLUIDO} from '../utils/codeStatusPedidos';


export class RendaPro extends Component{

    constructor() {
        super();
        this.state = {
            nomePro: "", servicoPro: "",
            localPro: "", notaPro: "",
            valorPro: "", fotoPro: "",
            avaliacoes: [],
            pedidosConcluidos: [],
            renda: ""
        }
        
        this.buscarAvaliacoes = this.buscarAvaliacoes.bind(this);
        this.buscarConcluidos = this.buscarConcluidos.bind(this);

    }

    colocaDadosNaCapa(pro){
        let decimal;
        let valorHora;
        let localPro = `${pro.endereco.cidade.cidade}, ${pro.endereco.cidade.microrregiao.uf.estado} - ${pro.endereco.cidade.microrregiao.uf.uf}`;
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

    componentDidMount(){
        this.buscarAvaliacoes();
        this.buscarConcluidos();
        this.colocaDadosNaCapa(getUsuario());
    }

    
    buscarConcluidos(){

        axios({
            method: "GET",
            url: `${DOMINIO}pedidos/profissional/${getUsuario().idProfissional}/status/${CONCLUIDO}`,
            headers: {"token": getToken()},
            timeout: 30000
        })
        .then(response =>{
            let jsonConcluidos = response.data;
            this.setState({pedidosConcluidos: jsonConcluidos});
            let rendaTotal = 0;
            let renda = "";

            jsonConcluidos.map((concluido) => (
                rendaTotal += ((concluido.valorServico)-(concluido.valorServico*.04))
                // rendaTotal += (concluido.valorServico)
            ));
            renda = new String(rendaTotal.toFixed(2));
            if(renda.includes(".")){
                renda = renda.replace(".", ",");
            }else{
                renda += ",00"
            }
            
            this.setState({renda: renda});
            console.log(jsonConcluidos);
        })
        .catch(error =>{
            console.log(error);
        })
    }
    buscarAvaliacoes(){

        axios({
            method: "GET",
            url: `${DOMINIO}avaliacoes/profissional/id/${getUsuario().idProfissional}`,
            headers: {"token": getToken()},
            timeout: 30000
        })
        .then(response =>{
            let jsonAvaliacoes = response.data;
            this.setState({avaliacoes: jsonAvaliacoes});
            console.log(jsonAvaliacoes);
        })
        .catch(error =>{
            console.log(error);
        })
    }
    render(){
        return(
            <Fragment>
                <CapaPerfilPro
                    nome={this.state.nomePro}
                    texto1={this.state.servicoPro}
                    texto2={this.state.localPro}
                    texto3="Média Geral: 4.9"
                    texto4={`R$ ${this.state.valorPro}`}
                    foto={this.state.fotoPro}
                />
                <section class="section-plano flex-center">
                    <div class="caixa-titulo-plano flex-center">
                        <h1 class="titulo-plano">Seus Planos</h1>
                    </div>
                    <div class="section-plano-renda" id="section-plans">
                        
                        <div class="caixa-btn-editar">
                            <button class="btn-editar">Alterar Plano</button>
                        </div>
                        <div class="caixa-plano-home">
                            <div class="col-1-of-3">
                                <div class="card">
                                <div class="card__side card__side--front-1">
                                    <div class="card__title card__title--1">
                                        <div class="image1-card-home"></div>
                                        <h4 class="card__heading">Mensal</h4>
                                    </div>
                                    <div class="card__details">
                                        <p class="text-card">Type something hereType something hereType something here</p>
                                    </div>
                                    <div class="caixa-check">
                                        <div class="check"></div>
                                    </div>
                                </div>
                                <div class="card__side card__side--back card__side--back-1">
                                    <div class="card__cta">
                                    <div class="card__price-box">
                                        <p class="card__price-only">Por apenas</p>
                                        <p class="card__price-value">R$ 15,00</p>
                                        <p class="card__price-month">por mês</p>
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <div class="col-1-of-3">
                                <div class="card">
                                    <div class="card__side card__side--front-2">
                                        <div class="card__title card__title--2">
                                            <div class="image2-card-home"></div>
                                        <h4 class="card__heading">Semestral</h4>
                                        </div>
                                        <div class="card__details">                
                                            <p class="text-card">Type something hereType something hereType something here</p>
                                        </div>
                                    </div>
                                    <div class="card__side card__side--back card__side--back-2">
                                        <div class="card__cta">
                                        <div class="card__price-box">
                                            <p class="card__price-only">Por apenas</p>
                                            <p class="card__price-value-full">R$ 72,00</p>
                                            <p class="card__price-value">R$ 12,00</p>
                                            <p class="card__price-month">por mês</p>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-1-of-3">
                                <div class="card">
                                <div class="card__side card__side--front-3">
                                    <div class="card__title card__title--3">
                                        <div class="image3-card-home"></div>
                                    <h4 class="card__heading">Anual</h4>
                                    </div>
                                    <div class="card__details">
                                        <p class="text-card">Type something hereType something hereType something here</p>
                                    </div>
                                </div>
                                <div class="card__side card__side--back card__side--back-3">
                                    <div class="card__cta">
                                    <div class="card__price-box">
                                            <p class="card__price-only">Por apenas</p>
                                            <p class="card__price-value-full">R$ 120,00</p>
                                            <p class="card__price-value">R$ 10,00</p>
                                            <p class="card__price-month">por mês</p>
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <div class="caixa-titulo-renda">
                    <h1 class="titulo-renda">Sua Renda</h1>
                </div>
                <div class="caixa-centro">
                    <div class="caixa-renda">
                        <div class="caixa-valores">
                            <p class="title-valor">Renda atual</p>
                            <p class="text-valor"><span style={{fontFamily: 'Manjari'}}>R$ </span>{this.state.renda}</p>
                        </div>
                        <div class="caixa-servicos flex-center">
                            <h2 class="title-servicos-prestados">Serviços Prestados</h2>
                                {
                                    this.state.avaliacoes.map(avaliacao =>(
                                        <CardServico 
                                            titulo={avaliacao.cliente.nome}
                                            enderecoCliente={`${avaliacao.cliente.endereco.logradouro}, 
                                                ${avaliacao.cliente.endereco.cidade.cidade} - 
                                                ${avaliacao.cliente.endereco.cidade.microrregiao.uf.uf}`
                                            }
                                            comentario={avaliacao.comentario}
                                            estrelas="caixa-star"
                                        />
                                    ))
                                }
                            {/* <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                />
                            <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                />
                            <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                />
                            <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                />
                            <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                /> */}
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export default RendaPro;
