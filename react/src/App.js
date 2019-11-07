import React from 'react';
import { Component, Fragment } from 'react';
import Header from './components/Header';
// import MenuResp from './components/MenuResp';
// import Footer from './components/Footer';

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
					{this.props.children}
 					<div className="aux-footer"/>
				</div>
		{/* <Footer/> */}
	</Fragment>
  );
}

}



export default App;
