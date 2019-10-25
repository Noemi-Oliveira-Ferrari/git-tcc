import React, { Component } from 'react';
import '../css/login.css';
import {Link} from 'react-router';
import { Botao } from '../components/Botao';
import {Inputs} from '../components/FormElements';
import IconLogin from '../img/log-in.png';

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
									<div className="titulo-card-login center flex-center">
										<h2>Bem-Vindo à DaUmHelp!</h2>
									</div>
									<div className="icon-login center">
										<figure>
											<img alt="Login" title="Login" src={IconLogin}/>
										</figure>
									</div>

									<form method="POST"name="frm_login">
										<div className="container-campos-login">
											<div className="container-txt-login">

												{/* <InputLogin
												label="E-mail"
												type="email">
												</InputLogin> */}
												<Inputs 
												classDivInput="caixa-campo-login"
												label="E-mail:"
												classInput="input-login"
												type="email"
												name="txt_email"/>

												<Inputs 
												classDivInput="caixa-campo-login"
												label="Senha:"
												classInput="input-login"
												type="password"
												name="txt_senha"/>

											</div>
											<Botao
												idBotao="btn-entrar"
												classBotao="btn-entrar"
												typeBotao="submit"
												valueBotao="ENTRAR"
											/>
											{/* // <button  className="btn-entrar">ENTRAR</button>  */}
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
		);
	}

}

export default Login;