import React, { Component } from 'react';
import '../css/login.css';


export class LinkCadastro extends Component{
   render(){
    return(

        <div className="texto-card">
            <p>Ainda não possui uma conta?
                <a className="link-cadastro" href="#">
                    <b>Cadastre-se</b>
                </a>
            </p>
        </div>
        

    );
   }

}

export default LinkCadastro;