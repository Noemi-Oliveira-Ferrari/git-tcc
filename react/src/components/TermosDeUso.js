import React, { Component } from 'react';
import {Link} from 'react-router';
import '../css/cadastro-pro.css';
import {Botao} from './Botao';


export class TermosDeUso extends Component{
    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-termos">
                    <h3 className="title-card">Termos de Uso</h3>
                    <div className="flex-center caixa-termos">
                        <div className="text-termos">
                            <p className="termo">
                                Ocasionalmente, nós podemos fazer alterações aos Acordos por razões válidas, como melhorar as funções ou recursos existentes ou acrescentar novas funções ou recursos ao Serviço, implementar avanços científicos e tecnológicos, e ajustes técnicos razoáveis do Serviço, assegurando a operacionalidade ou a segurança do Serviço, bem como por razões legais ou regulamentares. Quando realizarmos mudanças materiais aos Acordos, lhe forneceremos um aviso de acordo com as circunstâncias, por exemplo, exibindo uma notificação proeminente ou buscando sua confirmação dentro do Serviço ou enviando um e-mail para você. Em alguns casos, nós te notificaremos com antecedência, e seu uso continuado do Serviço, após terem sido feitas as alterações, constituirá sua aceitação das alterações. Portanto, por favor certifique-se de ler cuidadosamente qualquer notificação. Se você não quiser continuar a utilizar o Serviço sob a nova versão dos Acordos, você poderá encerrar sua conta entrando em contato conosco. Se você tiver recebido uma Versão Teste ou uma Assinatura Paga por meio de terceiros, você deverá cancelar a Assinatura Paga aplicável através do terceiro em questão.
                            </p>
                        </div>
                    </div>
                    <div className="caixa-confirmacao-termos">
                        <input 
                        required 
                        type="checkbox"
                        id="chk-termos" 
                        className="confirmar-termos" 
                        name="chk_termos"/>
                        <label htmlFor="chk-termos">
                            Declaro estar ciente com os <span className="negrito">Termos de Uso e Politica de privacidade</span>
                        </label>
                    </div>
                    <div className="caixa-voltar-termos">
                        <Link className="link" to="/escolha">
                            <Botao 
                            classBotao="btn-voltar flex-center" 
                            typeBotao="button" 
                            name="btn_voltar" 
                            id="btn-voltar" 
                            valueBotao="Voltar"/>
                        </Link>
                    </div>
                    <div className="caixa-continuar-termos">
                        <Botao 
                        classBotao="btn-prox flex-center" 
                        name="btn_prox"
                        id="btn-prox" 
                        valueBotao="Continuar" 
                        typeBotao="submit"/>
                    </div>
                </div>
            </div>
        );
    }

}

export default TermosDeUso;
