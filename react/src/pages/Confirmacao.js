import React, { useEffect, useState } from 'react';
import {Link} from 'react-router';
import EmailImg from '../img/emailcheck.png';
import check from '../img/check.png';
import ButtonToolbar from '../../node_modules/react-bootstrap/ButtonToolbar';
import $ from 'jquery';
import axios from 'axios';

import '../css/confirmacao.css';
import ModalSucesso from '../components/ModalSucesso';

function Confirmacao() {


    const [modalShow, setModalShow] = useState(false);
    const [codeConfirm, setCodeConfirm] = useState(random(1000, 9999));
    const [txtCodeConfirm, setTxtCodeConfirm] = useState("");
    const [renderizar, setRenderizar] = useState(true);
    const [profissional, setProfissional] = useState(JSON.parse(sessionStorage.getItem("profissional")));
    const [endereco, setEndereco] = useState(JSON.parse(sessionStorage.getItem("endereco")));
    // const [idEndereco, setIdEndereco] = useState(0);

    function random (min, max){
        return Math.trunc(Math.random() * (max + 1 - min) + min);
    }

    function cadastrarEndereco(){
        axios({
            method: 'POST',
            url: "http://localhost:8080/enderecos",
            type: "application/json",
            data: {
                bairro: endereco.bairro,
                cep: endereco.cep,
                cidade: {
                    idCidade: endereco.cidade.idCidade
                },
                logradouro: endereco.logradouro,
                numero: null
            }
        })
        .then((response)=>{
            let retorno = response.data;
            console.log("_____________");
            cadastrarProfissional(retorno.idEndereco);
        })
        .catch((error)=>{
            console.error(error);
        });

        function cadastrarProfissional(idEndereco){
            console.log(idEndereco);
            console.log(profissional);
            axios({
                method: 'POST',
                url: "http://localhost:8080/profissionais",
                type: "application/json",
                data: 
                {
                    cnpj: profissional.cnpj,
                    cpf: profissional.cpf,
                    dataNasc: profissional.dataNasc,
                    email: profissional.email,
                    endereco:{
                        idEndereco: idEndereco
                    },
                    nome: profissional.nome,
                    resumoQualificacoes: profissional.resumoQualificacoes,
                    senha: profissional.senha,
                    subcategoria:{
                        idSubcategoria: profissional.subcategoria.idSubcategoria
                    },
                    tipoUsuario:{
                        idTipoUsuario: profissional.tipoUsuario.idTipoUsuario
                    },
                    valorHora: profissional.valorHora
                }
            })
            .then((response)=>{
                console.log("*************");
                console.log(response.data);
            })
            .catch((error)=>{
                console.error(error);
            });
        }
    }

    function confirmarEmail(){
        // setModalShow(true);
        console.log(txtCodeConfirm+" "+codeConfirm);
        if(txtCodeConfirm == codeConfirm){
            cadastrarEndereco();
            // console.log(endereco);
            console.log("foi poxa");
        }else{
            console.log("nao vai dar nao");
        }
    }

    function definirTxtCodeConfirm(event){
        setTxtCodeConfirm(event.target.value);
        console.log(event.target.value);
    }

    useEffect(()=>{
        if(renderizar){
            $("#input-cod-confirm").attr("disabled", true);
            $("#btn-confirm").attr("disabled", true);

            console.log(codeConfirm);
            
            // setProfissional(JSON.parse(sessionStorage.getItem("profissional")));
            // setEndereco(JSON.parse(sessionStorage.getItem("endereco"))); 

            console.log(profissional.email);
            console.log(endereco);
            
            axios({
                method: 'POST',
                url: "http://localhost:8080/profissionais/confirmacao",
            data: {
                nome: profissional.nome,
                destinatario: profissional.email,
                codigoConfirm: codeConfirm
            }
            })
            .then((response)=>{
                console.log(response);
                console.log("enviado");
                $("#btn-confirm").attr("disabled", false);
                $("#input-cod-confirm").attr("disabled", false);
                
            })
            .catch((error)=>{
                    console.error(error);
            });
            setRenderizar(false);
        }
    });

    return(
        <section className="flex-center center">
            <div className="caixa-confirmacao-email">
                <div className="title-confirmacao center flex-center">
                    Verifique seu endereço de E-mail:
                </div>
                <div className="text-confirmacao flex-center">
                    Lorem ipsum dolor sit amet consectetur adipiscing elit ullamcorper velit nullam, lacinia aliquam himenaeos volutpat faucibus magnis torquent imperdiet rutrum, lectus per laoreet erat arcu morbi etiam
                </div>
                <div className="img-email flex-center center">
                    <figure>
                        <img src={EmailImg}  alt="Ícone E-mail"/>
                    </figure>
                </div>
                        
                {/* <form name="form_cod_email" action="index.html" method="POST"> */}
                    <div className="caixa-input-confirm flex-center center">
                        <div className="input-cod-confirm">
                            <input required onChange={definirTxtCodeConfirm} id="input-cod-confirm" className="input-cod-confirm"  type="text" pattern="[0-9]*" maxLength="4" name="cod_email"/>
                        </div>
                        <div className="img-check">
                            <ButtonToolbar>
                                <button onClick={() => confirmarEmail()} data-toggle="modal" data-target="#exampleModalCenter" type="submit" name="button" id="btn-confirm">
                                    <figure>
                                        <img src={check} alt="Confirmar" title="Confirmar" /> 
                                    </figure>
                                </button>
                                <ModalSucesso
                                    show={modalShow}
                                    onHide={() => setModalShow(false)}>

                                </ModalSucesso>
                            </ButtonToolbar>
                            
                        </div>
                    </div>
                {/* </form> */}
                <div className="links flex-center center">
                <Link to="/profissional/cadastro/confirmacao"> Reenviar E-mail? </Link>
                <Link to="/profissional/cadastro"> Alterar E-mail?</Link>
                </div>
            </div>
        </section>
    );
   }

   export default Confirmacao;