import React, { Component, Fragment } from 'react';
import '../css/servicos-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import { Inputs, InputNumber } from '../components/FormElements';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';


export class ServicosPro extends Component{
   render(){
    return(
       <Fragment>
                <div class="capa-perfil-pro">
            </div>
            <div class="caixa-servicos">
                <div class="avatar">
                </div>
                <h1 class="nome-cliente">Seus Pedidos</h1>
                <div class="caixa-informacoes-basicas-servicos">
                    <p>Olá Fulano, aqui você pode monitorar o estado dos seus pedidos</p>
                </div>
            </div>
            <div class="caixa-conteudo-servico-pro">
                <div class="conteudo-pro-servico">
                    <div class="caixa-informacoes-pendentes">
                        <div class="caixa-conteudo-informacoes-pendentes">
                            <div class="text-dados">
                                <h3>Pendentes</h3>
                            </div>
                            <div class="caixa-servico-pendente">
                                <h3 class="title-pendente">TitleTitle</h3>
                                <hr class="linha-servico-pendente"/>
                                <p class="text-pendente">Type something hereTypfghhsmjhdkjkghsgh ghyghrsy somefdhgtfhghgfthing hereType ething yfvbdgjsjujujk hereyfgfngfhsfdjkjhgdpe something mnjhgjgd here</p>
                                <div class="caixa-link-pendente">
                                    <button class="link-pendente">Visualizar serviço</button>
                                </div>
                            </div>
                            <div class="caixa-servico-pendente">
                                <h3 class="title-pendente">TitleTitle</h3>
                                <hr class="linha-servico-pendente"/>
                                <p class="text-pendente">Type something hereTypfghhsmjhdkjkghsgh ghyghrsy somefdhgtfhghgfthing hereType ething yfvbdgjsjujujk hereyfgfngfhsfdjkjhgdpe something mnjhgjgd here</p>
                                <div class="caixa-link-pendente">
                                    <button class="link-pendente">Visualizar serviço</button>
                                </div>
                            </div>
                            <div class="caixa-servico-pendente">
                                <h3 class="title-pendente">TitleTitle</h3>
                                <hr class="linha-servico-pendente"/>                                
                                <p class="text-pendente">Type something hereTypfghhsmjhdkjkghsgh ghyghrsy somefdhgtfhghgfthing hereType ething yfvbdgjsjujujk hereyfgfngfhsfdjkjhgdpe something mnjhgjgd here</p>
                                <div class="caixa-link-pendente">
                                    <button class="link-pendente">Visualizar serviço</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="caixa-informacoes-concluidos">
                        <div class="caixa-comentarios-servicos">
                            <div class="text-dados">
                                <h3>Concluídos</h3>
                            </div>
                            <div class="caixa-comentario-usuario-servico">
                                <div class="dados-usuario-servico">
                                    <h4 class="titulo-comentario-servico">TextText TextText</h4>
                                    <hr class="linha-concluidos"/>
                                    <div class="caixa-star">
                                        <div class="estrelas">
                                            <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                                            <label for="cm_star-1"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-1" name="fb" value="1"/>
                                            <label for="cm_star-2"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-2" name="fb" value="2"/>
                                            <label for="cm_star-3"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-3" name="fb" value="3"/>
                                            <label for="cm_star-4"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-4" name="fb" value="4"/>
                                            <label for="cm_star-5"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                                        </div>
                                    </div>
                                    <p class="texto-comentario">Typomething herehjtyutyjyujuyj Type something ghatgh ghcf\bcccccccccccathtg hereType something here.</p>
                                    <div class="caixa-btn">
                                        <button class="btn-ver-mais">Ver Mais</button>
                                    </div>
                                </div>
                            </div>
                            <div class="caixa-comentario-usuario-servico">
                                <div class="dados-usuario-servico">
                                    <h4 class="titulo-comentario-servico">TextText TextText</h4>
                                    <hr class="linha-concluidos"/>
                                    <div class="caixa-star">
                                        <div class="estrelas">
                                            <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                                            <label for="cm_star-1"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-1" name="fb" value="1"/>
                                            <label for="cm_star-2"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-2" name="fb" value="2"/>
                                            <label for="cm_star-3"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-3" name="fb" value="3"/>
                                            <label for="cm_star-4"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-4" name="fb" value="4"/>
                                            <label for="cm_star-5"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                                        </div>
                                    </div>
                                    <p class="texto-comentario">Typomething herehjtyutyjyujuyj Type something ghatgh ghcf\bcccccccccccathtg hereType something here.</p>
                                    <div class="caixa-btn">
                                        <button class="btn-ver-mais">Ver Mais</button>
                                    </div>
                                </div>
                            </div>
                            <div class="caixa-comentario-usuario-servico">
                                <div class="dados-usuario-servico">
                                    <h4 class="titulo-comentario-servico">TextText TextText</h4>
                                    <hr class="linha-concluidos"/>
                                    <div class="caixa-star">
                                        <div class="estrelas">
                                            <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                                            <label for="cm_star-1"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-1" name="fb" value="1"/>
                                            <label for="cm_star-2"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-2" name="fb" value="2"/>
                                            <label for="cm_star-3"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-3" name="fb" value="3"/>
                                            <label for="cm_star-4"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-4" name="fb" value="4"/>
                                            <label for="cm_star-5"><i class="fa"></i></label>
                                            <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                                        </div>
                                    </div>
                                    <p class="texto-comentario">Typomething herehjtyutyjyujuyj Type something ghatgh ghcf\bcccccccccccathtg hereType something here.</p>
                                    <div class="caixa-btn">
                                        <button class="btn-ver-mais">Ver Mais</button>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>


    );
   }

}

export default ServicosPro;