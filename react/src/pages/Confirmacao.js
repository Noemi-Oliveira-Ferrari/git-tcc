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

    function random (min, max){
        return Math.trunc(Math.random() * (max + 1 - min) + min);
    }

    function cadastrar(){
        axios({
            method: 'POST',
            url: "http://localhost:8080/enderecos",
            data: {endereco}
        })
        .then((response)=>{
            console.log(response.data);
        })
        .catch((error)=>{
            console.error(error);
        });
    }

    function confirmarEmail(){
        // setModalShow(true);
        console.log(txtCodeConfirm+" "+codeConfirm);
        if(txtCodeConfirm == codeConfirm){
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

            console.log(codeConfirm);
            
            // setProfissional(JSON.parse(sessionStorage.getItem("profissional")));
            // setEndereco(JSON.parse(sessionStorage.getItem("endereco"))); 

            console.log(profissional.email);
            console.log(endereco);
            setTimeout(()=>{
                console.log(profissional.email);
            }, 2000);
            
            // axios({
                //     method: 'POST',
                //     url: "http://localhost:8080/profissionais/confirmacao",
        //     data: {
        //         nome: profissional.nome,
        //         destinatario: profissional.email,
        //         codigoConfirm: codeConfirm
        //     }
        // })
        // .then((response)=>{
        //     console.log(response);
        // })
        // .catch((error)=>{
            //     console.error(error);
            // });
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
                            <input required onChange={definirTxtCodeConfirm} className="input-cod-confirm"  type="text" pattern="[0-9]*" maxLength="4" name="cod_email"/>
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