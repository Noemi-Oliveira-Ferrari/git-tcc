import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import { Inputs, InputNumber } from '../components/FormElements';


export class PerfilPro extends Component{
   render(){
    return(
       <Fragment>
               <div class="capa-perfil-pro">
               </div>
                  <div class="caixa-conteudo-perfil-pro">
                      <div class="avatar">
                      </div>
                      <div class="caixa-perfil">
                          <h1 class="nome-cliente">Tipe Something Text</h1>
                          <div class="caixa-informacoes-basicas">
                              <div class="caixa-info">
                                  <div class="caixa-info1">Type something here</div>
                                  <div class="caixa-info1">Type something here</div>
                              </div>
                              <div class="caixa-info">
                                  <div class="caixa-info1">Type something here</div>
                                  <div class="caixa-info1">Type something here</div>
                              </div>
                          </div>
                      </div>
                      <div class="conteudo-pro">
                          <div class="caixa-informacoes1">
                              <div class="text-dados">
                                  <h3>Seus Dados</h3>
                              </div>
                              <p class="text-dados-editar">Editar</p>
                              <div class="caixa-informacoes2">
                                  <div class="container-flex">
                                      <div class="card-formulario-pessoal">
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
                          <div class="caixa-comentarios">
                              <h2 class="title-avaliacao">Principais Avaliações</h2>
                              <div class="caixa-comentario-usuario">
                                  <div class="usuario">
                                      <div class="circulo-usuario"></div>
                                      <h4 class="nome-usuario">TitleTitle</h4>
                                  </div>
                                  <div class="dados-usuario">
                                      <h4 class="titulo-comentario">TextText TextText</h4>
                                      <div class="caixa-star">
                                          <div class="estrelas">
                                              <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                                              <label for="cm_star-1"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-1" name="fb" value="1"/>
                                              <label for="cm_star-2"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-2" name="fb" value="2"/>
                                              <label for="cm_star-3"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-3" name="fb" value="3"/>
                                              <label for="cm_star-4"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-4" name="fb" value="4"/>
                                              <label for="cm_star-5"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                                            </div>
                                      </div>
                                      <p class="texto-comentario">Type something herehjtyutyjyujuyj Type something ghatgh ghathtg hereType something here.</p>
                                  </div>
                              </div>
                              <div class="caixa-comentario-usuario">
                                  <div class="usuario">
                                      <div class="circulo-usuario"></div>
                                      <h4 class="nome-usuario">TitleTitle</h4>
                                  </div>
                                  <div class="dados-usuario">
                                      <h4 class="titulo-comentario">TextText TextText</h4>
                                      <div class="caixa-star">
                                          <div class="estrelas">
                                              <input type="radio" id="cm_star-empty" name="fb" value="" checked/>
                                              <label for="cm_star-1"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-1" name="fb" value="1"/>
                                              <label for="cm_star-2"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-2" name="fb" value="2"/>
                                              <label for="cm_star-3"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-3" name="fb" value="3"/>
                                              <label for="cm_star-4"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-4" name="fb" value="4"/>
                                              <label for="cm_star-5"><i class="fa"></i></label>
                                              <input type="radio" id="cm_star-5" name="fb" value="5" checked/>
                                            </div>
                                      </div>
                                      <p class="texto-comentario">Type something herehjtyutyjyujuyj Type something ghatgh ghathtg hereType something here.</p>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                  </Fragment>


    );
   }

}

export default PerfilPro;