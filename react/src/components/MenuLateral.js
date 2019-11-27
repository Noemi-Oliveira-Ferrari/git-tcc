import React, { Component } from 'react';
import '../css/font-awesome.css';
import '../css/menu-lateral.css';
import Perfil from '../img/user.png';
import Servicos from '../img/suitcase.png';
import { Link } from 'react-router';


export default class MenuLateral extends Component{


    render(){
        return(        
            <nav class="main-menu">
                <div>
                    <a class="logo" href="http://startific.com">
                    </a> 
                </div> 
                <div class="settings"></div>
                <div class="scrollbar" id="style-1">
                    
                    <ul>
                        <Link className="link" to="/app/profissional/perfil">
                            <li>
                                <div className="ola">
                                    <i className="fa fa-lg">
                                        <figure>
                                            <img id="img-perfil-sidebar" src={Perfil}/>
                                        </figure>
                                    </i>
                                    <span class="nav-text">Meu Perfil</span>
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
                                    <span class="nav-text">Meus Serviços</span>
                                </div>
                            </li>
                        </Link>
                    </ul>
                </div>
            </nav>
        );
    }
}