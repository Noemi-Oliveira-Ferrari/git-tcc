import React, { Component } from 'react';
import '../css/cadastro-pro.css';


export class InputCadastroPro extends Component{
    render(){
        return(
            <div className={this.props.classInputPro}>
                <label className="form-label">{this.props.label}</label>
                <input 
                    id={this.props.id} type={this.props.type} 
                    name={this.props.name} 
                    maxLength={this.props.maxLength}
                    type={this.props.type}
                    className="form-control form-input"
                    onChange={this.props.onChange}/>
            </div>

        );
    }
}

export class SelectCategoriaPro extends Component{
    render(){
        return(
            <div className={this.props.classSelectPro}>
                <label className="form-label">{this.props.label}</label>
                <select
                    id={this.props.id}
                    name={this.props.name} 
                    className="form-control form-input">
                        
                </select>
            </div> 
        );
    }
}

export class SelectSubcategoriaPro extends Component{
    render(){
        return(
            <div className={this.props.classSelectPro}>
                <label className="form-label">{this.props.label}</label>
                <select
                    id={this.props.id}
                    name={this.props.name} 
                    className="form-control form-input">
                        
                </select>
            </div> 
        );
    }
}
