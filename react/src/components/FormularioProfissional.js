import React,  {Component} from "react";
import {Inputs, Selects} from './FormElements';
import TermosDeUso from '../components/TermosDeUso';
import $ from 'jquery';
import axios from 'axios';
import {browserHistory} from 'react-router';
import { validarSenha, ancora} from '../js/validar';
import sha512 from 'js-sha512';


class DadosPessoaisPro extends Component{

    constructor(){
        super();
        this.state = {
            nome: "", dataNasc: "", cpf: "", cnpj: "", cpfCnpj: "",
            email: "", senha: "", confirmSenha: "",
            
            cep: "", logradouro: "", idCidade: "",
            bairro: "", cidade: "", uf: "", subcategoria: "",
            enderecoPronto: false,
            tipoPro: "rdo-pf", radioChecked: "rdo-pf"
        }
        this.setCep = this.setCep.bind(this);
        this.setSenha = this.setSenha.bind(this);
        this.setConfirmSenha = this.setConfirmSenha.bind(this);
        // this.validarSenha = this.validarSenha.bind(this);
        this.setCpfCnpj = this.setCpfCnpj.bind(this);
        this.setTipoPfPj = this.setTipoPfPj.bind(this);
    }
    componentDidMount(){
        console.clear();
    }    

    setCpfCnpj(event){
        this.setState({cpfCnpj: event.target.value});
        let cpfCnpj = event.target.value;
        if(cpfCnpj.length <= 14){
            console.log(`cpf -> ${cpfCnpj} ${cpfCnpj.length}`)
        }else{
            console.log(`cnpj -> ${cpfCnpj} ${cpfCnpj.length}`)
        }
    }
    setCep(event){
        this.setState({cep: event.target.value});
        let cepSize = event.target.value.length;
        console.log(sha512("123"));
        if (cepSize > 8) {
            this.getEndereco(event.target.value);
        }
    }
    getEndereco = (cep) =>{
        // axios.get(`http://3.220.68.195:8080/enderecos/cep/${cep}`)
        axios.get(`http://localhost:8080/enderecos/cep/${cep}`)
        .then((response)=>{
            let jsonEndereco = response.data;
            console.clear();
            console.log(response);
            if(jsonEndereco.cep != null){
                this.setState({logradouro: jsonEndereco.logradouro});
                this.setState({bairro: jsonEndereco.bairro});
                this.setState({cidade: jsonEndereco.cidade.cidade});
                this.setState({uf: jsonEndereco.cidade.microrregiao.uf.uf});
                this.setState({idCidade: jsonEndereco.cidade.idCidade});

                $('#txt-cep').removeClass("erro");
                $('#txt-logradouro').removeClass("erro");
                $('#txt-cidade').removeClass("erro");
                $('#txt-bairro').removeClass("erro");
                $('#txt-uf').removeClass("erro");
            }
        })
        .catch((error)=>{
            this.setState({logradouro: "CEP INVÁLIDO"});
            this.setState({bairro: ""});
            this.setState({cidade: ""});
            this.setState({uf: ""});
            this.setState({idCidade: ""});

            $('#txt-cep').addClass("erro");
            $('#txt-logradouro').addClass("erro");
            $('#txt-cidade').addClass("erro");
            $('#txt-bairro').addClass("erro");
            $('#txt-uf').addClass("erro");
        })
        .onload = console.log("loading");
    }

    setSenha(event){
        this.setState({senha: event.target.value});
        let senha = event.target.value;
    }

