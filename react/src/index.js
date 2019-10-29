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

ReactDOM.render(
    <Router history={browserHistory}>
        {/* <Route path="/" component={App}> */}
        <Route path={process.env.PUBLIC_URL + '/'} component={App}>
            <IndexRoute component={Login}/>
            <Route path={process.env.PUBLIC_URL + '/escolha'} component={CardsEscolha}/>
            <Route path={process.env.PUBLIC_URL + '/profissional/cadastro'} component={CadastroPro}/>
            <Route path={process.env.PUBLIC_URL + '/cliente/cadastro'} component={CadastroCliente}/>
            <Route path={process.env.PUBLIC_URL + '/cadastro/confirmacao'} component={Confirmacao}/>
        </Route>
    </Router>,    
    document.getElementById('root')
);
