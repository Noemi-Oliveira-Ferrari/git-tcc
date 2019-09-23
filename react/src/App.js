import React from 'react';
import { Component } from 'react';

// css e js
import './css/App.css';
import './utils/cardChooseAnimation';

// componentes
import CardsEscolha from './components/CardsEscolha';
import InputLogin from './components/InputLogin';
import DaUmHelp from './components/DaUmHelp';
import Login from './components/Login';

class App extends Component{
	render(){

	
  return (
	<div id="all">

{/* TELA DE ESCOLHA */}
		{/* <CardsEscolha>

		</CardsEscolha> */}

{/* TELA DO LOGIN */}
                
        <Login>
            
        </Login>


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
