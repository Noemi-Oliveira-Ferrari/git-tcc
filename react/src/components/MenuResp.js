import React, { Component } from 'react';
import MenuResponsivo, { menuResponsivo } from '../js/indexmenu';
// css
import '../css/menu-resp.css';
import '../css/padroes.css';
// imagens
import Logo from '../img/duh_icon.png';
import MenuIcon from '../img/menuu.png';
import Home from '../img/home.png';
import Fale from '../img/conversation.png';
import Quem from '../img/team.png';


export default class MenuResp extends Component{

    componentDidMount(){
        menuResponsivo();
    }

    render(){
        return(        
            
            <div class="background">
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
                        <div class="caixa2">
                            <div class="conteudo"></div>
                        </div>
                    </div>
                </div>
        </div>     
        );  
    }   
}
