package no.helsebiblioteket.admin.service;

import no.helsebiblioteket.admin.domain.Email;

public interface EmailService {
	public void sendEmail(Email email);
}
