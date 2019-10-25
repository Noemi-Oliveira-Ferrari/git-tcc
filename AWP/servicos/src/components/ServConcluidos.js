import React, { Component } from 'react';
import '../css/servicos-pro.css';
import '../css/padroes.css';
import Analisar from '../img/magnifying-glass.png'


export class ServConcluidos extends Component{
   render(){
    return(
         
        <div className="caixa-servico-concluido">
            <h3 className="title-concluido">Fiamento de dois comodos</h3>
            <h6 className="cliente-pendente">Maria Gasolina, Barueri - SP</h6>                
            <hr className="linha-concluidos"/>
            <div className="caixa-star">
                <div className="estrelas">
                    <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                    <label for="cm_star-1"><i className="fa"></i></label>
                    <input type="radio" id="cm_star-1" name="fb" value="1"/>
                    <label for="cm_star-2"><i className="fa"></i></label>
                    <input type="radio" id="cm_star-2" name="fb" value="2"/>
                    <label for="cm_star-3"><i className="fa"></i></label>
                    <input type="radio" id="cm_star-3" name="fb" value="3"/>
                    <label for="cm_star-4"><i className="fa"></i></label>
                    <input type="radio" id="cm_star-4" name="fb" value="4"/>
                    <label for="cm_star-5"><i className="fa"></i></label>
                    <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                </div>
            </div>
            <p className="text-concluido">Otimo profissional, chegou no horario e fez o trabalho bem feito, mto educado so deixou sujo</p>
            <div className="caixa-link-pendente flex-center">
                <figure >
                    <img alt="Ver mais" title="Ver mais" src={Analisar}/>
                </figure>
            </div>
        </div>
    );
   }

}

export default ServConcluidos;