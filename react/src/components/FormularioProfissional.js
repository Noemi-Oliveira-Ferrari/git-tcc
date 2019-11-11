import React,  {Component, Fragment} from "react";
import { DOMINIO } from '../global';
import {Inputs, Selects, InputNumber} from './FormElements';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';
import {ModalLoadConst, ModalAlertas} from './Modais';
import { validarConfirmacaoSenha, moveToError, generateHash, withError,
         withoutError, validarCnpj, validarCpfPro, validarEmail,
         validarSenha, validarString, validarVazios, retirarSimbolos,
         formataData, limpaValor, validarIdade} from '../js/validar';

export class DadosPessoaisPro extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "", cnpj: "", cpfCnpj: "",
            email: "", senha: "", confirmSenha: "",
            loading: false,
            showModalErro: false,
            erros: [],
            
            cep: "", logradouro: "", idCidade: "",
            bairro: "", cidade: "", uf: "", subcategoria: "",
            tipoPro: "rdo-pf", radioChecked: "rdo-pf",
            endereco: [], profissional: []
        }
        this.modalLoad = this.modalLoad.bind(this);
        this.ModalAlertas = this.ModalAlertas.bind(this);
        this.noConnection = this.noConnection.bind(this);
           
        this.setNome = this.setNome.bind(this);
        this.setCep = this.setCep.bind(this);
        this.setEmail = this.setEmail.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmSenha = this.setConfirmSenha.bind(this);
        this.setCpfCnpj = this.setCpfCnpj.bind(this);
        this.setTipoPfPj = this.setTipoPfPj.bind(this);
        this.setData = this.setData.bind(this);
        
        this.getCpf = this.getCpf.bind(this);
        this.getCnpj = this.getCnpj.bind(this);
        this.getEmail = this.getEmail.bind(this);
    }

    ModalAlertas = () =>{
        // if(this.state.showModalErro){
        //     $("body").css("overflow-y", "hidden");
        // }else{
        //     $("body").css("overflow-y", "auto");
        // }
        this.setState({showModalErro: !this.state.showModalErro});
    }
    
    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
    }
    
    componentDidMount(){
        let app = sessionStorage.getItem("app");
        
        if(app !== null && app !== ""){
            $(".container-senha").css("display", "none");
            if(app === "cliente"){
                console.log("cliente");
            }else if(app === "profissional"){
                console.log("profissional");
            }
            $("input, select, textarea").attr("disabled", "disabled");
            $("input, select, textarea").css("background-color", "#d5d5d5");
        }else{            
            $(".container-senha").css("display", "flex");
        }

        let profissional = JSON.parse(sessionStorage.getItem("profissional"));
        let endereco = JSON.parse(sessionStorage.getItem("endereco"));

        if(app === "profissional"){
            endereco = profissional.endereco;
        }

        if(profissional !== null){
            this.setState({profissional: profissional});
            
            this.setState({nome: profissional.nome});
            this.setState({dataNasc: formataData(profissional.dataNasc, "-", "/")});
            console.log("__________");
            console.log(profissional.cpf);
            if(profissional.cpf === "" || profissional.cpf === null){
                // $("#rdo-pj").prop("checked", true);
                this.setState({radioChecked: "rdo-pj"});
                this.setState({tipoPro: "rdo-pj"});
                this.setState({cpfCnpj: profissional.cnpj});
            }else{
                // $("#rdo-pf").prop("checked", true);
                this.setState({radioChecked: "rdo-pf"});
                this.setState({tipoPro: "rdo-pf"});
                this.setState({cpfCnpj: profissional.cpf || profissional.cnpj});
            }

            this.setState({email: profissional.email});
        }        
        if(endereco !== null){
            this.setState({endereco: endereco});
            this.setState({cep: endereco.cep});
            this.getEndereco(endereco.cep);
            this.setState({numero: endereco.numero});
            this.setState({idCidade: endereco.cidade.idCidade});
        }
    }    

    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        setTimeout(()=>{this.ModalAlertas();}, 500);
    }

    setCpfCnpj(event){
        this.setState({cpfCnpj: event.target.value});
        let cpfCnpj = event.target.value;
        if(this.state.radioChecked === "rdo-pf"){
            validarCpfPro(cpfCnpj);
            if(!cpfCnpj.includes("_") && cpfCnpj.length === 14){
                this.getCpf(retirarSimbolos(cpfCnpj));
            }
        }else{
            validarCnpj(cpfCnpj);
            if(!cpfCnpj.includes("_") && cpfCnpj.length === 18){
                this.getCnpj(retirarSimbolos(cpfCnpj));
            }
        }
    }
    
    getCpf(cpf){
        let erros = [];
        console.log(cpf);
        axios({
            method:"GET",
            url: `${DOMINIO}profissionais/cpf/${cpf}`,
            timeout: 30000
        })
        .then((response)=>{
            let jsonPro = response.data;
            console.log(jsonPro);
            if(jsonPro !== null && jsonPro !== ''){
                withError($("#txt-cpfCnpj"));
                erros.push(`CPF ${cpf} ja cadastrado`);
                this.setState({erros: erros});
                setTimeout(()=>{this.ModalAlertas();}, 500);
                this.setState({cpfCnpj: ""});
            }
            setTimeout(()=>{this.modalLoad();}, 200);
        })
        .catch((error)=>{
            this.noConnection();
            this.modalLoad();
        })
        .onload = this.modalLoad();
    }
    // {"message":"Network Error","name":"Error","stack":"Error: Network Error\n    at createError (http://localhost:3000/static/js/bundle.js:68072:16)\n    at XMLHttpRequest.handleError (http://localhost:3000/static/js/bundle.js:67920:15)","config":{"url":"http://localhost:8080/profissionais/cpf/48683336824","headers":{"Accept":"application/json, text/plain, */*"},"transformRequest":[null],"transformResponse":[null],"timeout":30000,"xsrfCookieName":"XSRF-TOKEN","xsrfHeaderName":"X-XSRF-TOKEN","maxContentLength":-1,"method":"get"}}
    
    getCnpj(cnpj){
        let erros = [];

        axios({
            method: "GET",
            url: `${DOMINIO}profissionais/cnpj/${cnpj}`,
            timeout: 30000
        })
        .then((response)=>{
            let jsonPro = response.data;
            console.log(jsonPro);
            if(jsonPro !== null && jsonPro !== ''){
                withError($("#txt-cpfCnpj"));
                erros.push(`CNPJ ${cnpj} ja cadastrada`);
                this.setState({erros: erros});
                setTimeout(()=>{this.ModalAlertas();}, 500);
                this.setState({cpfCnpj: ""});
            }
            setTimeout(()=>{this.modalLoad()}, 500);
        })
        .catch((error)=>{
            this.noConnection();
            this.modalLoad();
        })
        .onload = this.modalLoad();
    }



    setNome(event){
        this.setState({nome: event.target.value});
        validarString(event.target);
    }

    setData(event){
        this.setState({dataNasc: event.target.value});
        let data = event.target.value;

        if(!data.includes("_") && validarIdade(event.target)){
            withoutError($("#txt-dataNasc"));
        }else{
            withError($("#txt-dataNasc"));
        }
    }

    setEmail(event){
        this.setState({email: event.target.value});
        // this.getEmail(event.target.value);
        validarEmail(event.target);
    }

    getEmail(event){
        this.setState({email: event.target.value});
        let email = event.target.value;
        console.log(email);
        if(email.length > 5){
            let erros = []; 

            axios({
                method: "GET",
                url: `${DOMINIO}profissionais/email/${email}`,
                timeout: 30000
            })
            .then((response)=>{
                let jsonPro = response.data;
                console.log(jsonPro);
                if(jsonPro !== null && jsonPro !== ''){
                    withError($("#txt-email"));
                    erros.push(`E-mail ${email} ja cadastrado`);
                    this.setState({erros: erros});
                    setTimeout(()=>{this.ModalAlertas();}, 500);
                    this.setState({email: ""});
                    setTimeout(() => {
                        $("#txt-email").focus();
                    }, 200);
                    
                }
                setTimeout(()=>{this.modalLoad()}, 500);
            })
            .catch((error)=>{
                this.noConnection();
                this.modalLoad();
            })
            .onload = this.modalLoad();
        }   
    }

    setCep(event){
        this.setState({cep: event.target.value});
        let cepSize = event.target.value.length;
        if (cepSize > 8) {
            this.getEndereco(event.target.value);
        }
    }
    getEndereco = (cep) =>{
        let erros = [];
        // axios.get(`${DOMINIO}enderecos/cep/${cep}`)
        axios({
            method: "GET",
            url: `https://viacep.com.br/ws/${cep}/json/`,
            type: "application/json",
            timeout: 30000,
        })
        .then((response)=>{
            let jsonEndereco = response.data;
            let cepError = jsonEndereco.erro;
            if(jsonEndereco === null || jsonEndereco === "" || cepError === true){
                this.setState({logradouro: ""});
                this.setState({bairro: ""});
                this.setState({cidade: ""});
                this.setState({uf: ""});
                this.setState({idCidade: ""});
                
                withError($('#txt-cep'));
                withError($('#txt-logradouro'));
                withError($('#txt-cidade'));
                withError($('#txt-bairro'));
                withError($('#txt-uf'));
                this.modalLoad();
                erros.push(`O CEP ${cep} é invalido`);
                this.setState({erros: erros});
                setTimeout(()=>{this.ModalAlertas();}, 500);
            }else if(cepError !== true){
                this.setState({logradouro: jsonEndereco.logradouro});
                this.setState({bairro: jsonEndereco.bairro});
                this.setState({cidade: jsonEndereco.localidade});
                this.setState({uf: jsonEndereco.uf});
                this.setState({idCidade: jsonEndereco.ibge});

                withoutError($('#txt-cep'));
                withoutError($('#txt-logradouro'));
                withoutError($('#txt-cidade'));
                withoutError($('#txt-bairro'));
                withoutError($('#txt-uf'));
                setTimeout(()=>{this.modalLoad();}, 200);
            }
        })
        .catch((error)=>{
            this.noConnection();

            this.modalLoad();
        })
        .onload = this.modalLoad();
    }
    
    setSenha(event){
        this.setState({senha: event.target.value});
        let senha = event.target.value;
        validarSenha(senha);
    }

    setConfirmSenha(event){
        this.setState({confirmSenha: event.target.value});
        validarConfirmacaoSenha($('#txt-senha').get(0), event.target);
    }
    
    setTipoPfPj(event){
        this.setState({radioChecked: event.target.value});

        this.setState({tipoPro: event.target.value})
        setTimeout(()=>{
            console.log(`tipoPro ${this.state.tipoPro}`);
        }, 1000);    
    }

    render(){
        return(
            <Fragment>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <ModalAlertas tipoAlerta="erroAlt" titulo="ERRO NO CADASTRO" erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <div className="flex-center">
                    <div className="card-formulario-pessoal">
                        <div className="caixa-title-card">
                            <h3 className="title-card-pro">Dados Pessoais</h3>
                        </div>
                        <div className="title-card-pjPf">
                            <Inputs
                                id="rdo-pf"
                                type="radio"
                                name="rdos_pfpj"
                                label="Pessoa Física:"
                                classDivInput="caixa-rdo-pf"
                                forInput="rdo-pf"
                                onChange={this.setTipoPfPj}
                                // onChange={(e) => this.setState({ radioChecked: e.target.value })}
                                radioChecked={this.state.radioChecked === 'rdo-pf'}
                                valueInput="rdo-pf"
                            />
                            <Inputs
                                id="rdo-pj"
                                type="radio"
                                name="rdos_pfpj"
                                label="Pessoa Jurídica:"
                                classDivInput="caixa-rdo-pj"
                                forInput="rdo-pj"
                                onChange={this.setTipoPfPj}
                                // onChange={(e) => this.setState({ radioChecked: e.target.value })}
                                radioChecked={this.state.radioChecked === 'rdo-pj'}
                                valueInput="rdo-pj"
                            />
                        </div>
                        
                        <div className="float campos-dados">
                            <div className="flex-center container-nome-dataNasc">

                                <Inputs
                                    label="Nome:"
                                    id="txt-nome"
                                    name="txt_nome"
                                    maxLength="100"
                                    type="text"
                                    classDivInput="caixa-nome"
                                    classInput="form-control form-input"
                                    onChange={this.setNome}
                                    valueInput={this.state.nome || ""}
                                />

                                <InputNumber
                                    classDivInput="caixa-dataNasc"
                                    label="Data de Nascimento:"
                                    id="txt-dataNasc"
                                    type="text"
                                    name="txt_data_nasc"
                                    mascara="##/##/####"
                                    classInput="form-control form-input"
                                    onChange={this.setData}
                                    valueInput={this.state.dataNasc || ""}
                                />
                            
                            </div>
                            <div className="flex-center container-cpfCnpj-email">

                                <InputNumber
                                    classDivInput="caixa-cpfCnpj"
                                    label={this.state.tipoPro === "rdo-pf" ? "CPF" : "CNPJ"}
                                    id="txt-cpfCnpj"
                                    type="text"
                                    name="txt_cpfCnpj"
                                    classInput="form-control form-input"
                                    onChange={this.setCpfCnpj}
                                    mascara={this.state.tipoPro === "rdo-pf" ? "###.###.###-##" : "##.###.###/####-##"}
                                    valueInput={this.state.cpfCnpj || ""}
                                />

                                <Inputs
                                    classDivInput="caixa-email"
                                    label="E-mail:"
                                    maxLength="150"
                                    id="txt-email"
                                    type="email"
                                    name="txt_email"
                                    onChange={this.setEmail}
                                    onBlur={this.getEmail}
                                    classInput="form-control form-input"
                                    valueInput={this.state.email || ""}
                                />

                                
                            </div>
                            <div className="flex-center container-senha">

                                <Inputs
                                    classDivInput="caixa-senha"
                                    label="Senha:"
                                    maxLength="130"
                                    id="txt-senha"
                                    type="password"
                                    name="txt_senha"
                                    onChange={this.setSenha}
                                    classInput="form-control form-input"
                                />

                                <Inputs
                                    classDivInput="caixa-confirmar-senha"
                                    label="Confirmar Senha:"
                                    maxLength="130"
                                    id="txt-confirmar-senha"
                                    type="password"
                                    name="txt_confirmar_senha"
                                    onChange={this.setConfirmSenha}                           
                                    classInput="form-control form-input"
                                />

                            </div>

                            <div className="flex-center container-cep-logradouro">

                                <Inputs
                                    classDivInput="caixa-cep"
                                    label="CEP:"
                                    id="txt-cep"
                                    type="text"
                                    name="txt_cep"
                                    onChange={this.setCep}
                                    classInput="form-control form-input"
                                    mascara="99999-999"
                                    valueInput={this.state.cep || ""}
                                />

                                <Inputs
                                    classDivInput="caixa-logradouro"
                                    label="Logradouro:"
                                    maxLength="120"
                                    id="txt-logradouro"
                                    type="text"
                                    name="txt_logradouro"
                                    valueInput={this.state.logradouro || ""}
                                    readOnly
                                    classInput="form-control form-input"
                                />
                                
                            </div>
                            <div className="flex-center container-bairro-cidade-uf">

                                <Inputs
                                    classDivInput="caixa-bairro"
                                    label="Bairro:"
                                    maxLength="120"
                                    id="txt-bairro"
                                    type="text"
                                    name="txt_bairro"
                                    valueInput={this.state.bairro || ""}
                                    disabled
                                    
                                    classInput="form-control form-input"
                                />

                                <Inputs
                                    classDivInput="caixa-cidade"
                                    label="Cidade:"
                                    maxLength="120"
                                    id="txt-cidade"
                                    type="text"
                                    name="txt_cidade"
                                    data={this.state.idCidade}
                                    valueInput={this.state.cidade || ""}
                                    readOnly
                                    
                                    classInput="form-control form-input"
                                />

                                <Inputs
                                    classDivInput="caixa-uf"
                                    label="UF:"
                                    maxLength="2"
                                    id="txt-uf"
                                    type="text"
                                    name="txt_uf"
                                    valueInput={this.state.uf || ""}
                                    readOnly
                                    
                                    classInput="form-control form-input"
                                />
                                
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}

export class DadosProfissional extends Component{
    
    constructor(){
        super();        
        // this.popularCategorias = this.popularCategorias.bind(this);
        this.state = {
            categorias: [],
            idCategoria: "",
            subcategorias: [],
            idSubcategoria: "",
            valorHora: "",
            qualificacoes: "",
            loading: false,
            showModalErro: false,
            error: []
        }
        this.modalLoad = this.modalLoad.bind(this);
        this.noConnection = this.noConnection.bind(this);
        this.ModalAlertas = this.ModalAlertas.bind(this);

        this.getCategorias = this.getCategorias.bind(this);
        this.getSubcategorias = this.getSubcategorias.bind(this);
        this.setValorHora = this.setValorHora.bind(this);
        // this.setSubcategoria = this.setSubcategoria.bind(this);
    }

    ModalAlertas = () =>{
        this.setState({showModalErro: !this.state.showModalErro});
    }
    
    modalLoad = () =>{
        if(!this.state.loading){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({loading: !this.state.loading});
    }
    
    noConnection(){
        let erros = [`Não foi possível obter resposta do servidor. Tente novamente mais tarde.`];
        this.setState({erros: erros});
        setTimeout(()=>{this.ModalAlertas();}, 500);
    }

    setValorHora(event){
        this.setState({valorHora: event.target.value});
    }

    componentDidMount(){
        let profissional = JSON.parse(sessionStorage.getItem("profissional"));
        let categoria = JSON.parse(sessionStorage.getItem("categoria"));
        let subcategoria = JSON.parse(sessionStorage.getItem("subcategoria"));
        let app = sessionStorage.getItem("app");
        
        
        if(app === "profissional"){
            categoria = profissional.subcategoria.categoria.idCategoria;
            subcategoria = profissional.subcategoria.idSubcategoria;
        }

        console.log(categoria);
        console.log(subcategoria);

        if((categoria !== null && subcategoria !== null)){
            // console.log("/////////////////////");
            // console.log(this.getSubcategorias)
            this.getCategorias();
            this.setState({idCategoria: categoria});
            this.setState({idSubcategoria: subcategoria});
            this.setState({valorHora: profissional.valorHora});
            this.setState({qualificacoes: profissional.resumoQualificacoes});
            this.getSubcategorias(categoria);
        }else{
            this.getCategorias();
            this.getSubcategorias(1);

        }
    }
        
        
        
    getCategorias(){
        let load = false;
        // axios.get(`${DOMINIO}categorias`)
        axios({
            method: "GET",
            url: `${DOMINIO}categorias`,
            type: "application/json",
            timeout: 30000,  
        })
        .then((response)=>{
            let jsonCategorias = response.data;
            this.setState({categorias: jsonCategorias});
            if(load){
                this.modalLoad();
                load = true;
            }
        })
        .catch((error)=>{
            console.log("++++++++++++");
            this.setState({loading: !this.state.loading});
            this.modalLoad();
            this.setState({showModalErro: this.state.showModalErro});
            this.noConnection();
        })
        .onload =  this.modalLoad();
    }

    getSubcategorias(idCategoria){

        if(idCategoria === null || idCategoria === ""){
            idCategoria = 1;
        }

        // axios.get(`${DOMINIO}enderecos/cep/${cep}`)
        axios.get(`${DOMINIO}subcategorias/categoria/${idCategoria}`)
        .then((response)=>{
            let jsonSubcategorias = response.data;
            this.setState({subcategorias: jsonSubcategorias});
            setTimeout(()=>{this.modalLoad()}, 700);
        })
        .catch((error)=>{
            console.error(error);
            this.modalLoad();
            this.noConnection();
        })
        .onload = this.modalLoad();
    }
    
    render(){
        return(
            <Fragment>
                <ModalAlertas tipoAlerta="erroAlt" titulo="ERRO NO CADASTRO" erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <ModalLoadConst abrir={this.state.loading} onClose={this.modalLoad}/>
                <div className="flex-center">
                    <div className="card-formulario-servico">
                        <h3 className="title-card">Dados de Serviço</h3>
                        <div className="flex-center campos-servicos">
                            <div className="container-servico-pro">
                                <div className="flex-center container-categoria">
                                    <Selects
                                        labelSelect="Selecione o Tipo de Serviço:"
                                        idSelect="slt-categoria"
                                        nameSelect="slt_categoria"
                                        classDivSelect="caixa-categoria"
                                        onChangeSelect={()=>(this.getSubcategorias($("#slt-categoria").find(":selected").val()))}
                                        optionsSelect={this.state.categorias.map(categoria=>(
                                            <option key={categoria.idCategoria} value={categoria.idCategoria}
                                                selected={this.state.idCategoria === categoria.idCategoria ? "selected" : ""}
                                            >
                                                {categoria.categoria}
                                            </option>
                                            ))}
                                            firstOption="Tipos de serviços"
                                        />
                                </div>
                                
                                <div className="flex-center container-subcat">
                                    <Selects
                                        labelSelect="Selecione um serviço"
                                        idSelect="slt-subcat"
                                        nameSelect="slt_subcategoria"
                                        classDivSelect="caixa-subcat"
                                        // onChangeSelect={()=>(this.setSubcategoria($("#slt-subcat").find(":selected").val()))}
                                        optionsSelect={this.state.subcategorias.map(subcategoria=>(
                                                <option key={subcategoria.idSubcategoria} value={subcategoria.idSubcategoria}
                                                    selected={this.state.idSubcategoria === subcategoria.idSubcategoria ? "selected" : ""} 
                                                >
                                                    {subcategoria.subcategoria}
                                                </option>
                                            ))}
                                            firstOption="Serviços"
                                        />
                                </div>
                                
                                <div className="flex-center container-valor-hora">
                                    <InputNumber
                                        label="Valor/Hora:"
                                        id="txt-valor-hora"
                                        classInput="form-control form-input"
                                        name="txt_valor_hora"
                                        type="InputNumber"
                                        classDivInput="caixa-valor-hora"
                                        separadorMilhar="."
                                        separadorDecimal=","
                                        permitirNegativo="false"
                                        prefixo="R$"
                                        qtdDecimal="2"
                                        onChange={this.setValorHora}
                                        valueInput={this.state.valorHora || ""}
                                    />
                                </div>
                            </div>
                            <div className="container-servico-pro">
                                <div className="container-qualificacoes">
                                    <div className="float caixa-qualificacoes">
                                        <label className="form-label">Resumo de Qualificações:</label>
                                        <textarea 
                                            id="txt-qualificacoes" 
                                            className="txt-qualificacoes form-control form-input-pro"
                                            defaultValue={this.state.qualificacoes || ""}>

                                        </textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Fragment>
        );
    }
}


export default class FormularioProfissional extends Component{
    
    constructor(){
        super();
        this.state = {
            erros: [],
            showModalErro: false
        }
        this.ModalAlertas = this.ModalAlertas.bind(this);

        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);  
    }

    ModalAlertas = () =>{
        if(!this.state.showModalErro){
            $("body").css("overflow-y", "hidden");
        }else{
            $("body").css("overflow-y", "auto");
        }
        this.setState({showModalErro: !this.state.showModalErro});
    }

    componentDidUpdate(){
    }

    validarCampos(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email], select");
        let semErro = true;
        let erros = [];

        if(!validarVazios(campos)){
            semErro = false;
            erros.push("Há campos não preenchidos!\n");
            console.log("validarVazios "+semErro);
        }

        if(!validarString($('#txt-nome').get(0))){
            semErro = false;
            erros.push("O Nome digitado é inválido!\n");
            console.log("validarString nome "+semErro);
        }

        if(!validarIdade($('#txt-dataNasc').get(0))){
            semErro = false;
            erros.push("Para ser cadastrar é necessário ter no mínimo 18 anos!\n");
            console.log("validarIdade "+semErro);
        }

        if($('.caixa-cpfCnpj').text() === "CPF"){
            if(!validarCpfPro($('#txt-cpfCnpj').val())){
                semErro = false;
            erros.push("CPF Inválido\n");
                console.log("validarCpfPro "+semErro);
            }
        }else{
            if(!validarCnpj($('#txt-cpfCnpj').val())){
                semErro = false;
                erros.push("CNPJ Inválido\n");
                console.log("validarCnpj "+semErro);
            } 
        }

        if(!validarEmail($('#txt-email').get(0))){
            semErro = false;
            erros.push("E-mail digitado não é válido\n");
            console.log("validarEmail "+semErro);
        }

        if(!validarSenha($('#txt-senha').val())){
            semErro = false;
            erros.push("A senha deve ter letras maiúsculas e minúsculas, números, símbolos(@#$...), ter no mínimo 8 caractéres e não pode conter espaços");
            console.log("validarSenha "+semErro);
        }

        if(!validarConfirmacaoSenha($('#txt-senha').get(0), $('#txt-confirmar-senha').get(0))){
            semErro = false;
            erros.push("As senhas não correspondem!\n");
            console.log("validarConfirmacaoSenha "+semErro);
        }
        
        this.setState({erros: erros});
        erros = [];
        return semErro;
    }

    realizarCadastro(event){
        event.preventDefault();
        // console.clear();
        // console.log("Enviando dados ao banco...");
        let cpfCnpj = $("#txt-cpfCnpj").val().replace(/[.-]/g, "");
        let cpf;
        let cnpj;
        if(cpfCnpj.length <= 14){
            cpf = retirarSimbolos(cpfCnpj);
            cnpj = null;
        }else{
            cnpj = retirarSimbolos(cpfCnpj);
            cpf = null;
        }

        
        // console.log("validarCampos "+this.validarCampos());
        if(this.validarCampos() && $("#chk-termos").is(":checked")){
            // console.log("validarCampos TRUE"+this.validarCampos());
            
            let endereco = {
                cep: retirarSimbolos($("#txt-cep").val()),
                logradouro: $("#txt-logradouro").val(),
                bairro: $("#txt-bairro").val(),
                numero: null,
                cidade: {
                    idCidade: $("#txt-cidade").attr("data-idCidade")
                }
            };
            let profissional = {
                nome: $("#txt-nome").val(),
                dataNasc: formataData($("#txt-dataNasc").val(), "/", "-"),
                cpf: cpf,
                cnpj: cnpj,
                email: $("#txt-email").val(),
                senha: generateHash($("#txt-senha").val()),
                tipoUsuario: {
                    idTipoUsuario: 1
                },
                endereco: {
                    idEndereco: null
                },
                subcategoria: {
                    idSubcategoria: $("#slt-subcat").val(),
                },
                valorHora: limpaValor($("#txt-valor-hora").val()),
                resumoQualificacoes: $("#txt-qualificacoes").val()
            };
            sessionStorage.setItem("endereco", JSON.stringify(endereco));
            sessionStorage.setItem("profissional", JSON.stringify(profissional));
            sessionStorage.setItem("categoria", $("#slt-categoria").find(":selected").val());    
            sessionStorage.setItem("subcategoria", $("#slt-subcat").find(":selected").val());
            browserHistory.push("/cadastro/confirmacao");
        }else{
            setTimeout(() => {
                this.ModalAlertas();
            }, 200);
            moveToError();
        }
    }

    render(){
        return(
            <Fragment>
                <ModalAlertas tipoAlerta="erroAlt" titulo="ERRO NO CADASTRO" erros={this.state.erros} abrir={this.state.showModalErro} onClose={this.ModalAlertas}/>
                <form className="form-pro" name="form_profissional" method="GET" onSubmit={this.realizarCadastro}>
                    <DadosPessoaisPro/>
                    <DadosProfissional/>
                    <TermosDeUso link="/cadastro/confirmacao"/>
                </form>
            </Fragment>
        )
    }
    
}