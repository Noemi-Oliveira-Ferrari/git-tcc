import React, { Component, Fragment } from 'react';
import '../css/capa-perfil-pro.css';
import axios from 'axios';
import { Botao } from '../components/Botao';
import { DOMINIO, DOMINIO_IMG } from '../global';
import { getTipoLogado, getUsuario, getToken,setUsuarioPro, verificarLogado } from '../utils/verificaSessionStrg';
import {ModalLoadConst, ModalAlertas} from './ModaisLoad';
import ToTop from './ToTop';
import $ from 'jquery';
import iconServico from '../img/servico.png';
import iconLocal from '../img/local.png';
import iconAvaliacao from '../img/star.png';
import iconValor from '../img/money.png';

verificarLogado();

export class CapaPerfilPro extends Component{

    constructor(props){
        super(props);
        this.state = {
            texto1: this.props.texto1,
            texto2: this.props.texto2,
            texto3: this.props.texto3,
            texto4: this.props.texto4,
            foto: "",
            // imgPerfil: "",
            imgPerfil: getUsuario().foto !== null ? `${DOMINIO_IMG}${getUsuario().foto}` : "",
            upload: "",
            confirmUpload: false,
            erros: [],

            loading: false,
            showModalErro: false,
            erros: [],
            tipoAlerta: "",
            tituloAlerta: ""
        }
        this.uploadPhoto = this.uploadPhoto.bind(this);
        this.confirmUploadMode = this.confirmUploadMode.bind(this);
        this.modalLoad = this.modalLoad.bind(this);
        this.ModalAlertas = this.ModalAlertas.bind(this);
        this.mostrarAlerta = this.mostrarAlerta.bind(this);
        this.noConnection = this.noConnection.bind(this);
    }

    componentDidMount(){
        this.confirmUploadMode();
    }

