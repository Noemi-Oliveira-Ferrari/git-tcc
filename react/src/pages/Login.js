import React, { Component } from 'react';
import '../css/login.css';
import DaUmHelp from '../components/DaUmHelp';
import InputLogin from '../components/InputLogin';
import {Link} from 'react-router';
import Logo from '../components/Logo';


export class Login extends Component{
   render(){
    return(
      <div className="container-conteudo-login">
         <div className="container-login">
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
                              classNome="titulo">
                           </DaUmHelp>

                           
                           <form method="POST" action="login.html" name="frm_login">
                                 <div className="container-campos-login">
                                    <div className="container-txt-login">

                                    <InputLogin
                                       label="E-mail"
                                       type="email">
                                    </InputLogin>

                                    <InputLogin
                                       label="Senha"
                                       type="password">
                                    </InputLogin>
                                    </div>
                                    <button className="btn-entrar">ENTRAR</button> 
                                 </div>
                           </form>
                           <div className="texto-card">
                                 <p>Ainda não possui uma conta?<br></br>
                                    <Link className="link-cadastro" to="/escolha">
                                       Cadastre-se
                                    </Link>
                                 </p>
                           </div>
                        </div>
                     </div>
               </div>
            </div>
         </div>
   </div>




         // <div className="container-centro">
         //    <div className="caixa-texto">
         //       <div className="conteudo-caixa-texto">
         //          <h2>Comece a utilizar nossos serviços</h2>
         //       </div>
         //    </div>
         //    <div className="caixa-conteudo">
         //          <div className="card-login">
         //             <div className="conteudo-card">

         //                <Logo
         //                   classLogo="logotipo">
         //                </Logo>
                              
         //                <DaUmHelp
         //                   classNome="titulo">
         //                </DaUmHelp>

         //                   <form method="POST" action="login.html" name="frm_login">
         //                      <div className="container-campos-login">
         //                         <div className="container-txt-login">
         //                            <InputLogin
         //                               label="E-mail"
         //                               type="email">
         //                            </InputLogin>

         //                            <InputLogin
         //                               label="Senha"
         //                               type="password">
         //                            </InputLogin>
         //                         </div>
         //                         <Botao
         //                            classBotao="btn-entrar"
         //                            valueBotao="Entrar">
         //                         </Botao> 
         //                      </div>
         //                   </form>
         //                   <LinkCadastro>
         //                   </LinkCadastro>
         //             </div>
         //          </div>
         //       </div>
         // </div>
        

    );
   }

}

export default Login;