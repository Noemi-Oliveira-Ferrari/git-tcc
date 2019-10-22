import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { Route, Router, browserHistory, IndexRoute } from 'react-router';
import Login from './pages/Login';
import CardsEscolha from './pages/CardsEscolha';
import CadastroPro from './pages/CadastroPro';
import Confirmacao from './pages/Confirmacao';
import CadastroCliente from './pages/CadastroCliente';
import PerfilPro from './pages/PerfilPro';
import ServicosPro from './pages/ServicosPro';

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Login}/>
            <Route path="/escolha" component={CardsEscolha}/>
            <Route path="/profissional/cadastro" component={CadastroPro}/>
            <Route path="/cliente/cadastro" component={CadastroCliente}/>
            <Route path="/cadastro/confirmacao" component={Confirmacao}/>
            <Route path="/app/perfilpro" component={PerfilPro}/>
            <Route path="/app/servicospro" component={ServicosPro}/>
        </Route>
    </Router>,    
    document.getElementById('root')
);
