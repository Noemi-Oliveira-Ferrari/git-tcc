import React from 'react';
import { Component } from 'react';

// css e js
import './css/App.css';
import './utils/cardChooseAnimation';

// componentes
import CardsEscolha from './components/CardsEscolha';
import InputLogin from './components/InputLogin';
import DaUmHelp from './components/DaUmHelp';

class App extends Component{
	render(){

	
  return (
	<div id="all">

{/* TELA DE ESCOLHA */}
		{/* <CardsEscolha>

		</CardsEscolha> */}

{/* TELA DO LOGIN */}
                <div className="container-centro">
                    <div className="caixa-texto">
                        <div className="conteudo-caixa-texto">
                            <h2>Comece a utilizar nossos serviços</h2>
                        </div>
                    </div>
                    <div className="caixa-conteudo">
                        <div className="card-login">
                            <div className="conteudo-card">
                                <div className="logotipo"></div>

								<DaUmHelp
									classNome="texto-centro titulo">
								</DaUmHelp>

                                <form>
                                    <div className="texto-centro">

										<InputLogin
											label="E-mail"
											type="email">
										</InputLogin>

										<InputLogin
											label="Senha"
											type="password">
										</InputLogin>

                                        <button className="btn-entrar">Entrar</button> 
                                    </div>
                                </form>
                                <div className="texto-card texto-direita">
                                    <p>Ainda não possui uma conta?
                                        <a className="link-cadastro" href="#">
                                            <b>Cadastre-se</b>
                                        </a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            {/* <!-- div para separa o conteudo do footer --> */}
            <div id="espaco">

            </div>
            <footer hidden>

            </footer>
        </div>



  );
}

}



export default App;
