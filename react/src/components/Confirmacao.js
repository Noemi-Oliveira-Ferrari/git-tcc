import React, { Component } from 'react';
import {Link} from 'react-router';
import EmailImg from '../img/emailcheck.png';
import check from '../img/check.png';

import '../css/confirmacao.css';

export default class Confirmacao extends Component{

    componentDidMount(){
        console.log("foi");
    }

   render(){
    return(
        <section className="flex-center center">
            <div className="caixa-confirmacao-email">
                <div className="title-confirmacao center flex-center">
                    Verifique seu endereço de E-mail:
                </div>
                <div className="text-confirmacao flex-center">
                    Lorem ipsum dolor sit amet consectetur adipiscing elit ullamcorper velit nullam, lacinia aliquam himenaeos volutpat faucibus magnis torquent imperdiet rutrum, lectus per laoreet erat arcu morbi etiam
                </div>
                <div className="img-email flex-center center">
                    <figure>
                        <img src={EmailImg}/>
                    </figure>
                </div>
        
                <form name="form_cod_email" action="index.html" method="POST">
                    <div className="caixa-input-confirm flex-center center">
                        <div className="input-cod-confirm">
                            <input required className="input-cod-confirm"  type="text" pattern="[0-9]*4" maxLength="4" name="cod_email"/>
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
                <Link to="/profissional/cadastro/confirmacao"> Reenviar E-mail? </Link>
                <Link to="/profissional/cadastro"> Alterar E-mail?</Link>
                </div>
            </div>
        </section>
    );
   }
}
