package br.senai.sp.daumhelp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profissional implements Serializable {

    @JsonAlias("idProfissional")
    private int idProfissional;

    @JsonAlias("nome")
    private String nome;

    @JsonAlias("cpf")
    private String cpf;

    @JsonAlias("email")
    private String email;

    @JsonAlias("senha")
    private String senha;

    @JsonAlias("dataNasc")
    private String dataNasc;

    @JsonAlias("foto")
    private String foto;

    @JsonAlias("valorHora")
    private Double valorHora;

    @JsonAlias("resumoQualificacoes")
    private String resumoQualificacoes;

    @JsonAlias("endereco")
    private Endereco endereco;

    @JsonAlias("subcategoria")
    private Subcategoria subcategoria;

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public String getResumoQualificacoes() {
        return resumoQualificacoes;
    }

    public void setResumoQualificacoes(String resumoQualificacoes) {
        this.resumoQualificacoes = resumoQualificacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }




}
