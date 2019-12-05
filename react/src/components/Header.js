import React, { Component, Fragment } from 'react';
import '../css/header.css';
import Avatar from '../img/avatar.png';
import Logo from '../img/daum_help.png';
import ServicosIcon from '../img/suitcase.png';
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
                <Link to="/login" className="link"><div className="box-entrar flex-center">Entre</div></Link>
                <Link to="/escolha" className="link"><div className="box-cadastrar flex-center">Cadastra-se</div></Link>
            </div>
        );  
    }   
}
export class HeaderUsuario extends Component{

    constructor(){
        super();
        this.state ={
            username: getUsuario().nome,
            imgPerfil: getUsuario().foto !== "" ? getUsuario().foto !== null ? `${DOMINIO_IMG}${getUsuario().foto}` : IMG_PERFIL_PADRAO : IMG_PERFIL_PADRAO
        }
        this.logout = this.logout.bind(this);
        this.updateHeader = this.updateHeader.bind(this);
    }

    logout(event){
        localStorage.clear();
        setTimeout(() => {
            browserHistory.push("/login");
        }, 600);
    }

    componentDidMount(){
        this.updateHeader();
    }

    updateHeader(){
        if(getToken() !== null && getToken() !== ""){
            setInterval(() => {
                console.log("atualizar header!!!");
                console.log(getUsuario().nome);
                this.setState({imgPerfil: getUsuario().foto !== "" ? getUsuario().foto !== null ? `${DOMINIO_IMG}${getUsuario().foto}` : IMG_PERFIL_PADRAO : IMG_PERFIL_PADRAO});
                this.setState({username: getUsuario().nome});
            }, 60000);
        }
    }


    // render(){

    //     return(
    //         <div className="login-usuario">
    //             <div className="avatar-usuario" style={{backgroundImage: `url(${this.state.imgPerfil})`}}>
    //                 {/* <figure><img src={this.state.imgPerfil} alt="Login" title="Login"/></figure> */}
    //             </div>
    //             <Link to="/app/profissional/perfil" className="link">
    //                 <div className="box-nome flex-center">
    //                     {this.state.username}
    //                 </div>
    //             </Link>

    //             {/* <Link to="/" className="link"> */}
    //                 <div className="box-sair">
    //                     {/* <Link to="/app/profissional/servicos" className="link">
    //                         <figure>
    //                             <img src={Not} alt="Notificações" title="Notificações"/>
    //                         </figure> 
    //                     </Link>  */}
    //                     <figure>
    //                         <img src={Logout} alt="Deslogar" title="Deslogar" onClick={this.logout}/>
    //                     </figure>
    //                 </div>
    //             {/* </Link> */}
    //         </div>
    //     );  
    // }   
    render(){

        return(
            <div className="login-usuario">
                <Link to="/app/profissional/perfil" className="link">
                    <div className="avatar-usuario" style={{backgroundImage: `url(${this.state.imgPerfil})`}}></div>
                </Link>
                    <div className="box-nome">
                        <Link to="/app/profissional/perfil" className="link">
                            <span className="username-header">{this.props.username}</span>
                        </Link>
                        <Link to="/app/profissional/servicos" className="link">
                            <div className="servicos-header" style={{backgroundImage: `url(${ServicosIcon})`}}></div>
                        </Link>
                    </div>

                {/* <Link to="/" className="link"> */}
                    <div className="box-sair fle">
                        {/* <Link to="/app/profissional/servicos" className="link">
                            <figure>
                                <img src={Not} alt="Notificações" title="Notificações"/>
                            </figure> 
                        </Link>  */}
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
            <Fragment>
                <header id="header-desktop">
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
                            <Link className="link" to="/contato" >
                                <div className="item-menu-header flex-center"> 
                                    Contato
                                </div> 
                            </Link>
                        </nav>
                    </div>
                </header>    

            
                <header id="header-mobile">
                    <div className="caixa-header-mobile">
                        <div className="head-menu-mobile">
                            <Link to="/" className="link">
                                <div className="logo-menu-mobile">
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
                        </div>
                    </div>
                    <div className="caixa-menu-mobile flex-center">
                        <nav className="area-itens-menu-mobile">
                            <Link className="link" to="/" > 
                                <div className="item-menu-header-mobile-home flex-center"> 
                                    
                                </div>
                            </Link>
                            <Link className="link" to="/sobrenos" >
                                <div className="item-menu-header-mobile-sobrenos flex-center"> 
                                    
                                </div> 
                            </Link>
                            <Link className="link" to="/faq" >
                                <div className="item-menu-header-mobile-faq flex-center"> 
                                    
                                </div> 
                            </Link>
                        </nav>
                    </div>
                </header>       
            </Fragment>   
        );  
    }   
}
