import React, { Component } from 'react';
import '../css/App.css';


export class InputLogin extends Component{
   render(){
    return(
        
        <div className="caixa-input"> 
            <div className="label">
                <label>{this.props.label}:</label>
            </div>
            <input className="input" type={this.props.type} name="" value=""/>
        </div>
        
    );
   }

}

export default InputLogin;
