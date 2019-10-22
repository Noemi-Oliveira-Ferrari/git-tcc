import React, { Component } from 'react';
import '../css/servicos-pro.css';
import '../css/padroes.css';

export class ServConcluidos extends Component{
   render(){
    return(
         
        <div class="caixa-comentario-usuario-servico">
            <div class="dados-usuario-servico">
                <h4 class="titulo-comentario-servico">TextText TextText</h4>
                <hr class="linha-concluidos"/>
                <div class="caixa-star">
                    <div class="estrelas">
                        <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                        <label for="cm_star-1"><i class="fa"></i></label>
                        <input type="radio" id="cm_star-1" name="fb" value="1"/>
                        <label for="cm_star-2"><i class="fa"></i></label>
                        <input type="radio" id="cm_star-2" name="fb" value="2"/>
                        <label for="cm_star-3"><i class="fa"></i></label>
                        <input type="radio" id="cm_star-3" name="fb" value="3"/>
                        <label for="cm_star-4"><i class="fa"></i></label>
                        <input type="radio" id="cm_star-4" name="fb" value="4"/>
                        <label for="cm_star-5"><i class="fa"></i></label>
                        <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                    </div>
                </div>
                <p class="texto-comentario">Typomething herehjtyutyjyujuyj Type something ghatgh ghcf\bcccccccccccathtg hereType something here.</p>
                <div class="caixa-btn">
                    <button class="btn-ver-mais">Ver Mais</button>
                </div>
            </div>
        </div>
    );
   }

}

export default ServConcluidos;