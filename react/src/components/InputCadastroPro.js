import React, { Component } from 'react';
import '../css/cadastro-pro.css';


export class InputCadastroPro extends Component{
    render(){
        return(
            <div className={this.props.classInputPro}>
                <label className="form-label">{this.props.label}</label>
                <input required
                    id={this.props.id} type={this.props.type} 
                    name={this.props.name} 
                    maxLength={this.props.maxLength}
                    type={this.props.type}
                    className="form-control form-input"
                    onChange={this.props.onChange}
                    data-idCidade={this.props.data}/>
            </div>

        );
    }
}

export class SelectCategoriaPro extends Component{

    render(){
        return(
            <div className={this.props.classSelectPro}>
                <label className="form-label">{this.props.label}</label>
                <select required
                    id={this.props.id}
                    name={this.props.name} 
                    className="form-control form-input"
                    onChange={this.props.onChange}>
                        {/* {this.state.categorias.map(categoria=>(
                            <option key={categoria.idCategoria} value={categoria.idCategoria}>
                                {categoria.categoria}
                            </option>
                        ))}*/}
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
                <select required 
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
