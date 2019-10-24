import React, { useEffect, useState } from 'react';
import {Link} from 'react-router';
import EmailImg from '../img/emailcheck.png';
import check from '../img/check.png';
import ButtonToolbar from '../../node_modules/react-bootstrap/ButtonToolbar';
import $ from 'jquery';
import axios from 'axios';
import Loading from '../components/Loading'
import {browserHistory} from 'react-router';

import '../css/confirmacao.css';
import ModalSucesso from '../components/ModalSucesso';
import {ModalLoadFun} from '../components/ModaisLoad';

function Confirmacao() {


    const [modalShow, setModalShow] = useState(false);
    const [initLoad, setInitLoad] = useState(false);
    const [codeConfirm, setCodeConfirm] = useState(random(1000, 9999));
    const [txtCodeConfirm, setTxtCodeConfirm] = useState("");
    const [renderizar, setRenderizar] = useState(true);
    const [profissional, setProfissional] = useState(JSON.parse(sessionStorage.getItem("profissional")));
    const [cliente, setCliente] = useState(JSON.parse(sessionStorage.getItem("cliente")));
    const [endereco, setEndereco] = useState(JSON.parse(sessionStorage.getItem("endereco")));
    // const [usuario, setUsuario] = useState(JSON.parse(sessionStorage.getItem("endereco")));
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
            if(profissional === null){
                cadastrarCliente(retorno.idEndereco);
            }else if(cliente === null){
                cadastrarProfissional(retorno.idEndereco);
            }
        })
        .catch((error)=>{
            setInitLoad(false);
            console.error(error);
        })
        .onload = setInitLoad(true);

        function cadastrarProfissional(idEndereco){
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
                sessionStorage.clear();
                setInitLoad(false);
                setModalShow(true);
            })
            .catch((error)=>{
                setInitLoad(false);
                console.error(error);
            })
            .onload = setInitLoad(true);
        }

        function cadastrarCliente(idEndereco){
            axios({
                method: 'POST',
                url: "http://localhost:8080/clientes",
                type: "application/json",
                data: 
                {
                    cpf: cliente.cpf,
                    dataNasc: cliente.dataNasc,
                    email: cliente.email,
                    endereco:{
                        idEndereco: idEndereco
                    },
                    nome: cliente.nome,
                    senha: cliente.senha,
                    tipoUsuario:{
                        idTipoUsuario: cliente.tipoUsuario.idTipoUsuario
                    }
                }
            })
            .then((response)=>{
                sessionStorage.clear();
                setInitLoad(false);
                setModalShow(true);
            })
            .catch((error)=>{
                setInitLoad(false);
                console.error(error);
            })
            .onload = setInitLoad(true);
        }
    }


    function confirmarEmail(){
        console.log(txtCodeConfirm+" "+codeConfirm);
        if(txtCodeConfirm == codeConfirm){
            cadastrarEndereco();
        }else{
            console.log("codigo inválido");
        }
    }

    function definirTxtCodeConfirm(event){
        setTxtCodeConfirm(event.target.value);
        console.log(event.target.value);
    }

    function getUsuario(){
        
        let tipoCadastro;
        let usuario;

        console.log(codeConfirm);
        
        console.log(profissional);
        console.log(cliente);
        console.log(endereco);
        
        if(profissional === null){
            tipoCadastro = "clientes";
            usuario = cliente
        }else if(cliente === null){
            tipoCadastro = "profissionais";
            usuario = profissional;
        }
        console.log(tipoCadastro);

        axios({
            method: 'POST',
            url: `http://localhost:8080/${tipoCadastro}/confirmacao`,
        data: {
            nome: usuario.nome,
            destinatario: usuario.email,
            codigoConfirm: codeConfirm
        }
        })
        .then((response)=>{
            console.log(response);
            console.log("enviado");
            $("#btn-confirm").attr("disabled", false);
            $("#input-cod-confirm").attr("disabled", false);
            setInitLoad(false);
        })
        .catch((error)=>{
            setInitLoad(false);
            console.error(error);
        })
        .onload = setInitLoad(true);
    }

    useEffect(()=>{
        if(renderizar){
            $("#input-cod-confirm").attr("disabled", true);
            $("#btn-confirm").attr("disabled", true);
            console.clear();
            getUsuario();
            setRenderizar(false);
        }
    });

    return(
        <section className="flex-center center">
            <div className="caixa-confirmacao-email">
                <div className="title-confirmacao center flex-center">
                    Confirmação de E-mail
                </div>
                <div className="text-confirmacao flex-center">
                    <p>Um código de verificação foi enviado ao e-mail <span className="negrito">{cliente === null ? profissional.email : cliente.email}</span>. Verifique sua caixa de entrada e escreva o código no campo à baixo para finalizar o cadastro.</p>
                </div>
                <div className="img-email flex-center center">
                    <figure>
                        <img src={EmailImg}  alt="Ícone E-mail"/>
                    </figure>
                    {/* <Loading/> */}
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
                                    onHide={() => setModalShow(false)}/>
                                <ModalLoadFun
                                    show={initLoad}
                                    // onHide={() => setInitLoad(false)}
                                    />

                            </ButtonToolbar>
                            
                        </div>
                    </div>
                {/* </form> */}
                <div className="links-email center">
                    <button onClick={() => getUsuario()} className="link-reenviar-email "> Reenviar E-mail </button>
                    <button onClick={()=>{browserHistory.push(profissional === null ? "/cliente/cadastro" : "/profissional/cadastro")}} className="link-alterar-email "> Alterar E-mail</button>
                </div>
            </div>
        </section>
    );
   }

   export default Confirmacao;