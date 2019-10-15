package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_tipo_usuario")
public class TipoUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tipo_usuario;
	@NotNull
	private Character tipo_usuario;

	public Long getId_tipo_usuario() {
		return id_tipo_usuario;
	}

	public void setId_tipo_usuario(Long id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
	}

	public Character getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(Character tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	@Override
	public String toString() {
		return "TipoUsuario [id_tipo_usuario=" + id_tipo_usuario + ", tipo_usuario=" + tipo_usuario + "]";
	}

}
