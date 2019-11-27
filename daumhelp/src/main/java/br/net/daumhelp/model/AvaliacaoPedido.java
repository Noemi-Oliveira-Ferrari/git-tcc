package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_avaliacao_pedido")
public class AvaliacaoPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacaoPedido;
	private String comentario;

	@NotNull
	private Integer nota;
	private String dataAvaliacao;
	private String criadoEm;
	private String atualizadoEm;
	private Pedido pedido;

	public Long getIdAvaliacaoPedido() {
		return idAvaliacaoPedido;
	}

	public void setIdAvaliacaoPedido(Long idAvaliacaoPedido) {
		this.idAvaliacaoPedido = idAvaliacaoPedido;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "AvaliacaoPedido [idAvaliacaoPedido=" + idAvaliacaoPedido + ", comentario=" + comentario + ", nota="
				+ nota + ", dataAvaliacao=" + dataAvaliacao + ", criadoEm=" + criadoEm + ", atualizadoEm="
				+ atualizadoEm + ", pedido=" + pedido + "]";
	}

}
