import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';
import { DadosPessoaisPro, DadosProfissional } from '../components/FormularioProfissional';
import Edit from '../img/edit.png';


export class PerfilPro extends Component{
   render(){
    return(
       <Fragment>
            <CapaPerfilPro
                titulo="Ester Ribeiro"
                texto1="Eletricista Domiciliar"
                texto3="4.9 ******"
                texto2="Jandira, São Paulo - SP"
                texto4="R$100,00 p/ hora"
            ></CapaPerfilPro>
            <div className="caixa-conteudo-perfil-pro">
                <div className="container-flex">
                <div className="text-editar-perfil flex-center">
                    <figure>
                        <img src={Edit}/>
                    </figure>
                </div>
                    <DadosPessoaisPro/>
                    <DadosProfissional/>
                </div>
                <AvaliacaoPro/>
            </div>
        </Fragment>
    );
   }

}

export default PerfilPro;