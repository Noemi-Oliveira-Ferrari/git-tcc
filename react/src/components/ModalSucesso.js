import React, { Component } from 'react';
import '../css/modalSucesso.css';
import Modal from 'react-bootstrap/Modal';
import Botao from './Botao';
// import singCheck from '../img/iconfinder_sign-check_299110.png';
import { Link } from 'react-router';
function ModalSucesso (props) {

    return(

        
        <Modal {...props}
        size="lg"
        className=" modal center flex-center">
            <div className="caixa-modal flex-center">
                <Modal.Header>
                    <div className="img-confirm flex-center">
                        <figure>
                            {/* <img src={singCheck}/> */}
                        </figure>

                    </div>                   
                </Modal.Header>
                <Modal.Body>
                    <div className="title-confirm">
                        Cadastro realizado com sucesso
                    </div>
                    <div className="text-confirm">
                        Agora é só logar com o seu usuario e aproveitar os nossos serviços
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <div className="btn flex-center">
                        <Link to="/">
                            <Botao
                            classBotao="btn-inicio"
                            valueBotao="Retornar à página inicial">
                            </Botao>
                        </Link>
                    </div>
                </Modal.Footer>

            </div>


        </Modal>
        
    );
   }

export default ModalSucesso;

