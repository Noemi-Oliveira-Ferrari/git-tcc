import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import DadosPessoaisPro from './DadosPessoaisPro';
import DadosProfissional from './DadosProfissional';


export class CadastroPro extends Component{
   render(){
    return(
        
        <div className="container-conteudo">
            <form className="form-pro" name="form_profissional" method="GET" action="">
                
            {/* DDAOS PESSOAIS */}
            <DadosPessoaisPro>

            </DadosPessoaisPro>

            <DadosProfissional>

            </DadosProfissional>

                
            </form>
        </div>
    );
   }

}

export default CadastroPro;