    setConfirmSenha(event){
        this.setState({confirmSenha: event.target.value});
        let confirmSenha = event.target.value;
        validarSenha($('#txt-senha').get(0), event.target);
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
            <div className="flex-center">
                <div className="card-formulario-pessoal">
                    <div className="caixa-title-card">
                        <div className="title-card-pro">Dados Pessoais</div>
                    </div>
                    <div className="title-card-pjPf">
                        <Inputs
                            id="rdo-pf"
                            type="radio"
                            name="rdos_pfpj"
                            label="Pessoa Física:"
                            classDivInputPro="caixa-rdo-pf"
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
                            classDivInputPro="caixa-rdo-pj"
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
                                classDivInputPro="caixa-nome"
                                classInput="form-control form-input"
                            />

                            <Inputs
                                classDivInputPro="caixa-dataNasc"
                                label="Data de Nascimento:"
                                id="txt-dataNasc"
                                type="text"
                                name="txt_data_nasc"
                                mascara="99/99/9999"
                                classInput="form-control form-input"
                            />
                        
                        </div>
                        <div className="flex-center container-cpfCnpj-email">

                            <Inputs
                                classDivInputPro="caixa-cpfCnpj"
                                label={this.state.tipoPro === "rdo-pf" ? "CPF" : "CNPJ"}
                                id="txt-cpfCnpj"
                                type="text"
                                name="txt_cpfCnpj"
                                classInput="form-control form-input"
                                onChange={this.setCpfCnpj}
                                mascara={this.state.tipoPro === "rdo-pf" ? "999.999.999-99" : "99.999.999./9999-99"}
                            />

                            <Inputs
                                classDivInputPro="caixa-email"
                                label="E-mail:"
                                maxLength="150"
                                id="txt-email"
                                type="email"
                                name="txt_email"
                                
                                classInput="form-control form-input"
                            />

                            
                        </div>
                        <div className="flex-center container-senha">

                            <Inputs
                                classDivInputPro="caixa-senha"
                                label="Senha:"
                                maxLength="130"
                                id="txt-senha"
                                type="password"
                                name="txt_senha"
                                onChange={this.setSenha}
                                classInput="form-control form-input"
                            />

                            <Inputs
                                classDivInputPro="caixa-confirmar-senha"
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
                                classDivInputPro="caixa-cep"
                                label="CEP:"
                                id="txt-cep"
                                type="text"
                                name="txt_cep"
                                onChange={this.setCep}
                                classInput="form-control form-input"
                                mascara="99999-999"
                                valueInput={this.state.cep || ""}
                                disabled
                            />

                            <Inputs
                                classDivInputPro="caixa-logradouro"
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
                                classDivInputPro="caixa-bairro"
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
                                classDivInputPro="caixa-cidade"
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
                                classDivInputPro="caixa-uf"
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

        );
    }
}

class DadosProfissional extends Component{
    
    constructor(){
        super();        
        // this.popularCategorias = this.popularCategorias.bind(this);
        this.state = {
           categorias: [],
           subcategorias: []        
        }
        this.getCategorias = this.getCategorias.bind(this);
        this.getSubcategorias = this.getSubcategorias.bind(this);
    }

    componentDidMount(){
        this.getCategorias(this.getSubcategorias());
    }

