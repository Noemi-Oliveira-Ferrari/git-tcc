import React, { Component } from 'react';
import '../css/App.css';

import EmailImg from '../img/emailcheck.png';
import check from '../img/check.png';


export class Confirmacao extends Component{
   render(){
    return(
        <section className="flex-center">
        <div className="caixa-confirmacao ">
            <div className="title-confirmacao center flex-center">
                {this.props.titulo}
            </div>
            <div className="text-confirmacao flex-center">
                    {this.props.texto}
            </div>
            <div className="img-email flex-center center">
                <figure>
                    <img src={EmailImg}/>
                </figure>
            </div>
    
            <form name="form_cod_email" action="index.html" method="POST">
                <div className="caixa-input-confirm flex-center center">
                    <div className="input-cod-confirm">
                        <input required className="input-cod-confirm"  type="text" pattern="[0-9]*4" maxlength="4" name="cod_email"/>
                    </div>
                    <div className="img-check">
                    <button type="submit" name="button" id="btn-confirm">
                        <figure>
                            <img src={check} /> 
                        </figure>
                    </button>
                    </div>
                </div>
            </form>
            <div className="links flex-center center">
               <a href="#"> Reenviar E-mail? </a>
               <a href="#"> Alterar E-mail?</a>
            </div>

        </div>
    </section>
    );
   }

}

export default Confirmacao;