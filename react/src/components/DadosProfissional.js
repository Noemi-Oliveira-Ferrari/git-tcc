import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import InputCadastroPro from './InputCadastroPro';


export class DadosProfissional extends Component{
   render(){
    return(
        <div className="flex-center">
            <div className="card-formulario-servico">
                <h3 className="title-card">Dados Profissionais</h3>
                <div className="flex-center campos-servicos">
                    <div className="container-servico-pro">

                        <div className="flex-center container-categoria">
                        <InputCadastroPro
                        classInputPro="caixa-categoria"
                        label="Categoria:"
                        maxlength=""
                        id="txt-categoria"
                        type="text">
                        </InputCadastroPro>
                        </div>
                        
                        <div className="flex-center container-subcat">
                            <InputCadastroPro
                            classInputPro="caixa-subcat"
                            label="Subcategoria:"
                            maxlength=""
                            id="txt-subcat"
                            type="text">
                            </InputCadastroPro>
                        </div>
                        
                        <div className="flex-center container-valor-hora">
                            <InputCadastroPro
                            classInputPro="caixa-valor-hora"
                            label="Valor/Hora:"
                            maxlength=""
                            id="txt-valor-hora"
                            type="text">
                            </InputCadastroPro>
                        </div>
                    </div>
                    <div className="container-servico-pro">
                        <div className="container-qualificacoes">
                            <div className="float caixa-qualificacoes">
                                <label className="form-label">Resumo de Qualificações:</label>
                                <textarea id="txt-qualificacoes" className="txt-qualificacoes form-control form-input-pro"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
   }

}

export default DadosProfissional;
