import React, { Component, Fragment } from 'react';
import '../css/servicos-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import ServPendentes from '../components/ServPendentes';
import ServConcluidos from '../components/ServConcluidos';
import CapaPerfilPro from '../components/CapaPerfilPro';


export class ServicosPro extends Component{
   render(){
    return(
       <Fragment>
           <CapaPerfilPro
           titulo="Seus Pedidos"
           texto1="Veja o estado dos pedidos"
           texto2="Fique sempre por dentro"
           texto3="verifique suas atividades"
           texto4="Veja suas avaliações"
           ></CapaPerfilPro>
            <div class="caixa-conteudo-servico-pro">
                <div class="conteudo-pro-servico">
                    <div class="caixa-informacoes-pendentes">
                        <div class="caixa-conteudo-informacoes-pendentes">
                            <div class="text-dados">
                                <h3>Pendentes</h3>
                            </div>
                           <ServPendentes/>
                        </div>
                    </div>
                    <div class="caixa-informacoes-concluidos">
                        <div class="caixa-comentarios-servicos">
                            <div class="text-dados">
                                <h3>Concluídos</h3>
                            </div>
                           <ServConcluidos/>
                        </div>
                    </div>
                </div>
            </div>
        </Fragment>


    );
   }

}

export default ServicosPro;