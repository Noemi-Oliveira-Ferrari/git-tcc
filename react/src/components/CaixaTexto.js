
import React, { Component } from 'react';
import '../css/sobrenos.css'
import '../css/padroes.css'


class CaixaTexto extends Component{
    render(){
        return(    
            <div class="caixa-conteudo-quem-somos flex-center">
                <h2 class="titulo-caixa-quem-somos">{this.props.titulo}</h2>
                <p class="text-caixa-quem-somos">{this.props.texto}</p>
            </div>
        );
    }
}
export default CaixaTexto;