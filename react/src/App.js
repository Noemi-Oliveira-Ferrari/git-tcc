import React from 'react';
import { Component } from 'react';

// css e js
import './css/confirmacao.css';
import './css/login.css';
import './css/escolha.css';
import './css/cadastro-pro.css';
import './css/bootstrap.css';

import './utils/cardChooseAnimation';


class App extends Component{
	render(){

	
  return (
	<div id="all">

            {this.props.children}

            {/* <!-- div para separa o conteudo do footer --> */}
            <div id="espaco">

            </div>
            <footer hidden>

            </footer>
        </div>



  );
}

}



export default App;
