import React, { Component, Fragment } from 'react';
import '../css/faq.css';
import { InputFaq, InputProblema } from '../components/FormElements';
import { Botao } from '../components/Botao';

export default class Faq extends Component {
    render() {
        return (
            <Fragment>
                <div className="caixa-perguntar-faq">
                    <div>
                        <h1 className="titulo-duvida-faq">Como podemos lhe ajudar?</h1>
                    </div>
                    <div className="caixa-inputs-duvida-faq">
                        <div className="caixa-input-text-penguntar-faq">
                            <InputFaq type="text" classInput="input-text-penguntar-faq" />
                        </div>
                        <div className="caixa-btn-buscar-faq">
                            <Botao classBotao="btn-buscar-faq" type="submit" nameBotao="Procurar" valueBotao="Procurar"/>
                        </div>
                    </div>
                </div>
                <div className="caixa-peguntas-frenquentes">
                    <div className="tamanho-caixa-perguntas-frequentes-faq box-faq">
                        <div className="caixa-perguntas-frequentes-faq wrapper-faq">
                            <h2 className="titulo-pergunta-frequente-faq">Dúvida frequentes</h2>
                            <div className="caixa-conteudo-duvidas-frequentes-faq">
                                <div className="row-conteudo-duvidas-frequentes-faq">
                                    <div className="caixa-duvidas-frequentes-faq">
                                        <h3 className="title-pergunta-frequente-faq">Type something here</h3>
                                        <p className="text-pergunta-frequente-faq">Type something here Type something here Type something here Type something here Type something here</p>
                                    </div>
                                    <div className="caixa-duvidas-frequentes-faq">
                                        <h3 className="title-pergunta-frequente-faq">Type something here</h3>
                                        <p className="text-pergunta-frequente-faq">Type something here Type something here Type something here Type something here Type something here</p>
                                    </div>
                                </div>
                                
                                <div className="caixa-btn-mostar-mais-faq">
                                    <button className="btn-mostar-mais-faq">Mostrar Mais</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="caixa-problema-faq">
                        <div className="caixa-titulo-problema-faq">
                            <h1 className="titulo-problema-nao-resolvido-faq">Ainda não resolveu seu problema?</h1>
                        </div>
                        <div className="conteudo-problemas-nao-resolvidos-faq">
                            <p className="text-problema-faq">Possui um problema? Envie um e-mail que entraremos em contato assim que possível!</p>
                            <div className="caixa-inputs-faq">
                                <div className="caixa-inputs-formulario-contato">
                                    <form name="form_contato" method="GET" action="">
                                        <div className="container-flex-contato">
                                            <div className="card-formulario-contato">
                                                <div className="campos-dados-contato">
                                                    <div className="container-nome-email-contato">
                                                        <div className="caixa-nome-contato">
                                                            <InputProblema  
                                                                classInput="form-control form-input-contato"
                                                                label="Nome:"
                                                                type="text"
                                                                id="txt-email-contato"/>
                                                        </div>
                                                        <div className="caixa-cpf-contato">
                                                            <InputProblema  
                                                                classInput="form-control form-input-contato"
                                                                label="Email:"
                                                                type="text"
                                                                id="txt-email-contato"/>
                                                            
                                                            {/* <label className="form-label">E-mail:</label>
                                                            <input maxlength="10" id="txt-email-contato" type="text" value="" name="" className="form-control form-input-contato" /> */}
                                                        </div>
                                                    </div>
                                                    <div className="container-enviar-contato">
                                                        <Botao classBotao="btn-enviar-fomr-fale-conosco" type="submit" nameBotao="Enviar" valueBotao="Enviar"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        )
    }
}