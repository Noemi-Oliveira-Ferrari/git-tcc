import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import InputCadastroPro from './InputCadastroPro';


export class DadosPessoaisPro extends Component{
   render(){
    return(
        <div className="flex-center">
            <div className="card-formulario-pessoal">
                <h3 className="title-card">Dados Pessoais</h3>
                
                <div className="float campos-dados">
                    <div className="flex-center container-nome-dataNasc">

                        <InputCadastroPro
                        classInputPro="caixa-nome"
                        label="Nome:"
                        maxlength="100"
                        id="txt-nome"
                        type="text">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-dataNasc"
                        label="Data Nasc:."
                        maxlength="10"
                        id="txt-dataNasc"
                        type="text">
                        </InputCadastroPro>
                   
                    </div>
                    <div className="flex-center container-cpfCnpj-email">

                        <InputCadastroPro
                        classInputPro="caixa-cpfCnpj"
                        label="CPF/CNPJ:"
                        maxlength="20"
                        id="txt-cpfCnpj"
                        type="text">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-email"
                        label="E-mail:"
                        maxlength="150"
                        id="txt-email"
                        type="email">
                        </InputCadastroPro>

                        
                    </div>
                    <div className="flex-center container-senha">

                        <InputCadastroPro
                        classInputPro="caixa-senha"
                        label="Senha:"
                        maxlength="130"
                        id="txt-senha"
                        type="password">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-confirmar-senha"
                        label="Confirmar Senha:"
                        maxlength="130"
                        id="txt-confirmar-senha"
                        type="password">
                        </InputCadastroPro>

                    </div>

                    <div className="flex-center container-cep-logradouro">

                        <InputCadastroPro
                        classInputPro="caixa-cep"
                        label="CEP:"
                        maxlength="10"
                        id="txt-cep"
                        type="text">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-logradouro"
                        label="Logradouro:"
                        maxlength="120"
                        id="txt-logradouro"
                        type="text">
                        </InputCadastroPro>
                      
                    </div>
                    <div className="flex-center container-bairro-cidade-uf">

                        <InputCadastroPro
                        classInputPro="caixa-bairro"
                        label="Bairro:"
                        maxlength="120"
                        id="txt-bairro"
                        type="text">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-cidade"
                        label="Cidade:"
                        maxlength="120"
                        id="txt-cidade"
                        type="text">
                        </InputCadastroPro>

                        <InputCadastroPro
                        classInputPro="caixa-uf"
                        label="UF:"
                        maxlength="2"
                        id="txt-uf"
                        type="text">
                        </InputCadastroPro>
                        
                    </div>
                </div>
            </div>
        </div>
 
    );
   }

}

export default DadosPessoaisPro;