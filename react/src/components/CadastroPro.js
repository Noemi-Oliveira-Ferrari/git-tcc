import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import { DadosProfissional, DadosPessoaisPro } from './DadosProfissional';
import TermosDeUso from './TermosDeUso';


export class CadastroPro extends Component{

    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);
    }

    realizarCadastro(event){
        event.preventDefault();
        console.log("Enviando dados ao banco...");

        // let profissional = {
        //     nome: this.state.nome,
        //     dataNasc: this.state.dataNasc,
        //     cpf: this.state.cpf,
        //     cnpj: this.state.cnpj,
        //     email: this.state.email,
        //     senha: this.state.senha,
        //     endereco: {
        //         idEndereco: ""
        //     }
        // }
        // let endereco = {
        //     cep: this.state.cep,
        //     logradouro: this.state.logradouro,
        //     bairro: this.state.bairro,
        //     cidade: this.state.cidade,
        //     uf: this.state.uf
        // };

        // sessionStorage.setItem("profissional", JSON.stringify(profissional));
        // sessionStorage.setItem("endereco", JSON.stringify(endereco));
    }

    render(){
        return(
            
            <div className="container-conteudo">
                <form className="form-pro" name="form_profissional" method="GET" onSubmit={this.realizarCadastro}>
                    
                    {/* DDAOS PESSOAIS */}
                    <DadosPessoaisPro>
                    </DadosPessoaisPro>

                    <DadosProfissional>
                    </DadosProfissional>

                    <TermosDeUso>                
                    </TermosDeUso>
                </form>
            </div>
        );
    }

}

export default CadastroPro;
