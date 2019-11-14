package br.net.daumhelp.jwt.model;

import java.io.Serializable;

public class JWTResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JWTResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
 
	public String getToken() {
		return this.jwttoken;
	}
}