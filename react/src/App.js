import React from 'react';
import { Component, Fragment } from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import MenuLateral from './components/MenuLateral';
import {getToken} from './utils/verificaSessionStrg';

// css e js
import "./css/padroes.css";
import "./css/bootstrap.css";

import './utils/cardChooseAnimation';


class App extends Component{
	render(){

	
return (
	<Fragment>
		<Header/>
		<div className="aux-header"/>
		<div id="all">
			{getToken() !== "" ? getToken() !== null ? <MenuLateral/> : "" : ""}
			{/* // <MenuLateral/> */}
			{this.props.children}

			<div className="aux-footer"/>
		</div>
		<Footer/>
	</Fragment>
  );
}

}



export default App;
