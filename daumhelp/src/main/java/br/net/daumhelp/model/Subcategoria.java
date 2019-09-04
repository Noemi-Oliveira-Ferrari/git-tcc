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
@Table(name = "tbl_subcategoria")
public class Subcategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSubcategoria;
	@NotNull
	private String subcategoria;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;

	public Long getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(Long idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Subcategoria [idSubcategoria=" + idSubcategoria + ", subcategoria=" + subcategoria + ", categoria="
				+ categoria + "]";
	}
	
	

}
