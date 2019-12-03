import React, { Component, Fragment } from 'react';
import '../css/fale-conosco.css';
import '../css/faq.css';

export default class FaleConosco extends Component {
    render() {
        return (
            <Fragment>
            <div className="tamanho-caixa-contato">
                <div className="caixa-contato">
                    <div className="caixa-informacoes-empresa-contato">
                        <h2 className="titulo-contato">Fale Conosco</h2>
                        <p className="subtitulo-contato">Preencha todos os Campos para enviar sua mensagem.</p>
                        <div className="caixa-inputs-formulario-contato">
                            <form name="form_contato" method="GET" action="">
                                <div className="container-flex-contato">
                                    <div className="card-formulario-contato">
                                        <div className="campos-dados-contato">
                                            <div className="container-nome-cpf-contato">
                                                <div className="caixa-nome-contato">
                                                    <label className="form-label">Nome:</label>
                                                    <input maxlength="100" id="txt-nome-contato"type="text" value="" name="" className="form-control form-input-contato"/>
                                                </div>
                                                <div className="caixa-cpf-contato">    
                                                    <label className="form-label">CPF:</label>
                                                    <input maxlength="10" id="txt-cpf-contato"type="text" value="" name="" className="form-control form-input-contato"/>
                                                </div>
                                            </div>
                                            <div className="container-email-telefone-contato">
                                                <div className="caixa-email-contato">
                                                    <label className="form-label">E-mail:</label>
                                                    <input maxlength="20" id="txt-email-contato"type="text" value="" name="" className="form-control form-input-contato"/>
                                                </div>
                                                <div className="caixa-telefone-contato">
                                                    <label className="form-label">Telefone:</label>
                                                    <input maxlength="150" id="txt-telefone-contato"type="text" value="" name="" className="form-control form-input-contato"/>
                                                </div>
                                            </div>
                                            <div className="container-mensagem-contato">
                                                <div className="caixa-mensagem-contato">
                                                    <label className="form-label">Mensagem:</label>
                                                    <input maxlength="127" id="txt-mensagem-contato" type="password" value="" name="" className="form-control form-input-contato"/>
                                                </div>
                                            </div>
                                            <div className="container-enviar-contato">
                                                <button className="btn-enviar-fomr-fale-conosco">Enviar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div className="caixa-outras-formas-contato">
                
                <div className="wrapper">
                    <ul className="bg-bubbles">
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>

                    <div className="caixa-titulo-outros-contato">
                        <h2 className="title-principal-outras-formas-contato">
                            Outras Formas  de falar conosco
                        </h2>
                    </div>
                    <div className="caixa-outros-contato">
                        <div className="caixas-itens-outras-formas-contato">
                            <div className="image-outras-contato1"></div>
                            <div className="caixa-conteudo-itens-outras-contato">
                                <h4 className="title-outras-formas-contato">Via Email</h4>
                                <p className="text-outras-formas-contato">Nos envie um email e entraremos em contato assim que possível.</p>
                            </div>
                        </div>
                        <div className="caixas-itens-outras-formas-contato">
                            <div className="image-outras-contato2"></div>
                            <div className="caixa-conteudo-itens-outras-contato">
                                <h4 className="title-outras-formas-contato">Faq</h4>
                                <p className="text-outras-formas-contato">Resolva os seus problemas sobre a utilização da plaforma, e entre em contato caso necessário.</p>
                            </div>
                        </div>
                         <div className="caixas-itens-outras-formas-contato">
                            <div className="image-outras-contato"></div>
                            <div className="caixa-conteudo-itens-outras-contato">
                                <h4 className="title-outras-formas-contato">Central  de Solução</h4>
                                <p className="text-outras-formas-contato">TextText TextText TextText TextText TextText TextText TextText TextText TextText TextText TextText TextText</p>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
            </Fragment>



        )
    }
}