package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@NotNull
	@Size(min = 10, message = "A descrição está muito curta")
	private String descricao;

	private String dataHora;

	private Integer horasServico;

	@NotNull
	private String data_servico;

	private String horarioInicial;

	private String horarioFinal;

	private String foto1;

	private String foto2;

	private String foto3;

	private Double valorServico;

	@ManyToOne
	@JoinColumn(name = "idStatusPedido")
	private StatusPedido status;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idProfissional")
	private Profissional profissional;

	private String criadoEm;

	private String atualizadoEm;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
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

	public Integer getHorasServico() {
		return horasServico;
	}

	public void setHorasServico(Integer horasServico) {
		this.horasServico = horasServico;
	}

	public String getData_servico() {
		return data_servico;
	}

	public void setData_servico(String data_servico) {
		this.data_servico = data_servico;
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

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
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

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", descricao=" + descricao + ", dataHora=" + dataHora
				+ ", horasServico=" + horasServico + ", data_servico=" + data_servico + ", horarioInicial="
				+ horarioInicial + ", horarioFinal=" + horarioFinal + ", foto1=" + foto1 + ", foto2=" + foto2
				+ ", foto3=" + foto3 + ", valorServico=" + valorServico + ", status=" + status + ", cliente=" + cliente
				+ ", profissional=" + profissional + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + "]";
	}

}
