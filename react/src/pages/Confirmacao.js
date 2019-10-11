import React from 'react';
import {Link} from 'react-router';
import EmailImg from '../img/emailcheck.png';
import check from '../img/check.png';
import ButtonToolbar from '../../node_modules/react-bootstrap/ButtonToolbar';

import '../css/confirmacao.css';
import ModalSucesso from '../components/ModalSucesso';

function Confirmacao() {

    const [modalShow, setModalShow] = React.useState(false);

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
                
        
                <form name="form_cod_email" action="index.html" method="POST">
                    <div className="caixa-input-confirm flex-center center">
                        <div className="input-cod-confirm">
                            <input required className="input-cod-confirm"  type="text" pattern="[0-9]*4" maxLength="4" name="cod_email"/>
                        </div>
                        <div className="img-check">
                            <ButtonToolbar>
                                <button onClick={() => setModalShow(true)} data-toggle="modal" data-target="#exampleModalCenter" type="submit" name="button" id="btn-confirm">
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
                </form>
                <div className="links flex-center center">
                <Link to="/profissional/cadastro/confirmacao"> Reenviar E-mail? </Link>
                <Link to="/profissional/cadastro"> Alterar E-mail?</Link>
                </div>
            </div>
        </section>
    );
   }

   export default Confirmacao;