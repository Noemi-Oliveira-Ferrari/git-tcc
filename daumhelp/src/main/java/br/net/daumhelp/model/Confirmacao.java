package br.net.daumhelp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Confirmacao {

	@NotNull
	private String nome;
	@NotNull
	private String destinatario;
	@NotNull
	@Size(min = 4, max = 4)
	private String codigoConfirm;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getCodigoConfirm() {
		return codigoConfirm;
	}

	public void setCodigoConfirm(String codigoConfirm) {
		this.codigoConfirm = codigoConfirm;
	}

}
