
import React from 'react';
import Modal from 'react-bootstrap/Modal';
import "../css/padroes.css";


function ModalLoad (props) {

    return(        
        <Modal {...props}
        size="sm"
        className="fundo-load">
            <div className="caixa-load">
                {/* <Modal.Header>          
                </Modal.Header> */}
                <Modal.Body>
                    <div className="load-5">
                        {/* <p>Carregando...</p> */}
                        <div className="txt-load flex-center">
                            <div className="letter-holder">
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

export default ModalLoad;

