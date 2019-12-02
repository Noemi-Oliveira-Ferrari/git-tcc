import React, {Component} from 'react';
import Topo from '../img/to_top.png';
import {movePage} from '../js/validar';



export default class ToTop extends Component{

    clickHandler(event){
        movePage(0);
    }


    render(){
        return(            
            <div onClick={this.clickHandler} className="voltar-topo flex-center">
                <figure>
                    <img id="img-voltar-topo" src={Topo} alt="Voltar ao Topo" title="Voltar ao Topo"/>
                </figure>
            </div>
        );
    }


}