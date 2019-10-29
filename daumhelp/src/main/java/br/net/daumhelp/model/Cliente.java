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

@Entity
@Table(name = "tbl_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@NotNull
	@Size(min = 3, max = 200, message = "O nome não pode ter menos que 3 caractéres")
	private String nome;
	@NotNull
	@Size(min = 5, max = 255, message = "O e-mail digitado é pssui menos que 5 caractéres")
	private String email;

	@Size(min = 11, max = 20, message = "CPF menor que 11 digitos")
	private String cpf;

	private String foto;

	@NotNull
	@Size(min = 8, max = 130, message = "A senha deve conter pelo ao menos 8 caractéres")
	private String senha;

	@NotNull
	@Size(min = 6, max = 20, message = "Data digitado tem menos que 6 digitos")
	private String dataNasc;

	@NotNull
	@OneToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idTipoUsuario")
	private TipoUsuario tipoUsuario;

	private String criadoEm;
	private String atualizadoEm;
	
	@Size(min = 4, max = 150)
	private String foto;
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", foto="
				+ foto + ", senha=" + senha + ", dataNasc=" + dataNasc + ", endereco=" + endereco + ", tipoUsuario="
				+ tipoUsuario + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + "]";
	}

	
}
