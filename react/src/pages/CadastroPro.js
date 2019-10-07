import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import { DadosProfissional, DadosPessoaisPro } from '../components/DadosProfissional';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';


export class CadastroPro extends Component{

    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);   
    }


    validarCampos(){

        // console.log($("#txt-senha").val());
        // console.log($("#txt-confirmar-senha").val());
        // if($("#txt-senha").val() != $("#txt-confirmar-senha").val()){
        //     $('#txt-confirmar-senha').get(0).setCustomValidity('The two email addresses must match.');
        // }

        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email], select, textarea");
        console.log(campos);
        campos.forEach(campo =>{
            if(campo.value === ""){
                $(campo).addClass("erro");
            }
        })
    }

    realizarCadastro(event){
        event.preventDefault();
        console.clear();
        console.log("Enviando dados ao banco...");
        $('#txt-confirmar-senha').get(0).setCustomValidity('As senha não correspondem!');
        
        if($("#txt-senha").val() == $("#txt-confirmar-senha").val()){
            let cpfCnpj = $("#txt-cpfCnpj").val().replace(/[.-]/g, "");
            let cpf;
            let cnpj;
            if(cpfCnpj.length <= 11){
                cpf = cpfCnpj;
                cnpj = null;
            }else{
                cnpj = cpfCnpj;
                cpf = null;
            }        
    
            this.validarCampos();
    
            let endereco = {
                cep: $("#txt-cep").val(),
                logradouro: $("#txt-logradouro").val(),
                bairro: $("#txt-bairro").val(),
                cidade: {
                    idCidade: $("#txt-cidade").attr("data-idCidade")
                }
            };
            let profissional = {
                nome: $("#txt-nome").val(),
                dataNasc: $("#txt-dataNasc").val(),
                cpf: cpf,
                cnpj: cnpj,
                email: $("#txt-cep").val(),
                senha: $("#txt-cep").val(),
                endereco: {
                    idEndereco: null
                },
                subcategoria: {
                    idSubcategoria: $("#slt-subcat").val(),
                },
                valorHora: $("#txt-valor-hora").val(),
                resumoQualificacoes: $("#txt-qualificacoes").val()
            };
            console.log($("#chk-termos").is(":checked"));
    
            // sessionStorage.setItem("endereco", JSON.stringify(endereco));
            // sessionStorage.setItem("profissional", JSON.stringify(profissional));
            // browserHistory.push("/profissional/cadastro/confirmacao");
        }else{
            console.log("CORECTO MENINO");
            
        }

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

                    <TermosDeUso link="/profissional/cadastro/confirmacao">                
                    </TermosDeUso>
                </form>
            </div>
        );
    }

}

export default CadastroPro;
