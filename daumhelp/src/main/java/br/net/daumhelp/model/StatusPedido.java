package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_status_pedido")
public class StatusPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_status_pedido;

	@NotNull
	@Size(min = 3, max = 100, message = "Status inválido")
	private String status;

	public Long getId_status_pedido() {
		return id_status_pedido;
	}

	public void setId_status_pedido(Long id_status_pedido) {
		this.id_status_pedido = id_status_pedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusPedido [id_status_pedido=" + id_status_pedido + ", status=" + status + "]";
	}

}
