package com.mgg.springboot.dao.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
	private final String accessToken;
	private final Users user;

	public JwtResponse(String accessToken, Users user) {
		this.accessToken = accessToken;
		this.user = user;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public Users getUser() {
		return this.user;
	}
}
