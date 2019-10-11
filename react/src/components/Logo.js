import React, { Component } from 'react';
import '../css/login.css';
import LogoImg from '../img/logo.png';


export class Logo extends Component{
   render(){
    return(
         
            <div className="logotipo">
                <figure>
                    <img src={LogoImg} alt="DaUmHelp!" title="DaUmHelp!"/>
                </figure>
            </div>
    );
   }

}

export default Logo;