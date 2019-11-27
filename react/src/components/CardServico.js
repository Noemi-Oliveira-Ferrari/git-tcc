import React, { Component } from 'react';
// CSS
import '../css/servicos-pro.css';
import '../css/padroes.css';
// IMAGENS
import Analisar from '../img/magnifying-glass.png'
import Estrelas from './Estrelas';

export class CardServico extends Component{
   render(){
    return(
         
        <div className="caixa-servico">
            <h3 className="title-servico">{this.props.titulo}</h3>
            <h6 className="endereco-cliente-servico">{this.props.enderecoCliente}</h6>
            <hr className="linha-servico"/>
            <div className={this.props.estrelas}>
                <Estrelas/>
            </div>
            <p className="text-servico">{this.props.comentario}</p>
            <div className="caixa-link flex-center">
                <figure>
                    <img alt="Ver mais" title="Ver mais" src={Analisar}/>
                </figure>
            </div>
        </div>
    );
   }

}

export default CardServico;