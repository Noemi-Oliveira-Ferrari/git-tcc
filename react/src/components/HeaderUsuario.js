import React, { Component } from 'react';
import '../css/header.css';
import Avatar from '../img/ester.JPG';
import Not from '../img/bell.png';
import Config from '../img/gear.png';
import {Link} from 'react-router';


export default class HeaderUsuario extends Component{
    render(){
        return(        
           
                <div className="login-usuario">
                    <div className="avatar-usuario">
                        <figure><img src={Avatar} alt="Login" title="Login"/></figure>
                    </div>
                    <Link to="/app/profissional/perfil" className="link">
                        <div className="box-nome flex-center">
                            Ester Ribeiro
                        </div>
                    </Link>

                    <Link to="/" className="link">
                        <div className="box-sair">
                            <figure><img src={Not} alt="notificações" title="notificações"/></figure>
                            <figure><img src={Config} alt="configuraçoes" title="configuraçoes"/></figure>
                        </div>
                    </Link>
                </div>
        );  
    }   
}
