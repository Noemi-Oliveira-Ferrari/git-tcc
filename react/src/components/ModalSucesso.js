import React from 'react';
// import '../css/modalSucesso.css';
import "../css/modais.css";
import Modal from 'react-bootstrap/Modal';
import {Botao} from './Botao';
import Sucesso from '../img/success.png';
import { Link, browserHistory } from 'react-router';

function ModalSucesso (props) {

    return(        
        <Modal {...props} size="md"
        className="caixa-modal-sucesso flex-center">
            {/* <Modal.Header id="header-modal-sucesso">
            </Modal.Header> */}
            <Modal.Body id="body-modal-sucesso">
                <div className="img-modal-sucesso flex-center">
                    <figure>
                        <img src={Sucesso}/>
                    </figure>
                </div>
                <div className="title-modal-sucesso">
                    Cadastro realizado com sucesso
                </div>
                <div className="text-modal-sucesso">
                    Agora é só logar com o seu usuario e aproveitar os nossos serviços
                </div>
                <div className="caixa-btn-modal-sucesso flex-center">
                    {/* <Link to="/"> */}
                        <Botao
                        classBotao="btn-sucesso-login"
                        valueBotao="Retornar à página inicial"
                        clickBotao={()=>{browserHistory.push("/")}}>
                        </Botao>
                    {/* </Link> */}
                </div>
            </Modal.Body>
            {/* <Modal.Footer id="footer-modal-sucesso">
            </Modal.Footer> */}
        </Modal>
        
    );
   }

export default ModalSucesso;

