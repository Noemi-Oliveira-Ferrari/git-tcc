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
                <section className="section-plano flex-center">
                    <div className="caixa-titulo-plano flex-center">
                        <h1 className="titulo-plano">Seus Planos</h1>
                    </div>
                    <div className="section-plano-renda" id="section-plans">
                        
                        <div className="caixa-btn-editar">
                            <button className="btn-editar">Alterar Plano</button>
                        </div>
                        <div className="caixa-plano-home">
                            <div className="col-1-of-3">
                                <div className="card">
                                <div className="card__side card__side--front-1">
                                    <div className="card__title card__title--1">
                                        <div className="image1-card-home"></div>
                                        <h4 className="card__heading">Mensal</h4>
                                    </div>
                                    <div className="card__details">
                                        <p className="text-card">Você renova todo mês com a nossa plataforma por um precinho camarada.</p>
                                    </div>
                                    <div className="caixa-check">
                                        <div className="check"></div>
                                    </div>
                                </div>
                                <div className="card__side card__side--back card__side--back-1">
                                    <div className="card__cta">
                                    <div className="card__price-box">
                                        <p className="card__price-only">Por apenas</p>
                                        <p className="card__price-value">R$ 15,00</p>
                                        <p className="card__price-month">por mês</p>
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <div className="col-1-of-3">
                                <div className="card">
                                    <div className="card__side card__side--front-2">
                                        <div className="card__title card__title--2">
                                            <div className="image2-card-home"></div>
                                        <h4 className="card__heading">Semestral</h4>
                                        </div>
                                        <div className="card__details">                
                                            <p className="text-card">Mais em conta. Fique durante 6 meses na plataforma, e pague menos por isso.</p>
                                        </div>
                                    </div>
                                    <div className="card__side card__side--back card__side--back-2">
                                        <div className="card__cta">
                                        <div className="card__price-box">
                                            <p className="card__price-only">Por apenas</p>
                                            <p className="card__price-value-full">R$ 72,00</p>
                                            <p className="card__price-value">R$ 12,00</p>
                                            <p className="card__price-month">por mês</p>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="col-1-of-3">
                                <div className="card">
                                <div className="card__side card__side--front-3">
                                    <div className="card__title card__title--3">
                                        <div className="image3-card-home"></div>
                                    <h4 className="card__heading">Anual</h4>
                                    </div>
                                    <div className="card__details">
                                        <p className="text-card">Quanto mais tempo melhor. Assine por um ano e receba descontos com o tempo de uso</p>
                                    </div>
                                </div>
                                <div className="card__side card__side--back card__side--back-3">
                                    <div className="card__cta">
                                    <div className="card__price-box">
                                            <p className="card__price-only">Por apenas</p>
                                            <p className="card__price-value-full">R$ 120,00</p>
                                            <p className="card__price-value">R$ 10,00</p>
                                            <p className="card__price-month">por mês</p>
                                    </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <div className="caixa-titulo-renda">
                    <h1 className="titulo-renda">Sua Renda</h1>
                </div>
                <div className="caixa-centro">
                    <div className="caixa-renda">
                        <div className="caixa-valores">
                            <p className="title-valor">Renda atual</p>
                            <p className="text-valor"><span style={{fontFamily: 'Manjari'}}>R$ </span>{this.state.renda}</p>
                        </div>
                        <div className="caixa-servicos">
                            <h2 className="title-servicos-prestados">Serviços Prestados</h2>
                            <div className="caixa-avaliacoes-renda">
                                {/* {
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
                                } */}
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
                </div>
            </Fragment>
        );
    }
}

export default RendaPro;