    //MOSTRA ALERTA SE ÃO HOUVER CONEXAÃO COM O SERVIDOR
    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        this.mostrarAlerta("erroAlt", "ERRO");
    }
    //CONTROLA CAIXA DE LOAD
    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            // $("body").css("padding-right", "35px");
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
    }

    ModalAlertas = () =>{
        if(!this.state.showModalErro){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({showModalErro: !this.state.showModalErro});
    }

    mostrarAlerta(tipoAlerta, tituloAlerta){
        this.setState({tipoAlerta: tipoAlerta});
        this.setState({tituloAlerta: tituloAlerta});
        setTimeout(()=>{this.ModalAlertas()}, 200);
    }

    confirmUploadMode(){
        if(this.state.upload !== ""){
            $("#btn-confirm-upload").removeAttr("disabled");
            $("#btn-confirm-upload").removeClass("btn-upload-disabled");
            $("#btn-confirm-upload").addClass("caixa-confirm-file");
        }else{
            $("#btn-confirm-upload").attr("disabled", "disabled");
            $("#btn-confirm-upload").addClass("btn-upload-disabled");
            $("#btn-confirm-upload").removeClass("caixa-confirm-file");
        }
    }

    uploadPhoto(event){
        let alertas = [];
        event.preventDefault();
        const form = new FormData();
        form.append("img", this.state.upload);
        let idUsuario;
        if(getTipoLogado() === "cliente"){
            idUsuario = getUsuario().idCliente;
            form.append("idCliente", idUsuario);
        }else{
            idUsuario = getUsuario().idProfissional;
            form.append("idPro", idUsuario);
        }

        axios({
            method: "POST",
            url: `${DOMINIO}imagens/${getTipoLogado()}`,
            data: form,
            headers: {"token": getToken()},
            onUploadProgress:  progressEvent => {
                console.log("Em progresso: "+Math.round((progressEvent.loaded / progressEvent.total)*100)+"%");
            }
        })
        .then(response =>{
            this.setState({upload: ""});
            let profissional = response.data;
            console.log(profissional);

            console.log(-5);
            
            if(this.state.upload === ""){
                console.log(-4);
                setUsuarioPro(profissional);
                console.log(-3);
                this.confirmUploadMode();
                console.log(-2);
                this.setState({imgPerfil: `${DOMINIO_IMG}${profissional.foto}`});
                console.log(-1);
            }
            
            console.log(1);
            this.modalLoad();
            console.log(2);
            alertas.push("O upload da sua nova imagem de perfil realizado com sucesso!");
            console.log(3);
            this.setState({erros: alertas});
            console.log(4);
            this.mostrarAlerta("msgAlt", "Perfil Atualizado");
            console.log(5);
        })
        .catch(error=>{
            this.modalLoad();
            this.noConnection();
            this.setState({upload: ""});

            if(this.state.upload === ""){
                this.confirmUploadMode();
            }            
            console.log(error);
        })
        .onload = this.modalLoad();

        console.log("submit");
        console.log(form);
    }

    selectImgPerfil = event =>{
        const photo = event.target.files[0];

        const uploadedPhoto = {
            photo,
            name: photo.name,
            readableSize: photo.size,
            preview: URL.createObjectURL(photo),
        }

        this.setState({imgPerfil: uploadedPhoto.preview});
        this.setState({upload: photo});
        setTimeout(() => {
            console.log(this.state.upload);
            console.log(uploadedPhoto);
            this.confirmUploadMode();
        }, 500);
        console.log(uploadedPhoto.preview);

        setTimeout(() => {
            console.log("+++---------++");
            console.log(this.state.upload);
            console.log("+++---------++");            
        }, 1500);
    }

    limitChars(texts){
        let inputs = [];
        let output = [];

        inputs.push(texts.texto1);
        inputs.push(texts.texto2);
        inputs.push(texts.texto3);
        inputs.push(texts.texto4);
        inputs.push(texts.nome);
        
        for(let text of inputs){
            if(text.length > 60){
                output.push(text.substring(0, 57)+"...");
            }else{
                output.push(text);
            }
        }
        output.push(texts.foto);
        // console.log(output);
        return output;
    }
    
    render(){
        let props = this.limitChars(this.props);
        
        return(
            <Fragment>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <ModalAlertas tipoAlerta={this.state.tipoAlerta} titulo={this.state.tituloAlerta} erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <div className="capa-perfil-pro">
                </div>
                <div className="caixa-perfil">
                    <ToTop/>
                    <div className="avatar-auxiliar">
                        <div className="avatar" style={{backgroundImage: this.state.imgPerfil !== "" ? this.state.imgPerfil !== null ? `url(${this.state.imgPerfil})` : props[5] : props[5]}}></div>
                    </div>
                    <form method="POST" name ="frm_upload_perfil_pro" onSubmit={this.uploadPhoto}>
                        <div className="caixa-botoes-upload">
                            <button className="caixa-input-file" type="button">
                                {/* <input type="file"/> */}
                                <input onChange={this.selectImgPerfil} className="perfil-pro-upload" multiple="true" accept=".jpg, .png, .svg, .jpeg, .jfif" id="perfil-pro-upload" type="file"/>
                                <label tabIndex="0" htmlFor="perfil-pro-upload" className="perfil-pro-upload-label">ALTERAR FOTO</label>
                            </button>
                            <Botao 
                                classBotao="caixa-confirm-file"
                                typeBotao="submit"
                                idBotao="btn-confirm-upload"
                                nameBotao="btn_confirm_upload"
                                valueBotao="CONFIRMAR"
                            />
                            {/* <button className="" type="" id="">
                                CONFIRMAR
                            </button> */}
                        </div>
                    </form>
                    
                    <div className="center-info-usuario">

                        {/* <form method="POST" name="frm_upload_perfil_pro"> */}
                            {/* <input type="file" id="img-perfil-pro"></input> */}
                        {/* </form> */}

                        <div className="caixa-nome-usuario">
                            <h1 className="nome-usuario-capa flex-center">{props[4]}</h1>
                        </div>
                        <hr/>
                        <div className="caixa-informacoes-basicas">
                            <div className="caixa-info">
                                {/* <div className="icons-capa-perfil">
                                    <figure>
                                        <img src={iconServico} id="icon-capa-perfil" alt="Serviço" title="Serviço" />
                                    </figure>
                                </div> */}
                                <div className="caixa-info1">
                                    {props[0]}
                                </div>
{/* 
                                <div className="icons-capa-perfil">
                                    <figure>
                                        <img src={iconLocal} id="icon-capa-perfil" alt="Região" title="Região" />
                                    </figure>
                                </div> */}
                                <div className="caixa-info1">
                                    {props[1]}
                                </div>

                            </div>
                            <div className="caixa-info">
                                {/* <div className="icons-capa-perfil">
                                    <figure>
                                        <img src={iconAvaliacao} id="icon-capa-perfil" alt="Média de Avaliações" title="Média de Avaliações" />
                                    </figure>
                                </div> */}
                                <div className="caixa-info1">
                                    {props[2]}
                                </div>
{/* 
                                <div className="icons-capa-perfil">
                                    <figure>
                                        <img src={iconValor} id="icon-capa-perfil" alt="Valor por hora de Serviço" title="Valor por hora de Serviço" />
                                    </figure>
                                </div> */}
                                <div className="caixa-info1">
                                    {props[3]}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export default CapaPerfilPro;