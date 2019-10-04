import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { Route, Router, browserHistory, IndexRoute } from 'react-router';
import Login from './pages/Login';
import CardsEscolha from './pages/CardsEscolha';
import CadastroPro from './pages/CadastroPro';
import Confirmacao from './pages/Confirmacao';

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Login}/>
            <Route path="/escolha" component={CardsEscolha}/>
            <Route path="/profissional/cadastro" component={CadastroPro}/>
            <Route path="/profissional/cadastro/confirmacao" component={Confirmacao}/>
        </Route>
    </Router>,    
    document.getElementById('root')
);
