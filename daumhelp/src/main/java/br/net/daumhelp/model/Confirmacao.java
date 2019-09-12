package br.net.daumhelp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Confirmacao {
	
	@NotNull
	private String remetente;
	@NotNull
	@Size(min = 4, max = 4)
	private String codigoConfirm;
	
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getCodigoConfirm() {
		return codigoConfirm;
	}
	public void setCodigoConfirm(String codigoConfirm) {
		this.codigoConfirm = codigoConfirm;
	}
	
	
}
