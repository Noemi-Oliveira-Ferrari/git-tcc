
import '../css/App.css';
import '../utils/cardChooseAnimation';
import arrowRight from '../img/arrow-point-to-right.png';

import React, { Component } from 'react';


export class Escolha extends Component{
   render(){
    return(
        
        <div className={this.props.classCaixa}>
            <div className="title-escolha">
                {this.props.titulo}
            </div>
            <div className="texto-escolha"> 
                {this.props.texto}
            </div>
            <div className="img-escolha" >
                <figure>
                    <img className="img" src={this.props.img} alt=""/>
                </figure>
            </div>
            <div id={this.props.idBtn} className="botao-escolha">
                <div className="seta">
                    <figure>
                        <img className={this.props.seta} src={arrowRight} alt=""/>
                    </figure>
                </div>
            </div>
        </div>


    );
   }

}

export default Escolha;
