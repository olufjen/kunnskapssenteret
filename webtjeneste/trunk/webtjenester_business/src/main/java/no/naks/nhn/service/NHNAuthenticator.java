package no.naks.nhn.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class NHNAuthenticator extends Authenticator {
	private final String username;   private final char[] password;   
	public NHNAuthenticator(final String username, final String password) {
		super();     this.username = new String(username); 
		this.password = password.toCharArray();    }
	@Override   public PasswordAuthentication getPasswordAuthentication() {
		return (new PasswordAuthentication (username, password));   } 
}
