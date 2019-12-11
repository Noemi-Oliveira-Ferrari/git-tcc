import React, { Component } from 'react';
import PlayStore from '../img/google-play-badge.png';
import Icons8 from '../img/icons8.png';
import Instagram from '../img/instagram.png';
import GitHub from '../img/github.png';
import Skype from '../img/skype.png';
import Snapchat from '../img/snapchat.png';
import Flaticon from '../img/flaticon.png';
import {Link} from 'react-router';
import DaUmHelp from '../components/DaUmHelp';
import {DAUMHELP_APK} from '../global';
import '../css/footer.css';




const be = "Brace {Everything}"
// import {}



export default class Footer extends Component{
   render(){
    return(
        <footer>
            <div className="caixa-rodape-cadastro">
                <div className="caixa-rodape">
                    <h2 className="titulo-rodape"> <DaUmHelp/> </h2>
                    {/* <h2 className="titulo-rodape">DaUmHelp!</h2> */}
                    <p className="texto-rodape">Se cadastre hoje, e comece a utilizar nossos serviços.</p>
                    <Link className="link" to="/escolha"><div className="btn-cadastro-rodape flex-center">Cadastre-se</div></Link>
                </div>
            </div>
            <div className="caixa-rodape-informacoes">
                <div className="caixa2-rodape">
                    <div className="caixa-informacao-rodape">
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">
                                Empresa
                            </h3>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/sobrenos">
                                    Sobre
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/faq">
                                    FAQ
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/contato">
                                    Contato
                                </Link>
                            </div>
                            <div className="tamanho-caixa">
                            <a href={DAUMHELP_APK} download>
                                    <img src={PlayStore} className="tamanho-icone" alt="Ícone Google PlayStore" title="Disponível na Google Playstore"/>
                                </a>
                            </div>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">
                                Legal
                            </h3>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="#">
                                    Ajuda
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="#">
                                    Termos e Condições
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="#">
                                    Políticas de Privacidade
                                </Link>
                            </div>
                        </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape1">
                            <h3 className="titulos-rodape">
                                Ferramentas
                            </h3>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/#">
                                    Git
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/#">
                                    MS Project
                                </Link>
                            </div>
                            <div className="itens-info-rodape">
                                <Link className="itens subtitulo-rodape" to="/#">
                                    Planner
                                </Link>
                            </div>
                            </div>
                        <div className="caixas-conteudo-rodape caixas-conteudo-rodape-responsiva">
                            <hr className="linha-responsiva"/>
                            <h3 className="text-responsive">Links Externos</h3>
                            <div className="caixa-rede-social">
                                <h3 className="text-desktop">Link Externos</h3>
                                <div className="tamanho-caixa-cima">
                                    <div className="logo-rede-social">
                                        <a href="http://www.icons8.com.br">
                                            <img alt="Icons8" title="Icons8" src={Icons8} className="icon-footer"/>
                                        </a>
                                    </div>
                                    <div className="logo-rede-social">
                                        <a href="http://www.flaticon.com">
                                            <img alt="Flaticon" title="Flaticon" src={Flaticon} className="icon-footer"/>
                                        </a>
                                    </div>
                                    <div className="logo-rede-social">
                                        <a href="http://www.github.com.br">
                                            <img alt="GitHub" title="GitHub" src={GitHub} className="icon-footer"/>
                                        </a>
                                    </div>
                                    <div className="logo-rede-social">
                                        <a href="http://www.instagram.com.br">
                                            <img alt="Instagram" title="Instagram" src={Instagram} className="icon-footer"/>
                                        </a>
                                    </div>
                                    <div className="logo-rede-social">
                                        <a href="http://www.skype.com.br">
                                            <img alt="Skype" title="Skype" src={Skype} className="icon-footer"/>
                                        </a>
                                    </div>
                                    <div className="logo-rede-social">
                                        <a href="http://www.snapchat.com.br">
                                            <img alt="Snapchat" title="Snapchat" src={Snapchat} className="icon-footer"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div className="google-responsivo">
                                {/* <p className="text-responsive">Disponível em:</p> */}
                                <div className="tamanho-caixa-res">
                                    <a href={DAUMHELP_APK} download>
                                        <img src={PlayStore} className="tamanho-icone-res" alt="Ícone Google PlayStore" title="Disponível na Google Playstore"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="caixa-info-rodape">                    
                    <hr className="linha"/>
                    <div className="caixa-copyright-rodape text-rodape">
                        ©2019 DaUmHelp. Todos os direitos reservados
                    </div>
                    <div className="caixa-info-itens-rodape">
                        <div className="text-rodape">
                            {/* <Link className="itens-rodape">Mais Informações</Link> */}
                            Uma plataforma <span style={{fontFamily: 'Anonymous'}, {fontSize: '18px'}}>{be}</span>
                        </div>
                        {/* <div className="text-rodape">
                            <Link className="itens-rodape">Sobre Nós</Link>
                        </div>
                        <div className="text-rodape">
                            <Link className="itens-rodape">Outras</Link>
                        </div> */}
                    </div>
                </div>
            </div>
        </footer>
    );
   }

}


