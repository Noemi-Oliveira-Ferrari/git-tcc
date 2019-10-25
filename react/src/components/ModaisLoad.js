
import React from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-bootstrap/Modal';
import close from '../img/close.png';
import "../css/modais.css";


export function ModalLoadFun (props) {

    return(        
        <Modal {...props}
        size="sm"
        className="fundo-modal">
            <div className="caixa-function-load caixa-load">
                {/* <Modal.Header>
                </Modal.Header> */}
                <Modal.Body>
                    <div className="load-function load-5">
                        {/* <p>Carregando...</p> */}
                        <div className="txt-load flex-center">
                            <div className="letter-holder flex-center">
                                <div className="l-1 letter">C</div>
                                <div className="l-2 letter">a</div>
                                <div className="l-3 letter">r</div>
                                <div className="l-4 letter">r</div>
                                <div className="l-5 letter">e</div>
                                <div className="l-6 letter">g</div>
                                <div className="l-7 letter">a</div>
                                <div className="l-8 letter">n</div>
                                <div className="l-9 letter">d</div>
                                <div className="l-10 letter">o</div>
                                <div className="l-11 letter">.</div>
                                <div className="l-12 letter">.</div>
                                <div className="l-13 letter">.</div>
                            </div>
                        </div>
                        <div className="anel-load">
                            <div className="circulo-load">
                                <div className="bola-load"></div>
                            </div>
                        </div>
                    </div>
                </Modal.Body>
                {/* <Modal.Footer>
                </Modal.Footer> */}

            </div>
        </Modal>
        
    );
}


export function ModalAlertasFun (props) {

    return(        
        <Modal {...props}
        size="sm"
        className="fundo-modal">
            <div className="caixa-modal-erros-function">
                <Modal.Header className="header-modal-erros-function">
                    <div className="titulo-modal-erros-function flex-center">
                        <h3 className={props.tipoAlerta}>{props.titulo}</h3>
                    </div>
                    <div className="close-modal flex-center" onClick={props.onHide}>
                        <figure>
                            <img src={close} alt="Fechar" title="Fechar"/>
                        </figure>
                    </div>
                </Modal.Header>
                <Modal.Body className="body-modal-erros-function">
                    <div className="txt-modal-erros-function">
                        <ul>
                        {props.erros.map((erro, i=1)=>(
                                <li key={++i}>{erro}</li>
                            ))
                        }
                        </ul>
                    </div>
                </Modal.Body>
                {/* <Modal.Footer>
                </Modal.Footer> */}
            </div>
        </Modal>
        
    );
}

export const ModalAlertas = ({tipoAlerta, titulo, erros, onClose, abrir}) => 

    abrir 
    ? 
    ReactDOM.createPortal(
        <div className="fundo-modal">
            <div className="caixa-modal-erros caixa-load">
                <div className="titulo-modal-erros flex-center">
                    <h3 className={tipoAlerta}>{titulo}</h3>
                </div>
                <div className="close-modal flex-center" onClick={onClose}>
                    <figure>
                        <img src={close} alt="Fechar" title="Fechar"/>
                    </figure>
                </div>
                <div className="txt-modal-erros">
                    <ul>
                    {erros.map((erro, i=1)=>(
                            <li key={++i}>{erro}</li>
                        ))
                    }
                    </ul>
                </div>
            </div>
        </div>,
        document.body
    )
    :
    null;



export const ModalLoadConst = ({onClose, abrir}) => 

    abrir 
    ? 
    ReactDOM.createPortal(
        <div className="fundo-modal">
            <div className="caixa-const-load caixa-load">
                <div className="load-const load-5">
                    {/* <p>Carregando...</p> */}
                    <div className="txt-load flex-center">
                        <div className="letter-holder flex-center">
                            <div className="l-1 letter">C</div>
                            <div className="l-2 letter">a</div>
                            <div className="l-3 letter">r</div>
                            <div className="l-4 letter">r</div>
                            <div className="l-5 letter">e</div>
                            <div className="l-6 letter">g</div>
                            <div className="l-7 letter">a</div>
                            <div className="l-8 letter">n</div>
                            <div className="l-9 letter">d</div>
                            <div className="l-10 letter">o</div>
                            <div className="l-11 letter">.</div>
                            <div className="l-12 letter">.</div>
                            <div className="l-13 letter">.</div>
                        </div>
                    </div>
                    <div className="anel-load">
                        <div className="circulo-load">
                            <div className="bola-load"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>,
        document.body
    )
    :
    null;