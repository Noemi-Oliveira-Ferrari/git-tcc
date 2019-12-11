import React, { Component, Fragment } from 'react';
import {Link, browserHistory} from 'react-router';
import DaUmHelp from '../components/DaUmHelp';
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
        <div className="tamanho-conteudo-home">

            {/* CAIXA SLIDER */}
            <div className="tamanho-caixa-slider-home">
                <div className='slider-home'>
                    <div className='slide1-home'></div>
                </div>
                <section className="caixa-slider-home">
                    <div className="conteudo-slider-home">
                        <h1 className="titulo-slider-home"><DaUmHelp/></h1>
                        <p className="subtitulo-slider-home">A mais nova solução para os problemas do seu cotidiano.</p>
                        <div className="caixa-inputs-slider-home">
                            <input onClick={()=>{browserHistory.push("/escolha")}} type="button" className="text-slider-home" value="Cadastre-se"/>
                            <button onClick={()=>{browserHistory.push("/login")}} className="btn-slider-home">Entrar</button>
                        </div>
                    </div>
                </section>
            </div>
            
            {/* SOBRE A EMPRESA */}
            <div className="tamanho-conteudo-empresa-home">
                <section className="caixa-sobre-empresa-home">
                    <h1 className="titulo-empresa-home">Sobre a empresa</h1>
                    <p className="subtitulo-empresa-home"> Somos a mais nova empresa de prestação de serviços, que conecta você ao profissional mais próximo de maneira rapida e eficaz. </p>
                    <div className="section-empresa-home">
                        
                        <div className="comp-empresa-home flex-center">
                            <div className="img-empresa-home">
                                <figure>
                                    <img src={Empresa1}/>
                                </figure>
                            </div>
                            <p className="text-empresa-home flex-center"> Necessita de serviços rápidos e ageis? Não perca tempo e comece a utilizar a nossa platorma, para solucionar os seus problemas.</p>
                        </div>

                        <div className="comp-empresa-home flex-center">
                            <div className="img-empresa-home">
                                <figure>
                                    <img src={Empresa2}/>
                                </figure>
                            </div>
                            <p className="text-empresa-home flex-center"> Para utilizar a plataforma o usuario deve ser maior de idade, pois no processo de pedido há transações financeiras envolvidas </p>
                        </div>

                        <div className="comp-empresa-home flex-center">
                            <div className="img-empresa-home">
                                <figure>
                                    <img src={Empresa3}/>
                                </figure>
                            </div>
                            <p className="text-empresa-home flex-center"> Uma nova maneira de complementar sua renda, exercendo sua função de forma autônoma e informal</p>
                        </div>
                    
                    </div>
                </section>
            </div>
            
            {/* SECTION DO PROFISSIONAL */}
            <div className="tamanho-conteudo-profissional-home flex-center">
                <section className="section-profissional-home">
                    <h1 className="titulo-pro-home-responsivo">Seja um Profissional</h1>
                    <div className="caixa-profissional-imagem-home">
                        <figure>
                            <img src={Profissional}/>
                        </figure>
                    </div>
                    <div className="caixa-profissional-conteudo-home flex-center">
                        <h1 className="titulo-pro-home">Seja um Profissional</h1>
                        <p className="texto-pro-home">Diversas pessoas estão a procura de um profissional com um determinado perfil, seja o próximo a ser contratado!</p>
                        <div className="caixa-itens-pro-home">
                            <figure>
                                <img src={Pro1}/>
                            </figure>
                            <div className="text-list-empresa-home"> Divulgue e apareça para outros usuarios</div>
                        </div>
                        <div className="caixa-itens-pro-home">
                            <figure>
                                <img src={Pro2}/>
                            </figure>
                            <div className="text-list-empresa-home">Multiplique seus ganhos recebendo boas avaliações de clientes</div>
                        </div>
                        <div className="caixa-itens-pro-home">
                            <figure>
                                <img src={Pro3}/>
                            </figure>
                            <div className="text-list-empresa-home">As assinaturas não doem no bolso, e você sai no lucro</div>
                        </div>
                        <div className="caixa-itens-pro-home outro-home">
                            <figure>
                                <img src={Pro4}/>
                            </figure>
                            <div className="text-list-empresa-home">Tudo organizado e sem complicação</div>
                        </div>
                    </div>
                </section>
            </div>
            
            {/* NOSSOS PLANOS */}
            <div className="tamanho-conteudo-plano-home">
                <div className="caixa-titulo-plano-home">
                    <h2 className="titulo-plano-home">
                        Nossos Planos
                    </h2>
                </div>
                <section className="section-caixa-plano-home">
                    <section className="section-plano-home" id="section-plans">
                        <div className="caixa-plano-home">
                            <div className="col-1-of-3">
                                <div className="card">
                                    <div className="card__side card__side--front-1">
                                        <div className="card__title card__title--1">
                                            <div className="image1-card-home"></div>
                                            <h4 className="card__heading">Mensal</h4>
                                        </div>
                                        <div className="card__details">
                                            <p className="text-card">Obtenha o plano Mensal e econômize cerca de 2% no valor mensal de sua assinatura.</p>
                                        </div>
                                    </div>
                                    <div className="card__side card__side--back card__side--back-1">
                                        <div className="card__cta">
                                        <div className="card__price-box">
                                            <p className="card__price-only">Por apenas</p>
                                            <p className="card__price-value">R$   15,00</p>
                                        </div>
                                        <a href="#popup" className="btn-card-home">Selecionar</a>
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
                                            <p className="text-card">Obtenha o plano semestral e econômize cerca de 5% no valor mensal de sua assinatura.</p>
                                        </div>
                                    </div>
                                    <div className="card__side card__side--back card__side--back-2">
                                        <div className="card__cta">
                                        <div className="card__price-box">
                                            <p className="card__price-only">Por apenas</p>
                                            <p className="card__price-value">R$ 72,00</p>
                                            <p className="pormes">R$ 12,00/MÊS</p>
                                        </div>
                                        <a href="#popup" className="btn-card-home">Selecionar</a>
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
                                        <p className="text-card">Obtenha o plano Anual e econômize cerca de 8% no valor mensal de sua assinatura.</p>
                                    </div>
                                </div>
                                <div className="card__side card__side--back card__side--back-3">
                                    <div className="card__cta">
                                    <div className="card__price-box">
                                        <p className="card__price-only">Por apenas</p>
                                        <p className="card__price-value">R$ 120,00</p>
                                        <p className="pormes">R$ 10,00/MÊS</p>
                                    </div>
                                    <div className="btn-card-home">Selecionar</div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                        <div className="caixa-btn-preco-home">
                            <Link to="/escolha" className="btn-vamos-comecar">Vamos Começar</Link>
                        </div>
                    </section>
                </section>
            </div>
            
            {/* CAIXA DE COMENTARIOS */}
            <div className="tamanho-conteudo-comentario-home">
                <div className="caixa-titulo-avaliacao-home">
                    <h1>Avaliações de nossos Usuários</h1>
                </div>
                <div className="conteudo-avaliacoes-home">
                    <section className="customer-slider-avaliacao">
                        <div className="slide-avalicao-home">
                            <div className="imagem-comentario-usuario-home1"></div>
                            <h2 className="nome-comentario-home">Alice Pereira</h2>
                            {/* <hr className="linha-comentario-usuario-home"/> */}
                            <p className="texto-comentario-usuario-home"> Òtimo profissional, chegou no horario e deixou o local limpo </p>
                            <div className="caixa-btn-home">
                                <button className="ver-mais-comentario-home">Ver mais</button>
                            </div>
                        </div>
                        <div className="slide-avalicao-home">
                            <div className="imagem-comentario-usuario-home2"></div>
                            <h2 className="nome-comentario-home">Alex Souza</h2>
                            {/* <hr className="linha-comentario-usuario-home"/> */}
                            <p className="texto-comentario-usuario-home">Moço bem bonzinho, bem simpatico, mto organizado e fez o trabalho mto bem </p>
                            <div className="caixa-btn-home">
                                <button className="ver-mais-comentario-home">Ver mais</button>
                            </div>
                        </div>
                        <div className="slide-avalicao-home">
                            <div className="imagem-comentario-usuario-home3"></div>
                            <h2 className="nome-comentario-home">Karina Oliveira</h2>
                            {/* <hr className="linha-comentario-usuario-home"/> */}
                            <p className="texto-comentario-usuario-home">Cumpriu com o horário marcado, bem organizado e concluiu o trabalho com eficacia  </p>
                            <div className="caixa-btn-home">
                                <button className="ver-mais-comentario-home">Ver mais</button>
                            </div>
                        </div>
                        <div className="slide-avalicao-home">
                            <div className="imagem-comentario-usuario-home1"></div>
                            <h2 className="nome-comentario-home">Ana Julia</h2>
                            {/* <hr className="linha-comentario-usuario-home"/> */}
                            <p className="texto-comentario-usuario-home">Executou o serviço muito bem, contrataria denovo </p>
                            <div className="caixa-btn-home">
                                <button className="ver-mais-comentario-home">Ver mais</button>
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