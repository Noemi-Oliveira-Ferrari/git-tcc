import React, { Component } from 'react';
import '../css/header.css';
import Avatar from '../img/avatar.png';
import Logo from '../img/daum_help.png';
import {Link} from 'react-router';


export default class Header extends Component{
    render(){
        return(        
            <header>
                <div class="caixa-header">
                    <div class="head-menu center">
                        <Link to="/" className="link">
                            <div class="logo-menu">
                                <figure>
                                    <img src={Logo} alt="DaUmHelp!" title="DaUmHelp!"/>
                                </figure>
                            </div>
                        </Link>
                        <div class="entrar-cadastro">
                            <div class="area-sing">
                                <figure><img src={Avatar}/></figure>
                                <Link to="#" className="link"><div class="box-entrar flex-center">Entre</div></Link>
                                <Link to="#" className="link"><div class="box-cadastrar flex-center">Cadastra-se</div></Link>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="caixa-menu center flex-center">
                    <nav class="area-itens-menu flex-center">
                        <div class="item-menu-header flex-center"> 
                            Inicio
                        </div>
                        <div class="item-menu-header flex-center"> 
                            Sobre nós
                        </div> 
                        <div class="item-menu-header flex-center"> 
                            Contato
                        </div>
                    </nav>
                </div>
            </header>        
        );  
    }   
}
