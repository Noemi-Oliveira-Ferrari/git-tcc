import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';


export class AvaliacaoPro extends Component{
   render(){
    return( 
 
        <div className="caixa-comentarios-perfil">
            <h2 className="title-avaliacao">Principais Avaliações</h2>
            <div className="caixa-comentario-usuario">
                <div className="usuario-perfil">
                    <div className="circulo-usuario" style={{backgroundImage: `url(${this.props.fotoCliente})`}}>
                    </div>
                    <h4 className="nome-usuario">{this.props.nomeCliente}</h4>
                </div>
                <div className="dados-usuario">
                    <h4 className="titulo-comentario flex-center">Avaliação: {this.props.nota}</h4>
                    {/* <div className="caixa-star-perfil flex-center">
                        <div className="estrelas">
                            <input type="radio" id="cm_star-empty" name="fb" value="" />
                            <label htmlFor="cm_star-1"><i className="fa"></i></label>
                            <input type="radio" id="cm_star-1" name="fb" value="1"/>
                            <label htmlFor="cm_star-2"><i className="fa"></i></label>
                            <input type="radio" id="cm_star-2" name="fb" value="2"/>
                            <label htmlFor="cm_star-3"><i className="fa"></i></label>
                            <input type="radio" id="cm_star-3" name="fb" value="3"/>
                            <label htmlFor="cm_star-4"><i className="fa"></i></label>
                            <input type="radio" id="cm_star-4" name="fb" value="4"/>
                            <label htmlFor="cm_star-5"><i className="fa"></i></label>
                            <input type="radio" id="cm_star-5" name="fb" value="5" />
                        </div>
                    </div> */}
                    <p className="texto-comentario-perfil flex-center">{this.props.comentario}</p>
                </div>
            </div>
           
        </div>
    );
   }

}

export default AvaliacaoPro;