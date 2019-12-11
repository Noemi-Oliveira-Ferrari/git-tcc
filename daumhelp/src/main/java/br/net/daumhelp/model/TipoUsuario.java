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
	private Long idTipoUsuario;
	@NotNull
	private Character tipoDeUsuario;

	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public Character getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(Character tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + ", tipoUsuario=" + tipoDeUsuario + "]";
	}

}
