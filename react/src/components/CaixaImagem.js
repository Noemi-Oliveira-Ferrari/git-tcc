
import React, { Component } from 'react';
// css
import '../css/sobrenos.css'
import '../css/padroes.css'


class CaixaImagem extends Component{
    render(){
        return(    
            <div className="caixa-mvv-quem-somos flex-center">    
                <div className="imagem-mvv-quem-somos">
                    <figure>
                        <img src={this.props.img}/>
                    </figure>
                </div>
                <h3 className="title-mvv-quem-somos">{this.props.titulo}</h3>
                <p className="text-mvv-quem-somos">{this.props.texto}</p>
            </div>
        );
    }
}
export default CaixaImagem;

