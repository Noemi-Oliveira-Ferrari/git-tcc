import React, { Component } from 'react';
import '../css/login.css';


export class Botao extends Component{
    render(){
        return(
            <button 
                className={this.props.classBotao} 
                id={this.props.idBotao} 
                name={this.props.nameBotao}
                type={this.props.typeBotao}
                onClick={this.props.clickBotao}
            >
                {this.props.valueBotao}
            </button>
        );
    }
}

export class BotaoImg extends Component{
    render(){
        return(
            <button 
                className={this.props.classBotao} 
                id={this.props.idBotao} 
                name={this.props.nameBotao}
                type={this.props.typeBotao}
                onClick={this.props.clickBotao}
            >
                <figure>
                    <img id={this.props.idImgBotao} src={this.props.imgBotao} alt={this.props.altImg} title={this.props.titleImg}/>
                </figure>
            </button>
        );
    }
}
