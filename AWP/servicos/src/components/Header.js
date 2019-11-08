import React, { Component } from 'react';
import '../css/header.css';
import Avatar from '../img/avatar.png';
import Logo from '../img/daum_help.png';
import {Link} from 'react-router';


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
                        <div className="entrar-cadastro">
                            <div className="area-sing">
                                <figure><img src={Avatar} alt="Login" title="Login"/></figure>
                                <Link to="#" className="link"><div className="box-entrar flex-center">Entre</div></Link>
                                <Link to="/escolha" className="link"><div className="box-cadastrar flex-center">Cadastra-se</div></Link>
                            </div>
                        </div>
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
        );  
    }   
}
