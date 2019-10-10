
// import '../css/escolha.css';
import arrowRight from '../img/arrow-point-to-right.png';
import {Link} from 'react-router';
import React, { Component } from 'react';


class Escolha extends Component{
    render(){
        return(    
            <Link to={this.props.toCadastro}>
                <div className={this.props.classCaixa}>
                    <div className="title-escolha">
                        {this.props.titulo}
                    </div>
                    <div className="texto-escolha"> 
                        {this.props.texto}
                    </div>
                    <div className="img-escolha" >
                        <figure>
                            <img className="icon-escolha" src={this.props.img} alt=""/>
                        </figure>
                    </div>
                    <div id={this.props.idBtn} className="botao-escolha">
                        <div className="seta">
                            <figure>
                                <img className={this.props.seta} src={arrowRight} alt=""/>
                            </figure>
                        </div>
                    </div>
                </div>
            </Link>
        );
    }
}
export default Escolha;