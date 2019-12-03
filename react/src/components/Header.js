import React, { Component } from 'react';
import '../css/header.css';
import Avatar from '../img/avatar.png';
import Logo from '../img/daum_help.png';
import {Link, browserHistory} from 'react-router';
import FotoPerfil from '../img/ester.JPG';
import Not from '../img/bell.png';
import Logout from '../img/log-out.png';
import { getToken, getUsuario, getTipoLogado } from '../utils/verificaSessionStrg';
import {DOMINIO_IMG, IMG_PERFIL_PADRAO} from '../global';

export class HeaderLogin extends Component{
    render(){
        return(
            <div className="entrar-cadastro">
                <Link to="/" className="link"><div className="box-entrar flex-center">Entre</div></Link>
                <Link to="/escolha" className="link"><div className="box-cadastrar flex-center">Cadastra-se</div></Link>
            </div>
        );  
    }   
}
export class HeaderUsuario extends Component{

    constructor(){
        super();
        this.state ={
            imgPerfil: getUsuario().foto !== "" ? getUsuario().foto !== null ? `${DOMINIO_IMG}${getUsuario().foto}` : IMG_PERFIL_PADRAO : IMG_PERFIL_PADRAO
        }
        this.logout = this.logout.bind(this);
        this.teste = this.teste.bind(this);
    }

    logout(event){
        localStorage.clear();
        setTimeout(() => {
            browserHistory.push("/");
        }, 600);
    }

    componentDidMount(){
        console.log("99999999999999999999999999999");
        console.log(this.state.imgPerfil);
        this.teste();
    }

    teste(){
        setInterval(() => {
            console.log("atualizar header!!!");
            this.setState({imgPerfil: getUsuario().foto !== "" ? getUsuario().foto !== null ? `${DOMINIO_IMG}${getUsuario().foto}` : IMG_PERFIL_PADRAO : IMG_PERFIL_PADRAO});
        }, 60000);
    }


    render(){

        return(
            <div className="login-usuario">
                <div className="avatar-usuario" style={{backgroundImage: `url(${this.state.imgPerfil})`}}>
                    {/* <figure><img src={this.state.imgPerfil} alt="Login" title="Login"/></figure> */}
                </div>
                <Link to="/app/profissional/perfil" className="link">
                    <div className="box-nome flex-center">
                        {this.props.username}
                    </div>
                </Link>

                {/* <Link to="/" className="link"> */}
                    <div className="box-sair">
                        <Link to="/app/profissional/servicos" className="link">
                            <figure>
                                <img src={Not} alt="Notificações" title="Notificações"/>
                            </figure> 
                        </Link> 
                        <figure>
                            <img src={Logout} alt="Deslogar" title="Deslogar" onClick={this.logout}/>
                        </figure>
                    </div>
                {/* </Link> */}
            </div>
        );  
    }   
}


export default class Header extends Component{

    render(){
        return(        
            <header>
                <div className="caixa-header">
                    <div className="head-menu center">
                        <Link to="/" className="link">
                            <div className="logo-menu">
                                <figure>
                                    <img src={Logo} alt="DaUmHelp!" title="DaUmHelp!"/>
                                </figure>
                            </div>
                        </Link>
                        {
                            getToken() !== "" ? 
                                getToken() !== null ? 
                                    <HeaderUsuario
                                        username={getUsuario().nome}
                                    /> 
                                : <HeaderLogin/> 
                            : <HeaderLogin/>
                        }
                        {/* { 1 === 1 ? console.log(">>>>>> "+getToken() !== "") : ""}  */}
                        {/* <HeaderLogin/> */}
                        {/* <HeaderUsuario/> */}
                    </div>
                </div>
                <div className="caixa-menu center flex-center">
                    <nav className="area-itens-menu flex-center">
                        <Link className="link" to="/" > 
                            <div className="item-menu-header flex-center"> 
                                Início
                            </div>
                        </Link>
                        <Link className="link" to="/sobrenos" >
                            <div className="item-menu-header flex-center"> 
                                Sobre Nós
                            </div> 
                        </Link>
                        <Link className="link" to="/faq" >
                            <div className="item-menu-header flex-center"> 
                                FAQ
                            </div> 
                        </Link>
                    </nav>
                </div>
            </header>        
        );  
    }   
}
