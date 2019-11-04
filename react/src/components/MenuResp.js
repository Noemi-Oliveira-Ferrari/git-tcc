import React, { Component } from 'react';
import '../css/menubla.css';
import Avatar from '../img/avatar.png';
import Logo from '../img/daum_help.png';
import {Link} from 'react-router';
import HeaderLogin from './HeaderLogin';
import HeaderUsuario from './HeaderUsuario';
import MenuResponsivo, { menuResponsivo } from '../js/indexmenu';


export default class MenuResp extends Component{

    componentDidMount(){
        menuResponsivo();
    }

    render(){
        return(        
            
            <div class="background">
            {/* <!-- <div class="circle"></div> --> */}
        {/* <!--		<div class="iphone">--> */}
                <div class="screen">
                    <div class="menu">
                        <ul>
                            <li>
                                <img src="image/home-icon.png" class="icon-menu"/>
                                <a href="">Home</a>
                            </li>
                            <li>
                                <img src="image/home-icon.png" class="icon-menu"/>
                                <a href="">Quem Somos</a>
                            </li>
                            <li>
                                <img src="image/home-icon.png" class="icon-menu"/>
                                <a href="">Fale conosco</a>
                            </li>
                        </ul>
                    </div>
        {/* <!--				<div class="status"></div>--> */}
                    <div class="content">
                        <div class="caixa1">
                            <div class="button"></div>
                        </div>
                        <div class="caixa2">
                            <div class="conteudo"></div>
                        </div>
                    </div>
                </div>
            {/* <!-- </div> --> */}
        </div>     
        );  
    }   
}
