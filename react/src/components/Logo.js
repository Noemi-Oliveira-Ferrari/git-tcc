import React, { Component } from 'react';
import '../css/App.css';
import LogoImg from '../img/logo.png';


export class Logo extends Component{
   render(){
    return(
         
            <div className={this.props.classLogo}>
                <figure>
                    <img src={LogoImg} />
                </figure>
            </div>
    );
   }

}

export default Logo;