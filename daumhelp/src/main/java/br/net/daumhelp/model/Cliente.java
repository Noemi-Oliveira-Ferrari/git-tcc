package br.net.daumhelp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@NotNull
	@Size(min = 3, max = 100)
	private String nome;
	@NotNull
	@Size(min = 10, max = 100)
	private String email;
	@NotNull
	@Size(min = 4, max = 150)
	private String foto;

	@Size(min = 11, max = 20)
	private String cpf;

	@NotNull
	@Size(min = 8, max = 128)
	private String senha;
	
	@NotNull
	@Size(min = 6, max = 20)
	private String dataNasc;

	@NotNull
	@OneToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@NotNull
	private Long idTipoUsuario;
	
	private String criadoEm;
	private String atualizadoEm;

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


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", foto=" + foto + ", cpf="
				+ cpf + ", senha=" + senha + ", dataNasc=" + dataNasc + ", endereco=" + endereco + ", idTipoUsuario="
				+ idTipoUsuario + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + "]";
	}
}
