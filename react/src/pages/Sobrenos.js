import React, { Component, Fragment } from 'react';
// css
import '../css/sobrenos.css';
import '../css/padroes.css';
// components
import CaixaTexto from '../components/CaixaTexto';
import CaixaImagem from '../components/CaixaImagem';
// img
import Valor from '../img/value.png'
import Missao from '../img/target.png'
import Visao from '../img/flag.png'

export class Sobrenos extends Component{

    render(){
        return(
            <Fragment>
                <div class="tamanho-caixa-quem-somos">
                    <div class="caixa-quem-somos">
                        <h1 class="titulo-quem-somos">Quem Somos?</h1>
                        <div class="caixa-informacoes-empresa-quem-somos flex-center">
                            <CaixaTexto
                                titulo="Brace {Everything}"
                                texto="A Brace Everything que foi fundada em 2019. Criou a DaUmHelp! A plataforma é um meio ágil de intermediação entre pessoas que precisam de determinado serviço e pessoas que desejam oferecer seus serviços.​"
                            ></CaixaTexto>
                            <CaixaTexto
                                titulo=""
                                texto="O profissional faz uma assinatura baseada em uma tabela de preços, mensal semestral ou anual, para que pessoas que precisam de seus serviços encontra-lo. O cliente que precisa de profissionais para ajuda-lo a cumprir alguma tarefa é só ele se cadastrar e escolher a melhor opção."
                            ></CaixaTexto>
                        </div>
                    </div>
                </div>
                <div class="caixa-sobre-empresa-quem-somos">
                    <div class="caixa-cor-missao-vissao-valor-quem-somos flex-center">
                        <CaixaImagem
                            img={Missao}
                            titulo="Missão"
                            texto="A Brace Everything foi fundada em 2019 com a missão de: “ Desenvolver soluções para os mais diversos problemas por meio de inovações tecnológicas, afim de facilitar o dia a dia das pessoas de maneira ágil e eficaz."
                        ></CaixaImagem>
                        <CaixaImagem
                            img={Visao}
                            titulo="Visão"
                            texto="Ser reconhecida mundialmente por promover soluções convenientes ao máximo de pessoas possíveis."
                        ></CaixaImagem>
                        <CaixaImagem
                            img={Valor}
                            titulo="Valores"
                            texto=" Agilidade;
                            Trabalho em equipe;
                            Fidelidade ao cliente;
                            Inovação;
                            Ética;
                            Performance;
                            Eficiência;"
                        ></CaixaImagem>
                    </div>
                </div>
            </Fragment>
        );
    }

}

export default Sobrenos;
