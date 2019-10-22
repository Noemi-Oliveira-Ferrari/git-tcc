import React, { Component } from 'react';
import '../css/servicos-pro.css';
import '../css/padroes.css';


export class ServPendentes extends Component{
   render(){
    return(
         
        <div class="caixa-servico-pendente">
            <h3 class="title-pendente">TitleTitle</h3>
            <hr class="linha-servico-pendente"/>
            <p class="text-pendente">Type something hereTypfghhsmjhdkjkghsgh ghyghrsy somefdhgtfhghgfthing hereType ething yfvbdgjsjujujk hereyfgfngfhsfdjkjhgdpe something mnjhgjgd here</p>
            <div class="caixa-link-pendente">
                <button class="link-pendente">Visualizar serviço</button>
            </div>
        </div>
    );
   }

}

export default ServPendentes;