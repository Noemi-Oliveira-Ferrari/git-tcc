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
        // this.popularCategorias = this.popularCategorias.bind(this);
        this.state = {
           categorias: [],
           subcategorias: [],
        }
        this.getSubcategorias = this.getSubcategorias.bind(this);
    }

    componentDidMount(){
        this.getCategorias(this.getSubcategorias());
    }

    getCategorias(){
        axios.get(`http://localhost:8080/categorias`)
        .then((response)=>{
            let jsonCategorias = response.data;
            this.setState({categorias: jsonCategorias});
            this.getSubcategorias(jsonCategorias[0].idCategoria);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = $("#slt-categoria").append("<option id='loadCat' value=''>Carregando Tipos de Serviços.</option>");
    }
    getSubcategorias(idCategoria){
        
        console.log("=-=-=> "+idCategoria);
        if(idCategoria == null || idCategoria == ""){
            idCategoria = 1;
        }

        axios.get(`http://localhost:8080/subcategorias/categoria/${idCategoria}`)
        .then((response)=>{
            let jsonSubategorias = response.data;
            this.setState({subategorias: jsonSubategorias});

            $("#loadCat").remove();
            $("#slt-categoria").append("<option value=''>Selecione um Tipo Serviço</option>");
            
            $("#loadSubat").remove();
            $("#slt-subcat").empty();
            $("#slt-subcat").append("<option value=''>Selecione um Serviço</option>");
            for(let i = 0; i < jsonSubategorias.length; i++){
                console.log(jsonSubategorias[i]);
                $("#slt-subcat").append(`<option value="${jsonSubategorias[i].idSubcategoria}">${jsonSubategorias[i].subcategoria}</option>`);
            }
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = $("#slt-subcat").append("<option id='loadSubat' value=''>Carregando Serviços.</option>");
    }

    render(){
        return(
            <div className={this.props.classSelectPro}>
                <label className="form-label">{this.props.label}</label>
                <select
                    id={this.props.id}
                    name={this.props.name} 
                    className="form-control form-input"
                    onChange={this.props.onChange}>
                        {this.state.categorias.map(categoria=>(
                            <option key={categoria.idCategoria} value={categoria.idCategoria}>
                                {categoria.categoria}
                            </option>
                        ))}                
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
                        <option value="">Seleciona um Serviço</option>
                        {/* {this.state.subcategorias.map(subcategoria=>(
                            <option key={subcategoria.idSubcategoria} value={subcategoria.idSubategoria}>
                                {subcategoria.subcategoria}
                            </option>
                        ))} */}
                </select>
            </div> 
        );
    }
}
