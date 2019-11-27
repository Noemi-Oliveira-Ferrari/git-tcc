import React, { Component } from 'react';
import '../css/footer.css';
import PlayStore from '../img/google-play-badge.png';
import Facebook from '../img/facebook.png';
import Instagram from '../img/instagram.png';
import GitHub from '../img/github.png';
import Skype from '../img/skype.png';
import Snapchat from '../img/snapchat.png';
import Twiter from '../img/twitter.png';
import {Link} from 'react-router';
// import {}

import DaUmHelp from '../components/DaUmHelp';


export default class Footer extends Component{
   render(){
    return(
        <footer>
            <div className="caixa-rodape-cadastro">
                <div className="caixa-rodape">
                    <h2 className="titulo-rodape"> <DaUmHelp/> </h2>
                    {/* <h2 className="titulo-rodape">DaUmHelp!</h2> */}
                    <p className="texto-rodape">Se cadastre hoje, e comece a utilizar nossos serviços.</p>
                    <button className="btn-cadastro-rodape">Cadastre-se</button>
                </div>
            </div>
            <div className="caixa-rodape-informacoes">
                <div className="caixa2-rodape">
                    <div className="caixa-informacao-rodape">
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Empresa</h3>
                            <Link className="itens subtitulo-rodape" to="#">Sobre</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Serviços</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Contatos</Link><br></br>
                            <div className="tamanho-caixa">
                                <Link to="https://play.google.com">
                                    <img src={PlayStore} className="tamanho-icone" alt="Ícone Google Playstore" title="Google Playstore"/>
                                </Link>
                            </div>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Legal</h3>
                            <Link className="itens subtitulo-rodape" to="#">Termos/Condições</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Ajuda</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Política de cookies</Link><br></br>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">Ferramentas</h3>
                            <Link className="itens subtitulo-rodape" to="#">Apollo</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Athena</Link><br></br>
                            <Link className="itens subtitulo-rodape" to="#">Bluehost</Link><br></br>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape-responsiva">
                            <hr className="linha-responsiva"/>
                            <h3 className="text-responsive">Siga-nos:</h3>
                            <div className="caixa-rede-social">
                                <h3 className="text-desktop">Redes Sociais</h3>
                                <div className="tamanho-caixa-cima">
                                    <div className="logo-rede-social">
                                        <Link to="http://www.facebook.com.br">
                                            <img alt="Facebook" title="Facebook" src={Facebook} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.twitter.com.br">
                                            <img alt="Twitter" title="Twitter" src={Twiter} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.github.com.br">
                                            <img alt="GitHub" title="GitHub" src={GitHub} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.instagram.com.br">
                                            <img alt="Instagram" title="Instagram" src={Instagram} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.skype.com.br">
                                            <img alt="Skype" title="Skype" src={Skype} className="icon-footer"/>
                                        </Link>
                                    </div>
                                    <div className="logo-rede-social">
                                        <Link to="http://www.snapchat.com.br">
                                            <img alt="Snapchat" title="Snapchat" src={Snapchat} className="icon-footer"/>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div className="google-responsivo">
                                <p className="text-responsive">Disponível em:</p>
                                <div className="tamanho-caixa">
                                    <Link to="https://play.google.com">
                                        <img src={PlayStore} className="tamanho-icone-res" alt="Ícone Google PlayStore" title="Disponível na Google Playstore"/>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="caixa-info-rodape">                    
                    <hr className="linha"/>
                    <div className="caixa-copyright-rodape text-rodape">
                        <p>©2019 DaUmHelp. Todos os direitos reservados</p>
                    </div>
                    <div className="caixa-info-itens-rodape">
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


