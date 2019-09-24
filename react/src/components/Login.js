import React, { Component } from 'react';
import '../css/App.css';
import DaUmHelp from './DaUmHelp';
import InputLogin from './InputLogin';
import LinkCadastro from './LinkCadastro';
import Logo from './Logo';
import Botao from './Botao';


export class Login extends Component{
   render(){
    return(
         <div className="container-centro">
            <div className="caixa-texto">
               <div className="conteudo-caixa-texto">
                  <h2>Comece a utilizar nossos serviços</h2>
               </div>
            </div>
            <div className="caixa-conteudo">
                  <div className="card-login">
                     <div className="conteudo-card">

                        <Logo
                           classLogo="logotipo">
                        </Logo>
                              
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

                                 <Botao
                                    classBotao="btn-entrar"
                                    valueBotao="Entrar">
                                 </Botao> 
                              </div>
                           </form>
                           <LinkCadastro>
                           </LinkCadastro>
                     </div>
                  </div>
               </div>
         </div>
        

    );
   }

}

export default Login;