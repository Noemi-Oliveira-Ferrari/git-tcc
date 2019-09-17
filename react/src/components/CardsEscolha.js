import React from 'react';
import {Component} from 'react';
import '../css/App.css';
import '../utils/cardChooseAnimation';
import customer from '../img/customer-ai.png';
import worker from '../img/worker-ai.png';

import Escolha from './Escolha';

class CardsEscolha extends Component {
    render(){
		return(
			<div className="container-conteudo">
				<div className="title-caixa-escolha center">
					<h1>O que você gostaria de fazer?</h1>
				</div>
				<div className="caixa-escolha">

					<Escolha 
					classCaixa="caixa-profissional"
					titulo="Profissional" 
					texto="Você poderá cadastrar seus serviços e ser contratado pelos usuários da plataforma"
					img={worker}
					seta="seta-worker"
					idBtn="btn-escolha-worker"/>

					<Escolha 
					classCaixa="caixa-cliente"
					titulo="Cliente" 
					texto="Você poderá encontrar diversos profissionais ideais para o serviço que precisara"
					img={customer}
					seta="seta-customer"
					idBtn="btn-escolha-customer"/>

	
					{/* <div class="caixa-profissional">
						<div class="title-escolha">
							Profissional
						</div>
						<div class="texto-escolha"> 
							Você poderá cadastrar seus serviços e ser contratado pelos usuários da plataforma
						</div>
						<div class="img-escolha" >
							<figure>
								<img class="img" src={worker} alt=""/>
							</figure>
						</div>
						<div id="btn-escolha-worker" class="botao-escolha">
							<div class="seta">
								<figure>
									<img class="seta-worker" src={arrowRight} alt=""/>
								</figure>
							</div>
						</div>
					</div> */}
				
	
	
					{/* <div class="caixa-cliente">
						<div class="title-escolha">
							Cliente 
						</div>
						<div class="texto-escolha">
							Você poderá encontrar diversos profissionais ideais para o serviço que precisar
						</div>
						<div class="img-escolha">
							<figure>
								<img class="img" src={customer} alt="" />
							</figure>
						</div>
						<div id="btn-escolha-customer" class="botao-escolha">
							<div class="seta">
								<figure>
									<img class="seta-customer" src={arrowRight} alt="" />
								</figure>
							</div>
						</div>
					</div>*/}
				</div> 
			</div>
		);
	}
}

export default CardsEscolha;