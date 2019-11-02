import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';

export class CapaPerfilPro extends Component{
   render(){
    return(
       <Fragment>
            <div class="capa-perfil-pro">
                klugkglkj
            </div>
            <div class="caixa-perfil">
            <div class="avatar" style={{backgroundImage: this.props.fotoPro}}>
            </div>
            
                <h1 class="nome-cliente flex-center">{this.props.nomePro}</h1>
                <div class="caixa-informacoes-basicas">
                    <div class="caixa-info flex-center">
                        <div class="caixa-info1">{this.props.servicoPro}</div>
                        <div class="caixa-info1">{this.props.localPro}</div>
                    </div>
                    <div class="caixa-info flex-center">
                        <div class="caixa-info1">{this.props.notaPro}</div>
                        <div class="caixa-info1">{this.props.valorPro}</div>
                    </div>
                </div>
            </div>
        </Fragment>
    );
   }

}

export default CapaPerfilPro;