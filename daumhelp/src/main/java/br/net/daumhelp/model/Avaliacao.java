package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_avaliacao")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacao;
	private String comentario;

	@NotNull
	private Integer nota;
	private String dataAvaliacao;

	@ManyToOne
	@JoinColumn(name = "id_profissional")
	private Profissional profissional;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	private String criadoEm;
	private String atualizadoEm;

	public Long getIdAvaliacaoPedido() {
		return idAvaliacao;
	}

	public void setIdAvaliacaoPedido(Long idAvaliacaoPedido) {
		this.idAvaliacao = idAvaliacaoPedido;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(String dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		return "Avaliacao [idAvaliacaoPedido=" + idAvaliacao + ", comentario=" + comentario + ", nota=" + nota
				+ ", dataAvaliacao=" + dataAvaliacao + ", profissional=" + profissional + ", cliente=" + cliente
				+ ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + "]";
	}

}