    getCategorias(){
        // axios.get(`http://3.220.68.195:8080/enderecos/cep/${cep}`)
        axios.get(`http://localhost:8080/categorias`)
        .then((response)=>{
            let jsonCategorias = response.data;
            this.setState({categorias: jsonCategorias});
            this.getSubcategorias(jsonCategorias[0].idCategoria);
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload =  console.log("CARREGANDO CATS...")
    }

    getSubcategorias(idCategoria){

        if(idCategoria === null || idCategoria === ""){
            idCategoria = 1;
        }

        // axios.get(`http://3.220.68.195:8080/enderecos/cep/${cep}`)
        axios.get(`http://localhost:8080/subcategorias/categoria/${idCategoria}`)
        .then((response)=>{
            let jsonSubcategorias = response.data;
            this.setState({subcategorias: jsonSubcategorias});
        })
        .catch((error)=>{
            console.error(error);
        })
        .onload = console.log("CARREGANDO SUBS...")
    }

    
    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-servico">
                    <h3 className="title-card">Dados Profissionais</h3>
                    <div className="flex-center campos-servicos">
                        <div className="container-servico-pro">
                            <div className="flex-center container-categoria">
                                <Selects
                                    labelSelect="Tipos de Serviços:"
                                    idSelect="slt-categoria"
                                    nameSelect="slt_categoria"
                                    classDivSelect="caixa-categoria"
                                    onChangeSelect={()=>(this.getSubcategorias($("#slt-categoria").find(":selected").val()))}
                                    optionsSelect={this.state.categorias.map(categoria=>(
                                                <option key={categoria.idCategoria} value={categoria.idCategoria}>
                                                    {categoria.categoria}
                                                </option>
                                            ))}
                                    firstOption="Selecione um Tipo de serviço"
                                />
                            </div>
                            
                            <div className="flex-center container-subcat">
                                <Selects
                                    labelSelect="Serviços:"
                                    idSelect="slt-subcat"
                                    nameSelect="slt_subcategoria"
                                    classDivSelect="caixa-subcat"
                                    optionsSelect={this.state.subcategorias.map(subcategoria=>(
                                                <option key={subcategoria.idSubcategoria} value={subcategoria.idSubategoria}>
                                                    {subcategoria.subcategoria}
                                                </option>
                                            ))}
                                    firstOption="Selecione um serviço"
                                />
                            </div>
                            
                            <div className="flex-center container-valor-hora">
                                <Inputs
                                    label="Valor/Hora:"
                                    id="txt-valor-hora"
                                    classInput="form-control form-input"
                                    name="txt_valor_hora"
                                    type="text"
                                    classDivInputPro="caixa-valor-hora"
                                />
                            </div>
                        </div>
                        <div className="container-servico-pro">
                            <div className="container-qualificacoes">
                                <div className="float caixa-qualificacoes">
                                    <label className="form-label">Resumo de Qualificações:</label>
                                    <textarea id="txt-qualificacoes" className="txt-qualificacoes form-control form-input-pro"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default class FormularioProfissional extends Component{
    
    constructor(){
        super();
        this.realizarCadastro = this.realizarCadastro.bind(this);   
        this.validarCampos = this.validarCampos.bind(this);  
    }

    validarCampos(){
        
        let campos = document.querySelectorAll("input[type=password], input[type=text], input[type=email], select, textarea");
        console.log(campos);
        let notError = false;
        // console.log($("#txt-confirmar-senha").val());
        
        campos.forEach(campo =>{
            if(campo.value === ""){
                $(campo).addClass("erro");
                console.log($(campo).attr("id").replace(/(txt)\-/g, ""));
                notError = false;
            }else{
                $(campo).removeClass("erro");
                notError = true;
            }
        });

        if($('#txt-senha').val() === $('#txt-confirmar-senha').val() &&
        ($('#txt-senha').val() !== "" || $('#txt-confirmar-senha').val() !== "")){
            $('#txt-confirmar-senha').html('match');
            $('#txt-confirmar-senha').removeClass("erro");
            $('#txt-senha').removeClass("erro");
            notError = true;
        }else if($('#txt-senha').val() === "" || $('#txt-confirmar-senha').val() === ""){
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            notError = false;
        }else{
            $('#txt-confirmar-senha').html('mismatch');
            $('#txt-confirmar-senha').addClass("erro");
            $('#txt-senha').addClass("erro");
            notError = false;
        }

        return notError;
    }

    realizarCadastro(event){
        event.preventDefault();
        console.clear();
        console.log("Enviando dados ao banco...");
        let cpfCnpj = $("#txt-cpfCnpj").val().replace(/[.-]/g, "");
        let cpf;
        let cnpj;
        if(cpfCnpj.length <= 14){
            cpf = cpfCnpj;
            cnpj = null;
        }else{
            cnpj = cpfCnpj;
            cpf = null;
        }        
        
        console.log("validarCampos"+this.validarCampos());
        if(this.validarCampos() && $("#chk-termos").is(":checked")){
            console.log("validarCampos TRUE"+this.validarCampos());
            
            let endereco = {
                cep: $("#txt-cep").val(),
                logradouro: $("#txt-logradouro").val(),
                bairro: $("#txt-bairro").val(),
                cidade: {
                    idCidade: $("#txt-cidade").attr("data-idCidade")
                }
            };
            let profissional = {
                nome: $("#txt-nome").val(),
                dataNasc: $("#txt-dataNasc").val(),
                cpf: cpf,
                cnpj: cnpj,
                email: $("#txt-cep").val(),
                senha: $("#txt-cep").val(),
                endereco: {
                    idEndereco: null
                },
                subcategoria: {
                    idSubcategoria: $("#slt-subcat").val(),
                },
                valorHora: $("#txt-valor-hora").val(),
                resumoQualificacoes: $("#txt-qualificacoes").val()
            };
            sessionStorage.setItem("endereco", JSON.stringify(endereco));
            sessionStorage.setItem("profissional", JSON.stringify(profissional));
            browserHistory.push("/profissional/cadastro/confirmacao");
        }else{
            console.log($("#chk-termos").is(":checked"));
            ancora();
        }
    }

    render(){
        return(
            <form className="form-pro" name="form_profissional" method="GET" onSubmit={this.realizarCadastro}>
                <DadosPessoaisPro/>
                <DadosProfissional/>
                <TermosDeUso link="/profissional/cadastro/confirmacao"/>
            </form>
        )
    }
    
}