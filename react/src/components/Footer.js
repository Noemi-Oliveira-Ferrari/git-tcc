import React, { Component } from 'react';
import '../css/footer.css';
import { DaUmHelp } from '../components/DaUmHelp';
import PlayStore from '../img/google-play-badge.png';
import Facebook from '../img/facebook.png';
import Instagram from '../img/instagram.png';
import GitHub from '../img/github.png';
import Skype from '../img/skype.png';
import Snapchat from '../img/snapchat.png';
import Twiter from '../img/twitter.png';
import {Link} from 'react-router';


export default class Footer extends Component{
   render(){
    return(
        <footer>
            <div className="caixa-rodape-cadastro">
                <div className="caixa-rodape">
                    {/* <DaUmHelp
                        className="titulo-rodape">
                    </DaUmHelp> */}
                    <h2 className="titulo-rodape">DaUmHelp!</h2>
                    <p className="texto-rodape">Se cadastre hoje, e comece a utilizar nossos serviços.</p>
                    <button className="btn-cadastro-rodape">Cadastre-se</button>
                </div>
            </div>
            <div className="caixa-rodape-informacoes">
                <div className="caixa2-rodape">
                    <div className="caixa-informacao-rodape">
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Empresa</h3>
                            <Link className="itens subtitulo-rodape" to="#">Sobre</Link>
                            <Link className="itens subtitulo-rodape" to="#">Serviços</Link>
                            <Link className="itens subtitulo-rodape" to="#">Contatos</Link>
                            <div className="tamanho-caixa">
                                <Link to="https://play.google.com">
                                    <img src={PlayStore} className="tamanho-icone"/>
                                </Link>
                            </div>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Legal</h3>
                            <Link className="itens subtitulo-rodape" to="#">Termos/Condições</Link>
                            <Link className="itens subtitulo-rodape" to="#">Ajuda</Link>
                            <Link className="itens subtitulo-rodape" to="#">Política de cookies</Link>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Ferramentas</h3>
                            <Link className="itens subtitulo-rodape" to="#">Apollo</Link>
                            <Link className="itens subtitulo-rodape" to="#">Athena</Link>
                            <Link className="itens subtitulo-rodape" to="#">Bluehost</Link>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape-responsiva">
                            <hr className="linha-responsiva"/>
                            <h3 className="text-responsive">Siga-nos:</h3>
                            <div className="caixa-rede-social">
                                <h3 className="text-desktop">Redes Sociais</h3>
                                <div className="tamanho-caixa-cima">
                                    <div className="logo-rede-social">
                                        <Link to="http://www.facebook.com.br">
                                            <img src={Facebook} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.twitter.com.br">
                                            <img src={Twiter} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.github.com.br">
                                            <img src={GitHub} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.instagram.com.br">
                                            <img src={Instagram} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.skype.com.br">
                                            <img src={Skype} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.snapchat.com.br">
                                            <img src={Snapchat} className="icon-footer"/>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div className="google-responsivo">
                                <p className="text-responsive">Disponível em:</p>
                                <div className="tamanho-caixa">
                                    <Link to="https://play.google.com">
                                        <img src={PlayStore} className="tamanho-icone-res"/>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="caixa3-rodape">                    
                    <hr className="linha"/>
                    <div className="caixa-1 text-rodape">
                        <p>©2019 DaUmHelp. Todos os direitos reservados</p>
                    </div>
                    <div className="caixa-2">
                        <div className="text-rodape">
                            <Link className="itens-rodape">Mais Informações</Link>
                        </div>
                        <div className="text-rodape">
                            <Link className="itens-rodape">Sobre Nós</Link>
                        </div>
                        <div className="text-rodape">
                            <Link className="itens-rodape">Outras</Link>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    );
   }

}


