import React, { Component } from 'react';
import '../css/cadastro-pro.css';
import $ from 'jquery';
import axios from 'axios';


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

    constructor(){
        super();        
        this.popularCategorias = this.popularCategorias.bind(this);
    }
    componentDidMount(){
        axios.get(`http://localhost:8080/categorias`)
        .then((response)=>{
            let jsonCategorias = response.data;
            this.popularCategorias(jsonCategorias);
            // console.log(jsonCategorias);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("Carregando categorias...");
    }

    popularCategorias(jsonCategorias){
        console.log(jsonCategorias.length);
        for(let i = 0; i < jsonCategorias.length; i++){
            // $("#slt-categoria").append(`<option value="${jsonCategorias[i].idCategoria}">${jsonCategorias[i].categoria}</option>`);
            let o = new Option(`${jsonCategorias[i].categoria}`, `${jsonCategorias[i].idCategoria}`);
            $(o).html(`${jsonCategorias[i].categoria}`);
            $("#slt-categoria").append(o);
        }
    }
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
