import React, { Component, Fragment } from 'react';
// img
import Empresa1 from '../img/clock.png'
import Empresa2 from '../img/adult.png'
import Empresa3 from '../img/grafico.png'
import Pro1 from '../img/megaphone.png'
import Pro2 from '../img/analytics.png'
import Pro3 from '../img/hand.png'
import Pro4 from '../img/note.png'
import Profissional from '../img/pro.jpeg'
// css js
import $ from 'jquery';
import '../js/carousel';
import '../js/index';
import '../js/index-slider-home';
import '../css/home.css';
import '../css/bootstrap.css';
import '../css/padroes.css';


export class Home extends Component{
   render(){
    return(
        <div class="tamanho-conteudo-home">
            <h1>TESTE</h1>
        {/* CAIXA SLIDER */}
        <div class="tamanho-caixa-slider-home">
            <div class='slider-home'>
                <div class='slide1-home'></div>
            </div>
            <section class="caixa-slider-home">
                <div class="conteudo-slider-home">
                    <h1 class="titulo-slider-home">DaUmHelp!</h1>
                    <p class="subtitulo-slider-home">A mais nova solução para os problemas do seu cotidiano.</p>
                    <div class="caixa-inputs-slider-home">
                        <input type="button" class="text-slider-home" value="Cadastre-se"/>
                        <button class="btn-slider-home">Entrar</button>
                    </div>
                </div>
            </section>
        </div>
        
        {/* SOBRE A EMPRESA */}
        <div class="tamanho-conteudo-empresa-home">
            <section class="caixa-sobre-empresa-home">
                <h1 class="titulo-empresa-home">Sobre a empresa</h1>
                <p class="subtitulo-empresa-home"> Somos a mais nova empresa de prestação de serviços, que conecta você ao profissional mais próximo de maneira rapida e eficaz. </p>
                <div class="section-empresa-home">
                    
                    <div class="comp-empresa-home flex-center">
                        <div class="img-empresa-home">
                            <figure>
                                <img src={Empresa1}/>
                            </figure>
                        </div>
                        <p class="text-empresa-home flex-center"> Necessita de serviços rápidos e ageis? Não perca tempo e comece a utilizar a nossa platorma, para solucionar os seus problemas.</p>
                    </div>

                    <div class="comp-empresa-home flex-center">
                        <div class="img-empresa-home">
                            <figure>
                                <img src={Empresa2}/>
                            </figure>
                        </div>
                        <p class="text-empresa-home flex-center"> Para utilizar a plataforma o usuario deve ser maior de idade, pois no processo de pedido há transações financeiras envolvidas </p>
                    </div>

                    <div class="comp-empresa-home flex-center">
                        <div class="img-empresa-home">
                            <figure>
                                <img src={Empresa3}/>
                            </figure>
                        </div>
                        <p class="text-empresa-home flex-center"> Uma nova maneira de complementar sua renda, exercendo sua função de forma autônoma e informal</p>
                    </div>
                   
                </div>
            </section>
        </div>
        
        {/* SECTION DO PROFISSIONAL */}
        <div class="tamanho-conteudo-profissional-home flex-center">
            <section class="section-profissional-home">
                <h1 class="titulo-pro-home-responsivo">Seja um Profissional</h1>
                <div class="caixa-profissional-imagem-home">
                    <figure>
                        <img src={Profissional}/>
                    </figure>
                </div>
                <div class="caixa-profissional-conteudo-home flex-center">
                    <h1 class="titulo-pro-home">Seja um Profissional</h1>
                    <p class="texto-pro-home">Diversas pessoas estão a procura de um profissional com um determinado perfil, seja o próximo a ser contratado!</p>
                    <div class="caixa-itens-pro-home">
                        <figure>
                            <img src={Pro1}/>
                        </figure>
                        <div class="text-list-empresa-home"> Divulgue e apareça para outros usuarios</div>
                    </div>
                    <div class="caixa-itens-pro-home">
                        <figure>
                            <img src={Pro2}/>
                        </figure>
                        <div class="text-list-empresa-home">Multiplique e rentabilize</div>
                    </div>
                    <div class="caixa-itens-pro-home">
                        <figure>
                            <img src={Pro3}/>
                        </figure>
                        <div class="text-list-empresa-home">O profissional pode utiliza-la realizando um pagamento com planos bem amigaveis.</div>
                    </div>
                    <div class="caixa-itens-pro-home outro-home">
                        <figure>
                            <img src={Pro4}/>
                        </figure>
                        <div class="text-list-empresa-home">Tudo organizado e sem complicação</div>
                    </div>
                </div>
            </section>
        </div>
        
        {/* NOSSOS PLANOS */}
        <div class="tamanho-conteudo-plano-home">
            <div class="caixa-titulo-plano-home">
                <h2 class="titulo-plano-home">
                    Nossos Planos
                </h2>
            </div>
            <section class="section-caixa-plano-home">
                <section class="section-plano-home" id="section-plans">
                    <div class="caixa-plano-home">
                        <div class="col-1-of-3">
                            <div class="card">
                                <div class="card__side card__side--front-1">
                                    <div class="card__title card__title--1">
                                        <div class="image1-card-home"></div>
                                        <h4 class="card__heading">Mensal</h4>
                                    </div>
                                    <div class="card__details">
                                        <p class="text-card">Obtenha o plano Mensal e econômize cerca de 2% no valor mensal de sua assinatura.</p>
                                    </div>
                                </div>
                                <div class="card__side card__side--back card__side--back-1">
                                    <div class="card__cta">
                                    <div class="card__price-box">
                                        <p class="card__price-only">Por apenas</p>
                                        <p class="card__price-value">R$   15,00</p>
                                    </div>
                                    <a href="#popup" class="btn-card-home">Selecionar</a>
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
                                        <p class="text-card">Obtenha o plano semestral e econômize cerca de 5% no valor mensal de sua assinatura.</p>
                                    </div>
                                </div>
                                <div class="card__side card__side--back card__side--back-2">
                                    <div class="card__cta">
                                    <div class="card__price-box">
                                        <p class="card__price-only">Por apenas</p>
                                        <p class="card__price-value">R$ 72,00</p>
                                        <p class="pormes">R$ 12,00/MÊS</p>
                                    </div>
                                    <a href="#popup" class="btn-card-home">Selecionar</a>
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
                                    <p class="text-card">Obtenha o plano Anual e econômize cerca de 8% no valor mensal de sua assinatura.</p>
                                </div>
                            </div>
                            <div class="card__side card__side--back card__side--back-3">
                                <div class="card__cta">
                                <div class="card__price-box">
                                    <p class="card__price-only">Por apenas</p>
                                    <p class="card__price-value">R$ 120,00</p>
                                    <p class="pormes">R$ 10,00/MÊS</p>
                                </div>
                                <div class="btn-card-home">Selecionar</div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </div>
                    <div class="caixa-btn-preco-home">
                        <a href="#" class="btn-vamos-comecar">Vamos Começar</a>
                    </div>
                </section>
            </section>
        </div>
        
        {/* CAIXA DE COMENTARIOS */}
        <div class="tamanho-conteudo-comentario-home">
            <div class="caixa-titulo-avaliacao-home">
                <h1>Avaliações de nossos Usuários</h1>
            </div>
            <div class="conteudo-avaliacoes-home">
                <section class="customer-slider-avaliacao">
                    <div class="slide-avalicao-home">
                        <div class="imagem-comentario-usuario-home1"></div>
                        <h2 class="nome-comentario-home">Alice Pereira</h2>
                        <hr class="linha-comentario-usuario-home"/>
                        <p class="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div class="caixa-btn-home">
                            <button class="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div class="slide-avalicao-home">
                        <div class="imagem-comentario-usuario-home2"></div>
                        <h2 class="nome-comentario-home">Alex Souza</h2>
                        <hr class="linha-comentario-usuario-home"/>
                        <p class="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div class="caixa-btn-home">
                            <button class="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div class="slide-avalicao-home">
                        <div class="imagem-comentario-usuario-home3"></div>
                        <h2 class="nome-comentario-home">Karina Oliveira</h2>
                        <hr class="linha-comentario-usuario-home"/>
                        <p class="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div class="caixa-btn-home">
                            <button class="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div class="slide-avalicao-home">
                        <div class="imagem-comentario-usuario-home1"></div>
                        <h2 class="nome-comentario-home">Ana Julia</h2>
                        <hr class="linha-comentario-usuario-home"/>
                        <p class="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div class="caixa-btn-home">
                            <button class="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>

    );
   }

}

export default Home;