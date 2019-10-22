package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "tbl_profissional")
public class Profissional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProfissional;
	@NotNull
	@Size(min = 3, max = 200)
	private String nome;
	@NotNull
	@Size(min = 10, max = 255)
	private String email;

	@Size(min = 4, max = 150)
	private String foto;

	@Size(min = 14, max = 20)
	private String cnpj;
 
	@Size(min = 11, max = 20)
	private String cpf;

	@NotNull
	@Size(min = 8, max = 200)
	private String senha;

	@NotNull
	@Size(min = 6, max = 20)
	private String dataNasc;

	@NotNull
	private Double valorHora;

	private String resumoQualificacoes;

	@NotNull
	@OneToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;
	@NotNull
	@OneToOne
	@JoinColumn(name = "idSubcategoria")
	private Subcategoria subcategoria;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idTipoUsuario")
	private TipoUsuario tipoUsuario;

	private String criadoEm;
	private String atualizadoEm;

	public Long getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public String getResumoQualificacoes() {
		return resumoQualificacoes;
	}

	public void setResumoQualificacoes(String resumoQualificacoes) {
		this.resumoQualificacoes = resumoQualificacoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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
		return "Profissional [idProfissional=" + idProfissional + ", nome=" + nome + ", email=" + email + ", foto="
				+ foto + ", cnpj=" + cnpj + ", cpf=" + cpf + ", senha=" + senha + ", dataNasc=" + dataNasc
				+ ", valorHora=" + valorHora + ", resumoQualificacoes=" + resumoQualificacoes + ", endereco=" + endereco
				+ ", subcategoria=" + subcategoria + ", tipoUsuario=" + tipoUsuario + ", criadoEm=" + criadoEm
				+ ", atualizadoEm=" + atualizadoEm + "]";
	}

}
