import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import InputMask from 'react-input-mask';
import CurrencyFormat from 'react-currency-format';


export class InputNumber extends Component {
    render() {
        return(
            <div className={this.props.classDivInputPro}>
                <label htmlFor={this.props.forInput} className="form-label">{this.props.label}</label>
                <CurrencyFormat
                    id={this.props.id}
                    type={this.props.type}
                    name={this.props.name}
                    maxLengthInput={this.props.maxLength}
                    className={this.props.classInput}
                    onChange={this.props.onChange}
                    data={this.props.data}
                    value={this.props.valueInput}
                    // // readOnly={this.props.readOnly}
                    format={this.props.mascara}
                    radioChecked={this.props.radioChecked}
                    thousandSeparator={this.props.separadorMilhar}
                    decimalSeparator={this.props.separadorDecimal}
                    allowNegative={this.props.permitirNegativo}
                    prefix={this.props.prefixo}
                    decimalScale={this.props.qtdDecimal}
                    mask="_"
                    suffix={this.props.sufixo}
                />
            </div>
        );
    }
}
// export class InputMascara extends Component {
//     state = {
//         value: ''
//     }

//     onChange = (event) => {
//         this.setState({
//             value: event.target.value
//         });
//     }
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
//     render() {
//         return <InputMask
//             mask={this.props.mascaraInput}
//             id={this.props.idInput}
//             type={this.props.typeInput} 
//             name={this.props.nameInput} 
//             // maxLength={this.props.maxLengthInput}
//             className={this.props.classInput}
//             onChange={this.props.onChangeInput}
//             data-idCidade={this.props.dataInput}
//             value={this.props.valueInput}
//             // readOnly={this.props.readOnly}
//             maskChar=""
//             checked={this.props.radioChecked}/>;
//     }
// }
export class Inputs extends Component {
    state = {
        value: ''
    }

    onChange = (event) => {
        this.setState({
            value: event.target.value
        });
    }
    render() {
        return (
            <div className={this.props.classDivInputPro}>
                <label htmlFor={this.props.forInput} className="form-label">{this.props.label}</label>
                <InputMask
                    mask={this.props.mascara}
                    id={this.props.id}
                    type={this.props.type} 
                    name={this.props.name} 
                    maxLength={this.props.maxLength}
                    className={this.props.classInput}
                    onChange={this.props.onChange}
                    data-idCidade={this.props.data}
                    value={this.props.valueInput}
                    // readOnly={this.props.readOnly}
                    maskChar=""
                    checked={this.props.radioChecked}
                />
            </div>
        );
    }
}
// export class ttt extends Component{
//     render(){
//         return(
//             <div className={this.props.classDivInputPro}>
//                 <label htmlFor={this.props.forInput} className="form-label">{this.props.label}</label>
//                 <InputMascara
//                     idInput={this.props.id}
//                     typeInput={this.props.type} 
//                     nameInput={this.props.name}
//                     // maxLengthInput={this.props.maxLength}
//                     classInput={this.props.classInput}
//                     onChangeInput={this.props.onChange}
//                     dataInput={this.props.data}
//                     valueInput={this.props.valueInput}
//                     // readOnly={this.props.readOnly}
//                     mascaraInput={this.props.mascara}
//                     radioChecked={this.props.radioChecked}
//                     />
//             </div>

//         );
//     }
// }


export class Selects extends Component{

    render(){
        return(
            <div className={this.props.classDivSelect}>
                <label className="form-label">{this.props.labelSelect}</label>
                <select 
                    id={this.props.idSelect}
                    name={this.props.nameSelect} 
                    className="form-control form-input"
                    onChange={this.props.onChangeSelect}>
                        <option value="">{this.props.firstOption}</option>
                        {this.props.optionsSelect}
                </select>
            </div> 
        );
    }
}