package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
}