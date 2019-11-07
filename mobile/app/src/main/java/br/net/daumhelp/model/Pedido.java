package br.net.daumhelp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pedido implements Serializable {


    private int idPedido;
    private String descricao;
    private String dataHora;
    private String horasServico;
    private String dataServico;
    private String horarioInicial;
    private String horarioFinal;
    private String foto1;
    private String foto2;
    private String foto3;
    private Double valorServico;
    private Double multaCliente;
    private String finalizado;
    private Status status;
    private Cliente cliente;
    private Profissional profissional;


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getHorasServico() {
        return horasServico;
    }

    public void setHorasServico(String horasServico) {
        this.horasServico = horasServico;
    }

    public String getDataServico() {
        return dataServico;
    }

    public void setDataServico(String dataServico) {
        this.dataServico = dataServico;
    }

    public String getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(String horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public String getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(String horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public Double getMultaCliente() {
        return multaCliente;
    }

    public void setMultaCliente(Double multaCliente) {
        this.multaCliente = multaCliente;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", descricao='" + descricao + '\'' +
                ", dataHora='" + dataHora + '\'' +
                ", horasServico='" + horasServico + '\'' +
                ", dataServico='" + dataServico + '\'' +
                ", horarioInicial='" + horarioInicial + '\'' +
                ", horarioFinal='" + horarioFinal + '\'' +
                ", foto1='" + foto1 + '\'' +
                ", foto2='" + foto2 + '\'' +
                ", foto3='" + foto3 + '\'' +
                ", valorServico=" + valorServico +
                ", multaCliente=" + multaCliente +
                ", finalizado='" + finalizado + '\'' +
                ", status=" + status +
                ", cliente=" + cliente +
                ", profissional=" + profissional +
                '}';
    }

}

