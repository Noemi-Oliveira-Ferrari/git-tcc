
import React, { Component } from 'react';
import '../css/sobrenos.css'
import '../css/padroes.css'


export class CaixaTextoSuperior extends Component{
    render(){
        return(    
            <div className="caixa-conteudo-quem-somos flex-center">
                <h2 className="titulo-caixa-quem-somos">{this.props.titulo}</h2>
                <p className="text-caixa-quem-somos1">{this.props.texto}</p>
            </div>
        );
    }
}

export class CaixaTextoInferior extends Component{
    render(){
        return(    
            <div className="caixa-conteudo-quem-somos flex-center">
                <h2 className="titulo-caixa-quem-somos">{this.props.titulo}</h2>
                <p className="text-caixa-quem-somos2">{this.props.texto}</p>
            </div>
        );
    }
}

// export default { CaixaTextoSuperior, CaixaTextInferior };