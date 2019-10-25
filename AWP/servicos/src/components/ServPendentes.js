import React, { Component } from 'react';
import '../css/servicos-pro.css';
import '../css/padroes.css';
import Cancel from '../img/letter-x.png'
import Analisar from '../img/magnifying-glass.png'

export class ServPendentes extends Component{
   render(){
    return(
         
        <div className="caixa-servico-pendente">
            <h3 className="title-pendente">Arrumar maquina de lavar</h3>
            <h6 className="cliente-pendente">Maria Gasolina, Barueri - SP</h6>
            <hr className="linha-servico-pendente"/>
            <p className="text-pendente">Minha maquina quebrou soltou o motor pegou fogo, nao esta mais funcionando a tampa aida ta inteira</p>
            <div className="caixa-link-pendente flex-center">
                    <figure >
                        <img alt="Ver mais" title="Ver mais" src={Analisar}/>
                    </figure>
            </div>
        </div>
    );
   }

}

export default ServPendentes;