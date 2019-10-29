import React, { Component, Fragment } from 'react';
import '../js/carousel';
import '../js/index';
import '../js/index-slider-home';
import '../css/home.css';
import '../css/bootstrap.css';
import '../css/padroes.css';


export class Home extends Component{
   render(){
    return(
        <div className="tamanho-conteudo-slider-home">
        <div className="tamanho-caixa-slider-home">
            <section className="caixa-slider-home">
                <div className="conteudo-slider-home">
                    <h1 className="titulo-slider-home">DaUmHelp!</h1>
                    <p className="subtitulo-slider-home">A mais nova solução para os problemas do seu cotidiano.</p>
                    <input  className="text-slider-home" placeholder="Digite o serviço que precisa"/>
                    <button className="btn-slider-home">Entrar</button>
                </div>
            </section>
            {/* <div className='slider-home'>
                <div className='slide1-home'></div>
                <div className='slide2-home'></div>
                <div className='slide3-home'></div>
            </div> */}
        </div>
        <div className="tamanho-conteudo-empresa-home">
            <section className="caixa-sobre-empresa-home">
                <h1 className="titulo-empresa-home">Title Title</h1>
                <p className="subtitulo-empresa-home"> TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  </p>
                <div className="caixa-centro-conteudo-empresa-home">
                    <div className="caixa-conteudo-empresa-home">
                        <div className="caixa-imagem-empresa-home">
                            <div className="imagem-empresa-home"></div>
                        </div>
                        <p className="text-empresa">TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  TextText  </p>
                    </div>
                    <div className="caixa-conteudo-empresa-home">
                        <div className="caixa-imagem-empresa-home">
                            <div className="imagem-empresa-home"></div>
                        </div>
                        <p className="text-empresa">TextText  TextText  TextText TextText  TextText  TextText  TextText  TextText  TextText   TextText  TextText  TextTexTextText  TextText  TextTex  TextText  TextText  TextText  </p>
                    </div>
                    <div className="caixa-conteudo-empresa-home">
                        <div className="caixa-imagem-empresa-home">
                            <div className="imagem-empresa-home"></div>
                        </div>
                        <p className="text-empresa">TextText  TextText  TextText  TextText  TextText  TextText TextText  TextText  TextText TextText  TextText  TextTextTextText  TextText  TextTe TextText  TextText  TextText  </p>
                    </div>
                </div>
            </section>
        </div>
        <div className="tamanho-conteudo-profissional-home">
            <section className="section-profissional-home">
                <div className="caixa-profissonal-imagem-home">
                    <div className="borda-imagem-pro-home"></div>
                    <div className="caixa-branca-pro-home"></div>
                    <div className="imagem-profissional-home"></div>
                </div>
                <div className="caixa-profissonal-conteudo-home">
                    <h1 className="titulo-pro-home">Seja um Profissional</h1>
                    <p className="texto-pro-home">Diversas pessoas estão a procura de umprofissional com um determinado perfil,seja o próximo a ser contratado!</p>
                    <div className="caixa-itens-pro-home">
                        <div className="icon-image-home"></div>
                        <div className="text-list-empresa-home"> Divulgue e apareça</div>
                    </div>
                    <div className="caixa-itens-pro-home">
                        <div className="icon-image-home"></div>
                        <div className="text-list-empresa-home">Multiplique e rentabilize</div>
                    </div>
                    <div className="caixa-itens-pro-home">
                        <div className="icon-image-home"></div>
                        <div className="text-list-empresa-home">Diversas categorias e milhares de usuários</div>
                    </div>
                    <div className="caixa-itens-pro-home">
                        <div className="icon-image-home"></div>
                        <div className="text-list-empresa-home">Tudo organizado e sem complicação</div>
                    </div>
                </div>
            </section>
        </div>
        <section className="container-avaliacoes-home">
            <div className="caixa-titulo-avaliacao-home">
                <h1>Avaliações de nossos Usuários</h1>
            </div>
            <div className="conteudo-avaliacoes-home">
                <section className="customer-slider-avaliacao">
                    <div className="slide-avalicao-home">
                        <div className="imagem-comentario-usuario-home1"></div>
                        <h2 className="nome-comentario-home">TetxText</h2>
                        <hr className="linha-comentario-usuario-home"/>
                        <p className="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div className="caixa-btn-home">
                            <button className="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div className="slide-avalicao-home">
                        <div className="imagem-comentario-usuario-home2"></div>
                        <h2 className="nome-comentario-home">TetxText</h2>
                        <hr className="linha-comentario-usuario-home"/>
                        <p className="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div className="caixa-btn-home">
                            <button className="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div className="slide-avalicao-home">
                        <div className="imagem-comentario-usuario-home3"></div>
                        <h2 className="nome-comentario-home">TetxText</h2>
                        <hr className="linha-comentario-usuario-home"/>
                        <p className="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div className="caixa-btn-home">
                            <button className="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                    <div className="slide-avalicao-home">
                        <div className="imagem-comentario-usuario-home1"></div>
                        <h2 className="nome-comentario-home">TetxText</h2>
                        <hr className="linha-comentario-usuario-home"/>
                        <p className="texto-comentario-usuario-home">TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  TextText TextText  TextText  TextText  TextText  TextText TextText TextText TextText  </p>
                        <div className="caixa-btn-home">
                            <button className="ver-mais-comentario-home">Ver mais</button>
                        </div>
                    </div>
                </section>
            </div>
        </section>
    </div>

    );
   }

}

export default Home;