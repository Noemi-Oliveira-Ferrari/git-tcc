import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import FormularioProfissional from '../components/FormularioProfissional';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';


export class CadastroPro extends Component{

    render(){
        return(
            
            <div className="container-conteudo-cadastro-pro">
                <div className="caixa-title-cadastro center">
                    <h1>Realize seu Cadastro!</h1>
                </div>
                {/* <form className="form-pro" name="form_profissional" method="GET" onSubmit={this.realizarCadastro}> */}
                    <FormularioProfissional/>
                {/* </form> */}
            </div>
        );
    }

}

export default CadastroPro;
