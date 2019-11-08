import React, { Component, Fragment } from 'react';
import '../css/servicos-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import CapaPerfilPro from '../components/CapaPerfilPro';
import CardServico from '../components/CardServico';


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
                    <div class="caixa-conteudo-informacoes-servico">
                        <div class="text-dados">
                            <h3>Pendentes</h3>
                        </div>
                        <CardServico
                            titulo="Concerto maquina de lavar Brastemp"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                            estrelas="caixa-star-hidden"
                        ></CardServico>
                        <CardServico
                            titulo="Concerto maquina de lavar Brastemp"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                            estrelas="caixa-star-hidden"
                        ></CardServico>
                        <CardServico
                            titulo="Concerto maquina de lavar Brastemp"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Minha maquina quebrou e nao funciona a peça tal pegou fogo e preciso de uma nova"
                            estrelas="caixa-star-hidden"
                        ></CardServico>
                    </div>
                    <div class="caixa-conteudo-informacoes-servico">
                        <div class="text-dados">
                            <h3>Concluídos</h3>
                        </div>
                        <CardServico
                            titulo="Fiamento de dois comodos"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                            estrelas="caixa-star"
                        ></CardServico>
                        <CardServico
                            titulo="Fiamento de dois comodos"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                            estrelas="caixa-star"
                        ></CardServico>
                        <CardServico
                            titulo="Fiamento de dois comodos"
                            cliente="Maria Gasolina, Barueri - SP"
                            comentario="Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo"
                            estrelas="caixa-star"
                        ></CardServico>
                    </div>
                </div>
            </div>
        </Fragment>


    );
   }

}

export default ServicosPro;