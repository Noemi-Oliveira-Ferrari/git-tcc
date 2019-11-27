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

export class RendaPro extends Component{

    render(){
        return(
            <>
            <section class="section-plano">
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
                                                <p class="card__price-value">R$ 25,00</p>
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
                                            <p class="card__price-value">R$ 45,00</p>
                                        </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            </div>
                </div>
            </section>

            <div class="caixa-titulo-renda">
                <h1 class="titulo-renda">Renda do Profissional</h1>
            </div>
            <div class="caixa-centro">
                <div class="caixa-renda">
                    <div class="caixa-valores">
                        <p class="title-valor">Renda atual</p>
                        <p class="text-valor">120,00</p>
                    </div>
                    <div class="caixa-servicos">
                        <h2 class="title-servicos-prestados">Serviços Prestados</h2>
                        <div class="servico-prestado">
                            <div class="caixa-esquerda-servico">
                                <div class="perfil-cliente-servico-prestado"></div>
                                <p class="nome-cliente-servico">TextText TextText</p>
                            </div>
                            <div class="caixa-direita-servico">
                                <h3 class="title-servico">Type something here</h3>
                                <p class="text-servico">Type something here Type  dfgdsfg something here Type something fvb gb hereType something here fgdgdf Type something fvb gb hereType something here fgdgdf</p>
                                <button class="btn-detalhes-servico">Detalhes</button>
                            </div>
                        </div>
                        <div class="servico-prestado">
                            <div class="caixa-esquerda-servico">
                                <div class="perfil-cliente-servico-prestado"></div>
                                <p class="nome-cliente-servico">TextText TextText</p>
                            </div>
                            <div class="caixa-direita-servico">
                                <h3 class="title-servico">Type something here</h3>
                                <p class="text-servico">Type something here Type  dfgdsfg something here Type something fvb gb hereType something here fgdgdf Type something fvb gb hereType something here fgdgdf</p>
                                <button class="btn-detalhes-servico">Detalhes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
        );
    }

}

export default RendaPro;
