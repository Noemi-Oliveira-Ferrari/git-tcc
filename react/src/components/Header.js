import React, { Component, Fragment } from 'react';
import MenuResponsivo, { menuResponsivo } from '../js/indexmenu';
// CSS
import '../css/header.css';
import '../css/menu-resp.css';
import '../css/padroes.css';
// IMAGENS
import Avatar from '../img/avatar.png';
import LogoEscrito from '../img/daum_help.png';
import Logo from '../img/duh_icon.png';
import MenuIcon from '../img/menuu.png';
import Home from '../img/home.png';
import Fale from '../img/conversation.png';
import Quem from '../img/team.png';
// COMPONETES
import {Link} from 'react-router';
import HeaderLogin from './HeaderLogin';
import HeaderUsuario from './HeaderUsuario';


export default class Header extends Component{

    componentDidMount(){
        menuResponsivo();
    }

    render(){
        return(      
            <Fragment>  
                <header className="header-desktop">
                    <div className="caixa-header">
                        <div className="head-menu center">
                            <Link to="/" className="link">
                                <div className="logo-menu">
                                    <figure>
                                        <img src={LogoEscrito} alt="DaUmHelp!" title="DaUmHelp!"/>
                                    </figure>
                                </div>
                            </Link>
                            {/* <HeaderLogin/> */}
                            <HeaderUsuario/>
                        </div>
                    </div>
                    <div className="caixa-menu center flex-center">
                        <nav className="area-itens-menu flex-center">
                            <div className="item-menu-header flex-center"> 
                                Início
                            </div>
                            <div className="item-menu-header flex-center"> 
                                Sobre nós
                            </div> 
                            <div className="item-menu-header flex-center"> 
                                Contato
                            </div>
                        </nav>
                    </div>
                </header>   
                
                {/* //  MENU RESPONSIVO */}
                <header class="header-resp">
                    <div class="screen">
                        <div class="menu">
                            <ul>
                                <li>
                                    <img src={Home} class="icon-menu"/>
                                    <a href="">Home</a>
                                </li>
                                <li>
                                    <img src={Quem} class="icon-menu"/>
                                    <a href="">Quem Somos</a>
                                </li>
                                <li>
                                    <img src={Fale} class="icon-menu"/>
                                    <a href="">Fale conosco</a>
                                </li>
                            </ul>
                        </div>
                        <div class="content">
                            <div class="caixa1">
                                <div class="button">
                                    <figure>
                                        <img src={MenuIcon}/>
                                    </figure>
                                </div>
                                <div className="caixa-menu-resp flex-center">
                                    <figure>
                                        <img src={Logo}/>
                                    </figure>
                                </div>
                            </div>
                            {/* <div class="caixa2">
                                <div class="conteudo"></div>
                            </div> */}
                        </div>
                    </div>
            </header>   
        </Fragment>  
        );  
    }   
}
