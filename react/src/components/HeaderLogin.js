import React, { Component } from 'react';
import '../css/header.css';
import Avatar from '../img/avatar.png';
import {Link} from 'react-router';


export default class HeaderLogin extends Component{
    render(){
        return(        
           
                <div className="entre-cadastre">
                    <figure><img src={Avatar} alt="Login" title="Login"/></figure>
                    <Link to="/" className="link"><div className="box-entrar flex-center">Entre</div></Link>
                    <Link to="/escolha" className="link"><div className="box-cadastrar flex-center">Cadastra-se</div></Link>
                </div>
        );  
    }   
}
