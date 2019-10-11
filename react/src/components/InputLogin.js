import React, { Component } from 'react';
import '../css/login.css';
import InputMask from 'react-input-mask';


export class MeuInput extends Component {
    state = {
        value: ''
    }

    onChange = (event) => {
        this.setState({
            value: event.target.value
        });
    }
/*
    // beforeMaskedValueChange = (newState, oldState, userInput) => {
    //     var { value } = newState;
    //     var selection = newState.selection;
    //     var cursorPosition = selection ? selection.start : null;

    //     // keep minus if entered by user
    //     if (value.endsWith('-') && userInput !== '-' && !this.state.value.endsWith('-')) {
    //         if (cursorPosition === value.length) {
    //             cursorPosition--;
    //             selection = { start: cursorPosition, end: cursorPosition };
    //         }
    //         value = value.slice(0, -1);
    //     }

    //     return {
    //         value,
    //         selection
    //     };
    // }
*/
    render() {
        return <InputMask mask="99999-9999" className="input-login"/>;
    }
}

export class InputLogin extends Component{
   render(){
    return(

        // <Inputs 
        //     classDivInputPro="caixa-campo-login"
        //     label="E-mail"
        //     classInput="input-login"
        //     type="email"
        //     name="txt_email"/>


        <div className="caixa-campo-login">
            <label>{this.props.label}:</label>
            <input className="input-login" type={this.props.type} name=""/>
        </div>
        
    );
   }

}

