import React, { Component, Fragment } from 'react';
import '../css/login.css';
import {Link, browserHistory} from 'react-router';
import { Botao } from '../components/Botao';
import {Inputs} from '../components/FormElements';
import IconLogin from '../img/log-in.png';
import {DOMINIO} from '../global';
import { getToken, getUsuario, getTipoLogado } from '../utils/verificaSessionStrg';
import $ from 'jquery';
import axios from 'axios';
import { ModalLoadConst, ModalAlertas } from '../components/ModaisLoad';
import { generateHash, validarEmail } from '../js/validar';
import jwtDecoder from 'jwt-decode';
import DaUmHelp from '../components/DaUmHelp';



export class Login extends Component{

	constructor(){
		super();
		this.state = {
			email: "", senha: "",
            loading: false,
            showModalErro: false,
			erros: [],
			tipoAlerta: "",
			titleAlt: ""
		}
        this.modalLoad = this.modalLoad.bind(this);
        this.ModalAlertas = this.ModalAlertas.bind(this);
		this.enviarLogin = this.enviarLogin.bind(this);
		this.setEmail = this.setEmail.bind(this);
		this.setSenha = this.setSenha.bind(this);
		this.buscarProfissional = this.buscarProfissional.bind(this);
		// this.buscarCliente = this.buscarCliente.bind(this);
	}
	
    componentWillMount(){
        let token = getToken();
        if(token !== null && token !== ""){
            browserHistory.push(`app/${getTipoLogado()}/perfil`);
        }
    }


    ModalAlertas = () =>{
        this.setState({showModalErro: !this.state.showModalErro});
    }
    
    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
	}
    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        this.setState({tipoAlerta: "erroAlt"});
        this.setState({titleAlt: "ERRO!"});
        setTimeout(()=>{this.ModalAlertas();}, 500);
    }
	
	enviarLogin(event){
		event.preventDefault();
		let email = this.state.email;
		let senha = generateHash(this.state.senha);
		console.log(email+" \n"+senha);
		this.buscarProfissional();
	}
	buscarProfissional(){
		axios({
			method: "POST",
			// url: `${DOMINIO}clientes/id/1`,
			url: `${DOMINIO}login/profissional`,
			data: {
				email: this.state.email,
				senha: generateHash(this.state.senha)
			},
			timeout: 30000
		})
		.then((response)=>{
			let tokenJson = response.data;
			let tokenDecoded = jwtDecoder(tokenJson.token);
			let jsonPro = tokenDecoded.profissional;
			let token = tokenJson.token;
			console.log(jsonPro);
			
			if(jsonPro === null || jsonPro === ''){
				console.log("pro nao encontrado");
				// this.buscarCliente();
			}else{
				console.log("pro logado");
				this.logarUsuario(jsonPro, "profissional", token);
			}
			setTimeout(()=>{this.modalLoad()}, 500);
		})
		.catch(error=>{
			console.log("sem conexao pro");
			console.log(error);
			if(error.message === "Network Error"){
				this.noConnection();
			}else{
				console.log("pro nao encontrado");
				this.buscarCliente();
			}
			this.modalLoad();
		})
		.onload = this.modalLoad();
	}
	buscarCliente(){		
		let erros = [];
		axios({
			method: "POST",
			url: `${DOMINIO}login/cliente`,
			data: {
				email: this.state.email,
				senha: generateHash(this.state.senha)
			},
			timeout: 30000
		})
		.then((response)=>{
			let tokenJson = response.data;
			let tokenDecoded = jwtDecoder(tokenJson.token);
			let jsonCliente = tokenDecoded.cliente;
			let token = tokenJson.token;
			console.log(jsonCliente);

			console.log(jsonCliente);
			console.log(token);

			if(jsonCliente === null || jsonCliente === ''){
				erros = [`Nenhum cliente com essas credenciais foi encontrado.`];
				this.setState({erros: erros});
				this.setState({tipoAlerta: "alertaAlt"});
				this.setState({titleAlt: "ERRO!"});
				setTimeout(()=>{this.ModalAlertas();}, 200);
			}else{
				this.logarUsuario(jsonCliente, "cliente", token);
				console.log("cliente logado");
			}
			setTimeout(()=>{this.modalLoad()}, 500);
		})
		.catch((error)=>{
			console.log("sem conexao cliente");
			if(error.message === "Network Error"){
				this.noConnection();
			}else{
				console.log("cliente nao encontrado");
				erros = [`Nenhum usuário com essas credenciais foi encontrado.`];
				this.setState({erros: erros});
				this.setState({tipoAlerta: "alertaAlt"});
				this.setState({titleAlt: "ERRO!"});
				setTimeout(()=>{this.ModalAlertas();}, 200);
			}
			this.modalLoad();
		})
		.onload = this.modalLoad();
	}

	logarUsuario(jsonUser, user, token){
		localStorage.clear();
		localStorage.setItem(user, JSON.stringify(jsonUser));
		localStorage.setItem("app", user);
		localStorage.setItem("token", token);
		if(user === "profissional"){
			localStorage.setItem("categoria", jsonUser.subcategoria.categoria.idCategoria);
			localStorage.setItem("subcategoria", jsonUser.subcategoria.idSubcategoria);
			browserHistory.push(`/app/${user}/servicos`);
		}else{
			browserHistory.push(`/app/${user}/perfil`);
		}

	}


    setEmail(event){
        this.setState({email: event.target.value});
        console.log(event.target.value);
        validarEmail(event.target);
    }

    getEmail(event){
        this.setState({email: event.target.value});
        let email = event.target.value;
        console.log(email);
        if(email.length > 5){
            let erros = [];

            axios({
                method: "GET",
                url: `${DOMINIO}profissionais/email/${email}`,
                timeout: 30000
            })
            .then((response)=>{
                let jsonPro = response.data;
                console.log(jsonPro);
                if(jsonPro !== null && jsonPro !== ''){
					$("txt-senha").attr("disabled", false);
                }
                setTimeout(()=>{this.modalLoad()}, 500);
            })
            .catch((error)=>{
                this.noConnection();
                this.modalLoad();
            })
            .onload = this.modalLoad();
        }   
    }

	
	setSenha(event){
		this.setState({senha: event.target.value});
		console.log(event.target.value);
	}

   	render(){
		return(
            <Fragment>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <ModalAlertas tipoAlerta={this.state.tipoAlerta} titulo={this.state.titleAlt} erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
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
											<h2>Bem-Vindo à <DaUmHelp/> </h2>
											{/* <h2>Bem-Vindo à <span cl1ass="titulo-daumhelp">DaUmHelp!</span></h2> */}
										</div>
										<div className="icon-login center">
											<figure>
												<img alt="Login" title="Login" src={IconLogin}/>
											</figure>
										</div>

										<form method="POST" name="frm_login" onSubmit={this.enviarLogin}>
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
													name="txt_email"
													id="txt-email-login"
													onChange={this.setEmail}
													// onBlur={this.getEmail}
													disabled="false"/>

													<Inputs 
													classDivInput="caixa-campo-login"
													label="Senha:"
													classInput="input-login"
													type="password"
													name="txt_senha"
													id="txt-senha-login"
													onChange={this.setSenha}
													disabled="true"/>

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
			</Fragment>
		);
	}

}

export default Login;