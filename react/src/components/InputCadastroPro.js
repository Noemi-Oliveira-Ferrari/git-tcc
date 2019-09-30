import React, { Component } from 'react';
import '../css/cadastro-pro.css';


export class InputCadastroPro extends Component{
   render(){
    return(
        <div className={this.props.classInputPro}>
            <label className="form-label">{this.props.label}</label>
            <input maxlength={this.props.maxlength} id={this.props.id} type={this.props.type} value={this.props.value} name={this.props.name} className="form-control form-input"/>
        </div>
 
    );
   }

}

export default InputCadastroPro;
