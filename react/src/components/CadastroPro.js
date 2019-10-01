import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import { DadosProfissional, DadosPessoaisPro } from './DadosProfissional';
import TermosDeUso from './TermosDeUso';
import axios from 'axios';


export class CadastroPro extends Component{

    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);
    }

    getEndereco(event){
    axios.get(`http://localhost:8080/enderecos/cep/${this.state.nome}`)
        .then((response)=>{
            console.log(response.data);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("loading");
    }

    realizarCadastro(event){
        event.preventDefault();
        console.log("Enviando dados ao banco...");

        // axios.get("http://localhost:8080/categorias")
        // .then((response)=>{
        //     console.log(response.data);
        // })
        // .catch((error)=>{
        //     console.error(error);
        // })
        // .onload = console.log("loading");

        axios.post({
            mathod: "POST",
            url: "http://localhost:8080/profissionais",
            data: {
                nome: this.state.nome,
                dataNasc: this.state.dataNasc,
                cpf: this.state.cpf,
                cnpj: this.state.cnpj,
                email: this.state.email,
                senha: this.state.senha,
                cep: this.state.cep,
                logradouro: this.state.logradouro,
                bairro: this.state.bairro,
                cidade: this.state.cidade,
                uf: this.state.uf
            }
        });
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
