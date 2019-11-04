package br.net.daumhelp.jwt.model;
import java.io.Serializable;

public class JWTRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String email;
	private String senha;

	public JWTRequest() {
	}

	public JWTRequest(String email, String senha) {
		this.setEmail(email);
		this.setSenha(senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}