package br.net.daumhelp.jwt.model;
import java.io.Serializable;

public class JWTRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String email;
	private String password;

	public JWTRequest() {
	}

	public JWTRequest(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}