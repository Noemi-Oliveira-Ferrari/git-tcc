import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import { Inputs, InputNumber } from '../components/FormElements';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';


export class PerfilPro extends Component{
   render(){
    return(
       <Fragment>
                <CapaPerfilPro
                    titulo="Ester Ribeiro"
                ></CapaPerfilPro>
                <div class="caixa-conteudo-perfil-pro">
                    <div class="conteudo-pro">
                        <div class="caixa-informacoes1">
                            <div class="caixa-informacoes2">
                                <div class="container-flex">
                                    <div class="card-formulario-pessoal">
                                        <div class="text-dados">
                                            <h3>Seus Dados</h3>
                                        </div>
                                        <p class="text-dados-editar">Editar</p>
                                        <div class="campos-dados">
                                            <div class="flex-center container-nome-dataNasc">
                                                <Inputs
                                                   label="Nome:"
                                                   id="txt-nome"
                                                   name="txt_nome"
                                                   maxLength="100"
                                                   type="text"
                                                   classDivInput="caixa-nome"
                                                   classInput="form-control form-input"
                                                   // onChange={this.setNome}
                                                />                                             
                                                <InputNumber
                                                   classDivInput="caixa-dataNasc"
                                                   label="Data de Nascimento:"
                                                   id="txt-dataNasc"
                                                   type="text"
                                                   name="txt_data_nasc"
                                                   mascara="##/##/####"
                                                   classInput="form-control form-input"
                                                   // onChange={this.setData}
                                                />
                                            </div>
                                            <div class="flex-center container-cpfCnpj-email">
                                                <InputNumber
                                                   classDivInput="caixa-cpfCnpj"
                                                   label="CPF"
                                                   id="txt-cpfCnpj"
                                                   type="text"
                                                   name="txt_cpfCnpj"
                                                   classInput="form-control form-input"
                                                   // onChange={this.setCpfCnpj}
                                                   // mascara={this.state.tipoPro === "rdo-pf" ? "###.###.###-##" : "##.###.###/####-##"}
                                                />
                                                <Inputs
                                                   classDivInput="caixa-email"
                                                   label="E-mail:"
                                                   maxLength="150"
                                                   id="txt-email"
                                                   type="email"
                                                   name="txt_email"
                                                   // onChange={this.setEmail}
                                                   classInput="form-control form-input"
                                                />
                                            </div>
                                            <div class="flex-center container-senha">
                                                <Inputs
                                                   classDivInput="caixa-senha"
                                                   label="Senha:"
                                                   maxLength="130"
                                                   id="txt-senha"
                                                   type="password"
                                                   name="txt_senha"
                                                   // onChange={this.setSenha}
                                                   classInput="form-control form-input"
                                                />
                                                <Inputs
                                                   classDivInput="caixa-confirmar-senha"
                                                   label="Confirmar Senha:"
                                                   maxLength="130"
                                                   id="txt-confirmar-senha"
                                                   type="password"
                                                   name="txt_confirmar_senha"
                                                   // onChange={this.setConfirmSenha}                           
                                                   classInput="form-control form-input"
                                                />
                                            </div>
                                            <div class="flex-center container-cep-logradouro">
                                                <Inputs
                                                   classDivInput="caixa-cep"
                                                   label="CEP:"
                                                   id="txt-cep"
                                                   type="text"
                                                   name="txt_cep"
                                                   // onChange={this.setCep}
                                                   classInput="form-control form-input"
                                                   mascara="99999-999"
                                                   // valueInput={this.state.cep || ""}
                                                />
                                                <Inputs
                                                   classDivInput="caixa-logradouro"
                                                   label="Logradouro:"
                                                   maxLength="120"
                                                   id="txt-logradouro"
                                                   type="text"
                                                   name="txt_logradouro"
                                                   // valueInput={this.state.logradouro || ""}
                                                   readOnly
                                                   classInput="form-control form-input"
                                                />
                                              </div>
                                              <div class="flex-center container-bairro-cidade-uf">
                                                <Inputs
                                                   classDivInput="caixa-bairro"
                                                   label="Bairro:"
                                                   maxLength="120"
                                                   id="txt-bairro"
                                                   type="text"
                                                   name="txt_bairro"
                                                   // valueInput={this.state.bairro || ""}
                                                   disabled
                                                   classInput="form-control form-input"
                                                />
                                                <Inputs
                                                   classDivInput="caixa-cidade"
                                                   label="Cidade:"
                                                   maxLength="120"
                                                   id="txt-cidade"
                                                   type="text"
                                                   name="txt_cidade"
                                                   // data={this.state.idCidade}
                                                   // valueInput={this.state.cidade || ""}
                                                   // readOnly
                                                   classInput="form-control form-input"
                                                />

                                                <Inputs
                                                   classDivInput="caixa-uf"
                                                   label="UF:"
                                                   maxLength="2"
                                                   id="txt-uf"
                                                   type="text"
                                                   name="txt_uf"
                                                   // valueInput={this.state.uf || ""}
                                                   // readOnly
                                                   classInput="form-control form-input"
                                                />
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                          </div>
                          <AvaliacaoPro/>
                      </div>
                  </div>
                  </Fragment>


    );
   }

}

export default PerfilPro;