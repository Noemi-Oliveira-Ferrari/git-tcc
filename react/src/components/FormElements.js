import React, { Component, Fragment } from 'react';
import '../css/cadastro-pro.css';
import InputMask from 'react-input-mask';
import CurrencyFormat from 'react-currency-format';


export class InputNumber extends Component {
    render() {
        return(
            <div className={this.props.classDivInput}>
                <label htmlFor={this.props.forInput} className="form-label">{this.props.label}</label>
                <CurrencyFormat 
                    id={this.props.id}
                    type={this.props.type}
                    name={this.props.name}
                    maxLength={this.props.maxLengthInput}
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
                    mask={this.props.tempMask}
                    suffix={this.props.sufixo}
                />
            </div>
        );
    }
}

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
            <div className={this.props.classDivInput}>
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
                    maskChar=""
                    checked={this.props.radioChecked}
                    onBlur={this.props.onBlur}
                    ref={this.props.ref}
                />
            </div>
        );
    }
}
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

export class InputFaq extends Component{
    render(){
     return(
 
         // <Inputs 
         //     classDivInput="caixa-campo-login"
         //     label="E-mail"
         //     classInput="input-login"
         //     type="email"
         //     name="txt_email"/>
 
        
         <input 
         className={this.props.classInput} type={this.props.type} name="" id={this.props.idSelect}/>
         
     );
    }
 
 }

export class InputProblema extends Component{
    render(){
     return(
 
        <Fragment>
            <label>{this.props.label}</label>
            <input id={this.props.id} type="" value="" name="" className={this.props.classInput} />
        </Fragment>
         
     );
    }
 
 }