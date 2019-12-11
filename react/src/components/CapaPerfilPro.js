import React, { Component, Fragment } from 'react';
import '../css/capa-perfil-pro.css';
import axios from 'axios';
import { Botao } from '../components/Botao';
import { DOMINIO, DOMINIO_IMG } from '../global';
import { getTipoLogado, getUsuario, getToken,setUsuarioPro, verificarLogado } from '../utils/verificaSessionStrg';
import {ModalLoadConst, ModalAlertas} from './ModaisLoad';
import ToTop from './ToTop';
import $ from 'jquery';
import iconServico from '../img/servico.png';
import iconLocal from '../img/local.png';
import iconAvaliacao from '../img/star.png';
import iconValor from '../img/money.png';

verificarLogado();

export class CapaPerfilPro extends Component{
   render(){
    return(
       <Fragment>
            <div class="capa-perfil-pro">

            </div>
            <div class="caixa-perfil">
            <div class="avatar" style={{backgroundImage: this.props.fotoPro}}>
            </div>
            
                <h1 class="nome-cliente flex-center">{this.props.titulo}</h1>
                <div class="caixa-informacoes-basicas">
                    <div class="caixa-info flex-center">
                        <div class="caixa-info1">{this.props.texto1}</div>
                        <div class="caixa-info1">{this.props.texto2}</div>
                    </div>
                    <div class="caixa-info flex-center">
                        <div class="caixa-info1">{this.props.texto3}</div>
                        <div class="caixa-info1">{this.props.texto4}</div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export default CapaPerfilPro;