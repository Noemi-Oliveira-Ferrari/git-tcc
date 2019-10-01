import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { Route, Router, browserHistory, IndexRoute } from 'react-router';
import Login from './components/Login';
import CardsEscolha from './components/CardsEscolha';
import CadastroPro from './components/CadastroPro';
import Confirmacao from './components/Confirmacao';

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
