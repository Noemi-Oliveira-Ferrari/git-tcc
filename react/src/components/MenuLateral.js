import React, { Component } from 'react';
import '../css/font-awesome.css';
import '../css/menu-lateral.css';
import Perfil from '../img/user.png';
import Servicos from '../img/suitcase.png';
import Renda from '../img/renda.png';
import { Link } from 'react-router';


export default class MenuLateral extends Component{


    render(){
        return(        
            <nav className="main-menu">
                <div>
                    <a className="logo" href="http://startific.com">
                    </a> 
                </div> 
                <div className="settings">
                    <h3>MENU</h3>
                </div>
                <div className="scrollbar" id="style-1">
                    
                    <ul>
                        <Link className="link" to="/app/profissional/perfil">
                            <li>
                                <div className="ola">
                                    <i className="fa fa-lg">
                                        <figure>
                                            <img id="img-perfil-sidebar" src={Perfil}/>
                                        </figure>
                                    </i>
                                    <span className="nav-text">Meu Perfil</span>
                                </div>
                            </li>   
                        </Link>

                        <Link className="link" to="/app/profissional/servicos">             
                            <li>       
                                <div className="ola">
                                    <i className="fa fa-lg">
                                        <figure>
                                            <img id="img-perfil-sidebar" src={Servicos}/>
                                        </figure>
                                    </i>
                                    <span className="nav-text">Meus Serviços</span>
                                </div>
                            </li>
                        </Link>

                        <Link className="link" to="/app/profissional/renda">             
                            <li>       
                                <div className="ola">
                                    <i className="fa fa-lg">
                                        <figure>
                                            <img id="img-perfil-sidebar" src={Renda}/>
                                        </figure>
                                    </i>
                                    <span className="nav-text">Minha Renda</span>
                                </div>
                            </li>
                        </Link>
                    </ul>
                </div>
            </nav>
        );
    }
}