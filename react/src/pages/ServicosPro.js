import React, { Component, Fragment } from 'react';
import '../css/servicos-pro.css';
import CapaPerfilPro from '../components/CapaPerfilPro';
import CardServico from '../components/CardServico';
import $ from 'jquery';
import axios from 'axios';
import MenuLateral from '../components/MenuLateral';
import { getTipoLogado, getToken, getUsuario } from '../utils/verificaSessionStrg';
import { DOMINIO } from '../global';
import {SOLICITADO, ORCADO, ACEITO, REJEITADO, CANCELADO_CLIENTE, CANCELADO_PROFISSIONAL, CONCLUIDO} from '../utils/codeStatusPedidos';

export class ServicosPro extends Component{

    constructor(){
        super();
        this.state = {
            pedidosPendentes: [],
            pedidosConcluidos: [],
            avaliacao: ""
        }
        this.buscarPendentes = this.buscarPendentes.bind(this);
        this.buscarConcluidos = this.buscarConcluidos.bind(this);
    }

    componentDidMount(){
        this.buscarPendentes();
        this.buscarConcluidos();
    }

    buscarPendentes(){
        // console.log(getUsuario());

        axios({
            method: "GET",
            url: `${DOMINIO}pedidos/profissional/${getUsuario().idProfissional}/status/${ACEITO}`,
            headers: {"token": getToken()},
            timeout: 30000
        })
        .then(response =>{
            let jsonPendentes = response.data;
            this.setState({pedidosPendentes: jsonPendentes});
            console.log(jsonPendentes);
        })
        .catch(error =>{
            console.log(error);
        })

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
            console.log(jsonConcluidos);
        })
        .catch(error =>{
            console.log(error);
        })
    }

    buscarAvaliacoes(){

        axios({
            method: "GET",
            url: `${DOMINIO}avaliacoes/`,
            headers: {"token": getToken()},
            timeout: 30000
        })
        .then(response =>{
            let nota = response.data;
            this.setState({avaliacao: nota});
            console.log(nota);
        })
        .catch(error =>{
            console.log(error);
        })
    }
    

    render(){
        return(
            <Fragment>
                {/* <MenuLateral/> */}
                <CapaPerfilPro
                    nome={getUsuario().nome}
                    texto1="Veja o estado dos Pedidos"
                    texto2="Fique sempre por dentro"
                    texto3="Verifique suas Atividades"
                    texto4="Veja suas Avaliações"
                />
                <div class="caixa-conteudo-servico-pro">
                    <div class="caixa-titulo-servico">
                        <h1 class="titulo-servico">Seus Pedidos</h1>
                    </div>
                    <div class="conteudo-pro-servico">
                        <div class="caixa-conteudo-informacoes-servico">
                            <div class="text-dados">
                                <h3>Pendentes</h3>
                            </div>
                            <div className="servico-overflow">
                                {
                                    this.state.pedidosPendentes.map(pedido =>(
                                        <CardServico 
                                            titulo={pedido.cliente.nome}
                                            enderecoCliente={`${pedido.cliente.endereco.logradouro}, 
                                                ${pedido.cliente.endereco.cidade.cidade} - 
                                                ${pedido.cliente.endereco.cidade.microrregiao.uf.uf}`
                                            }
                                            comentario={pedido.descricao}
                                            estrelas="caixa-star-hidden"
                                        />
                                    ))
                                }
                                <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Rofrigo Amoedo, Jandira - SP"
                                    comentario="Minha maquina quebrou e nao funciona. O regulador de água estourou e preciso de um reparo urgente"
                                    estrelas="caixa-star-hidden"
                                />
                                {/*
                                <CardServico
                                    titulo="Concerto maquina de lavar Brastemp"
                                    enderecoCliente="Maria Gasolina, Barueri - SP"
                                    comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                                    estrelas="caixa-star-hidden"
                                /> */}
                            </div>
                        </div>
                        <div class="caixa-conteudo-informacoes-servico">
                            <div class="text-dados">
                                <h3>Concluídos</h3>
                            </div>
                            <div className="servico-overflow">
                                {
                                    this.state.pedidosConcluidos.map(pedido =>(
                                        <CardServico 
                                            titulo={pedido.cliente.nome}
                                            enderecoCliente={`${pedido.cliente.endereco.logradouro}, 
                                                ${pedido.cliente.endereco.cidade.cidade} - 
                                                ${pedido.cliente.endereco.cidade.microrregiao.uf.uf}`
                                            }
                                            comentario={pedido.descricao}
                                            estrelas="caixa-star"
                                        />
                                    ))
                                }
                                <CardServico
                                    titulo="Fiamento de dois comodos"
                                    enderecoCliente="Maria Fernandes, Barueri - SP"
                                    comentario="Ótimo profissional, chegou no horário e fez o trabalho bem feito, mto educado, só deixou sujo"
                                    estrelas="caixa-star"
                                    avaliacao={`Avalação do Cliente: 9`}
                                />
                                {/* 
                                <CardServico
                                    titulo="Fiamento de dois comodos"
                                    enderecoCliente="Maria Gasolina, Barueri - SP"
                                    comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                                    estrelas="caixa-star"
                                /> */}
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>


        );
    }

}

export default ServicosPro;