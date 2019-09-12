package br.senai.sp.daumhelp.model;

import java.io.Serializable;

public class Profissional implements Serializable {

    private int idProfissional;
    private String nomePro;
    private String cpfPro;
    private String emailPro;
    private String senhaPro;
    private String dataNascPro;
    private String fotoPro;
    private Double valorPro;
    private int idEnderecoPro;
    private int idSubcategoria;

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNomePro() {
        return nomePro;
    }

    public void setNomePro(String nomePro) {
        this.nomePro = nomePro;
    }

    public String getCpfPro() {
        return cpfPro;
    }

    public void setCpfPro(String cpfPro) {
        this.cpfPro = cpfPro;
    }

    public String getEmailPro() {
        return emailPro;
    }

    public void setEmailPro(String emailPro) {
        this.emailPro = emailPro;
    }

    public String getSenhaPro() {
        return senhaPro;
    }

    public void setSenhaPro(String senhaPro) {
        this.senhaPro = senhaPro;
    }

    public String getDataNascPro() {
        return dataNascPro;
    }

    public void setDataNascPro(String dataNascPro) {
        this.dataNascPro = dataNascPro;
    }

    public String getFotoPro() {
        return fotoPro;
    }

    public void setFotoPro(String fotoPro) {
        this.fotoPro = fotoPro;
    }

    public Double getValorPro() {
        return valorPro;
    }

    public void setValorPro(Double valorPro) {
        this.valorPro = valorPro;
    }

    public int getIdEnderecoPro() {
        return idEnderecoPro;
    }

    public void setIdEnderecoPro(int idEnderecoPro) {
        this.idEnderecoPro = idEnderecoPro;
    }

    public int getIdSubcategoria() {
        return idSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        this.idSubcategoria = idSubcategoria;
    }

    @Override
    public String toString() {
        return "Profissional{" +
                "idProfissional=" + idProfissional +
                ", nomePro='" + nomePro + '\'' +
                ", cpfPro='" + cpfPro + '\'' +
                ", emailPro='" + emailPro + '\'' +
                ", senhaPro='" + senhaPro + '\'' +
                ", dataNascPro='" + dataNascPro + '\'' +
                ", fotoPro='" + fotoPro + '\'' +
                ", valorPro=" + valorPro +
                ", idEnderecoPro=" + idEnderecoPro +
                ", idSubcategoria=" + idSubcategoria +
                '}';
    }
}
