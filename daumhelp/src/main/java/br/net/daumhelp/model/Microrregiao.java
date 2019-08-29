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
@Table(name = "tbl_microrregiao")
public class Microrregiao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMicro;
	@NotNull
	private String micro;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_uf")
	private UF uf;

	public Long getIdMicro() {
		return idMicro;
	}

	public void setIdMicro(Long idMicro) {
		this.idMicro = idMicro;
	}

	public String getMicrorregiao() {
		return micro;
	}

	public void setMicrorregiao(String microrregiao) {
		this.micro = microrregiao;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Microrregiao [idMicro=" + idMicro + ", microrregiao=" + micro + ", uf=" + uf + "]";
	}

}
