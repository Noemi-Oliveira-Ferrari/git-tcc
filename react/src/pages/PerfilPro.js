import React, { Component, Fragment } from 'react';
import '../css/perfil-pro.css';
import '../css/bootstrap.css';
import '../css/padroes.css';
import $ from 'jquery';
import AvaliacaoPro from '../components/AvaliacaoPro';
import CapaPerfilPro from '../components/CapaPerfilPro';
import { DadosPessoaisPro, DadosProfissional } from '../components/FormularioProfissional';
import Edit from '../img/edit.png';
import Cancel from '../img/close.png';
import Save from '../img/save.png';


export class PerfilPro extends Component{

    constructor() {
        super();
        this.state = {
            nomePro: "", servicoPro: "",
            localPro: "", notaPro: "",
            valorPro: "", fotoPro: "",
            iconEdit: Edit, atualizar: false
        }
        this.enableFields = this.enableFields.bind(this);
        this.setOriginalData = this.setOriginalData.bind(this);
    }

    componentDidMount(){
        this.setOriginalData();
    }
    setOriginalData(){
        console.log("lupa linda");
        let pro = JSON.parse(sessionStorage.getItem("profissional"));

        let localPro = `${pro.endereco.cidade.cidade}, ${pro.endereco.cidade.microrregiao.uf.estado} - ${pro.endereco.cidade.microrregiao.uf.uf}`
        let valorHora = pro.valorHora.toString().replace(".", ",");

        if(pro !== null && pro !== ""){
            this.setState({nomePro: pro.nome});
            this.setState({servicoPro: pro.subcategoria.subcategoria});
            this.setState({localPro: localPro});
            this.setState({valorPro: valorHora});
            this.setState({fotoPro: "url(https://images.pexels.com/photos/2760241/pexels-photo-2760241.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260)"});
        }
    }
    enableFields(){
        console.log("habili");
        
        if(!this.state.atualizar){
            this.setState({iconEdit: Cancel});
            $("input, select, textarea").removeAttr("disabled");
            $("input, select, textarea").css("background-color", "#CCE5E9");
            $(".text-atualizar-perfil ").css("background-color", "#efefef");
        }else{
            this.setState({iconEdit: Edit});
            $("input, select, textarea").attr("disabled", "disabled");
            $("input, select, textarea").css("background-color", "#d5d5d5");
            $(".text-atualizar-perfil ").css("background-color", "#c3c3c3");
            this.setOriginalData();
        }
        this.setState({atualizar: !this.state.atualizar});
    }


    render(){
        return(
            <Fragment>
                <CapaPerfilPro
                    nomePro={this.state.nomePro}
                    servicoPro={this.state.servicoPro}
                    localPro={this.state.localPro}
                    notaPro="Média Geral: 4.9"
                    valorPro={`R$ ${this.state.valorPro}`}
                    fotoPro={this.state.fotoPro}
                />
                <div className="caixa-conteudo-perfil-pro">
                    <div className="container-flex">
                        <div className="caixa-control-perfil flex-center">
                            <div className="text-editar-perfil flex-center" onClick={this.enableFields}>
                                <figure>
                                    <img src={this.state.iconEdit}/>
                                </figure>
                            </div>
                            <div className="text-atualizar-perfil flex-center" onClick={this.enableFields}>
                                <figure>
                                    <img src={Save}/>
                                </figure>
                            </div>
                        </div>
                        <DadosPessoaisPro/>
                        <DadosProfissional/>
                    </div>
                    <AvaliacaoPro/>
                </div>
            </Fragment>
        );
    }

}

export default PerfilPro;