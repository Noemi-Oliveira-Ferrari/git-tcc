import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import { Inputs, InputNumber } from '../components/FormElements';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';
import { DadosPessoaisPro } from '../components/FormularioProfissional';


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
                <div class="caixa-conteudo-perfil-pro">
                    <div class="conteudo-pro">
                        <div class="caixa-informacoes1">
                            <div class="caixa-informacoes2">
                                <div class="container-flex">
                                    <DadosPessoaisPro/>
                                  </div>
                              </div>
                          </div>
                          <AvaliacaoPro/>
                      </div>
                  </div>
                  </Fragment>


    );
   }

}

export default PerfilPro;