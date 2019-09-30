import React, { Component } from 'react';
import '../css/login.css';


export class InputLogin extends Component{
   render(){
    return(
        
        // <div className="caixa-input"> 
        //     <div className="label">
        //         <label>{this.props.label}:</label>
        //     </div>
        //     <input className="input" type={this.props.type} name="" value=""/>
        // </div>

        <div className="caixa-campo-login">
            <label>{this.props.label}:</label>
            <input class="input-login" type={this.props.type} name="" value=""/>
        </div>
        
    );
   }

}

export default InputLogin;
