import React, { Component, Fragment } from 'react';
import '../css/servicos-pro.css';
import CapaPerfilPro from '../components/CapaPerfilPro';
import CardServico from '../components/CardServico';
import $ from 'jquery';
import axios from 'axios';
import { getTipoLogado, getToken, getUsuario } from '../utils/verificaSessionStrg';
import { DOMINIO } from '../global';
import {SOLICITADO, ORCADO, ACEITO, REJEITADO, CANCELADO_CLIENTE, CANCELADO_PROFISSIONAL, CONCLUIDO} from '../utils/codeStatusPedidos';
import { conditionalExpression } from '@babel/types';

export class ServicosPro extends Component{

    constructor(){
        super();
        this.state = {
            pedidosPendentes: [],
            pedidosConcluidos: []
        }
        this.buscarPendentes = this.buscarPendentes.bind(this);
        this.buscarConcluidos = this.buscarConcluidos.bind(this);
    }

    componentDidMount(){
        this.buscarConcluidos();
    }

    buscarPendentes(){
        console.log(getUsuario());

        axios({
            method: "GET",
            url: `${DOMINIO}pedidos/profissional/${getUsuario().idProfissional}/status/${SOLICITADO}`,
            headers: {"token": getToken()},
            timeout: 30000
        })
        .then(response =>{
            let jsonPendentes = response.data;
            this.setState({pedidosPendentes: jsonPendentes});
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
        })
        .catch(error =>{
            console.log(error);
        })
    }
    

    render(){
        return(
        <Fragment>
            <CapaPerfilPro
            nome="Seus Pedidos"
            texto1="Veja o estado dos pedidos"
            texto2="Fique sempre por dentro"
            texto3="verifique suas atividades"
            texto4="Veja suas avaliações"
            ></CapaPerfilPro>
            <div class="caixa-conteudo-servico-pro">
                <div class="conteudo-pro-servico">
                    <div class="caixa-conteudo-informacoes-servico">
                        <div class="text-dados">
                            <h3>Pendentes</h3>
                        </div>
                        <div className="servico-overflow">
                            <CardServico
                                titulo="Concerto maquina de lavar Brastemp"
                                cliente="Maria Gasolina, Barueri - SP"
                                comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                                estrelas="caixa-star-hidden"
                            ></CardServico>
                            <CardServico
                                titulo="Concerto maquina de lavar Brastemp"
                                cliente="Maria Gasolina, Barueri - SP"
                                comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                                estrelas="caixa-star-hidden"
                            ></CardServico>
                        </div>
                    </div>
                    <div class="caixa-conteudo-informacoes-servico">
                        <div class="text-dados">
                            <h3>Concluídos</h3>
                        </div>
                        <div className="servico-overflow">
                            <CardServico
                                titulo="Fiamento de dois comodos"
                                cliente="Maria Gasolina, Barueri - SP"
                                comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                                estrelas="caixa-star"
                            ></CardServico>
                        
                            <CardServico
                                titulo="Fiamento de dois comodos"
                                cliente="Maria Gasolina, Barueri - SP"
                                comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                                estrelas="caixa-star"
                            ></CardServico>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>


        );
    }

}

export default ServicosPro;