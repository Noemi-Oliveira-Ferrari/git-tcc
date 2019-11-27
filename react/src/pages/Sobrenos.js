import React, { Component, Fragment } from 'react';
import '../css/sobrenos.css';
import '../css/padroes.css';
import CaixaTexto from '../components/CaixaTexto';


export class Sobrenos extends Component{

    render(){
        return(
            <>
                <div class="tamanho-caixa-quem-somos">
                <div class="caixa-quem-somos">
                    <h1 class="titulo-quem-somos">Quem Somos?</h1>
                    <div class="caixa-informacoes-empresa-quem-somos flex-center">
                        <CaixaTexto
                            titulo="DaUmHelp!"
                            texto="A plataforma é um meio ágil de intermediação entre pessoas que precisam de determinado serviço e pessoas que desejam oferecer seus serviços.​"
                        ></CaixaTexto>
                        <CaixaTexto
                            titulo=""
                            texto=""
                        ></CaixaTexto>
                    </div>
                </div>
            </div>
            <div class="caixa-sobre-empresa-quem-somos">
                <div class="caixa-cor-missao-vissao-valor-quem-somos">
                    <div class="caixa-missao-vissao-valor-quem-somos">    
                        <div class="imagem-quem-somos"></div>
                        <h3 class="title-missao-visao-valor-quem-somos">Missão</h3>
                        <p class="text-missao-visao-valor-quem-somos">A Brace Everything foi fundada em 2019 com a missão de: “ Desenvolver soluções para os mais diversos problemas por meio de inovações tecnológicas, afim de facilitar o dia a dia das pessoas de maneira ágil e eficaz. ”</p>
                    </div>
                    <div class="caixa-missao-vissao-valor-quem-somos">    
                        <div class="imagem-quem-somos"></div>
                        <h3 class="title-missao-visao-valor-quem-somos">Visão</h3>
                        <p class="text-missao-visao-valor-quem-somos">Ser reconhecida mundialmente por promover soluções convenientes ao máximo de pessoas possíveis.</p>
                    </div>
                    <div class="caixa-missao-vissao-valor-quem-somos">    
                        <div class="imagem-quem-somos"></div>
                        <h3 class="title-missao-visao-valor-quem-somos">Valor</h3>
                        <p class="text-missao-visao-valor-quem-somos">
                            •	Agilidade;
                            •	Trabalho em equipe;
                            •	Fidelidade ao cliente;
                            •	Inovação;
                            •	Ética;
                            •	Performance;
                            •	Eficiência;
                        </p>
                    </div>
                </div>
            </div>
        </>
        );
    }

}

export default Sobrenos;
