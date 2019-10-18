import React, { Component } from 'react';
import customer from '../img/customer-ai.png';
import worker from '../img/worker-ai.png';
import $ from 'jquery';
import Escolha from '../components/Escolha';
import '../css/escolha.css';


class CardsEscolha extends Component {

	componentDidMount(){
		if($(".caixa-profissional").css("width") === "450px"){
	
			$(".caixa-profissional").mouseenter(function () {
				$(this).css("transform", "scale(1.07)");
				$("#btn-escolha-worker").css("height", "60px");
			
				$(".seta-worker").css("visibility", "visible");
				$(".seta-worker").css("opacity", 1);
			
				$("#btn-escolha-worker").css("padding-left", "380px");
			});
		
			$(".caixa-profissional").mouseleave(function () {
				$(this).css("transform", "scale(1)");
				setTimeout(() => {  
					$("#btn-escolha-worker").css("height", "0px");
					$(".seta-worker").css("visibility", "hidden");
					$(".seta-worker").css("opacity", 0);    
				}, 125);    
				$(".seta-worker").css("visibility", "hidden");
				$(".seta-worker").css("opacity", 0);    
				$("#btn-escolha-worker").css("padding-left", "10px");
				// $(".seta-worker").css("transform", "translate(10px)");
			});
		
			$(".caixa-cliente").mouseenter(function () {
				$(this).css("transform", "scale(1.07)");
				$("#btn-escolha-customer").css("height", "60px");
			
				$(".seta-customer").css("visibility", "visible");
				$(".seta-customer").css("opacity", 1);
			
				$("#btn-escolha-customer").css("padding-left", "380px");
			});
		
			$(".caixa-cliente").mouseleave(function () {
				$(this).css("transform", "scale(1)");
				setTimeout(() => {  
					$("#btn-escolha-customer").css("height", "0px");
					$(".seta-customer").css("visibility", "hidden");
					$(".seta-customer").css("opacity", 0);    
				}, 125);    
				$(".seta-customer").css("visibility", "hidden");
				$(".seta-customer").css("opacity", 0);    
				$("#btn-escolha-customer").css("padding-left", "10px");
				// $(".seta-customer").css("transform", "translate(10px)");
			});
		}
	}

    render(){
		return(
			<div className="container-conteudo-escolha">
				<div className="title-caixa-escolha center">
					<h1>O que você gostaria de fazer?</h1>
				</div>
				<div className="caixa-escolha">

					<Escolha 
						classCaixa="caixa-profissional"
						toCadastro="/profissional/cadastro"
						titulo="Profissional" 
						texto="Você poderá cadastrar seus serviços e ser contratado pelos usuários da plataforma"
						img={worker}
						seta="seta-worker"
						idBtn="btn-escolha-worker"/>

					<Escolha 
						classCaixa="caixa-cliente"
						toCadastro="/cliente/cadastro"
						titulo="Cliente" 
						texto="Você poderá encontrar diversos profissionais ideais para o serviço que precisara"
						img={customer}
						seta="seta-customer"
						idBtn="btn-escolha-customer"/>

				</div> 
			</div>
		);
	}
}

export default CardsEscolha;