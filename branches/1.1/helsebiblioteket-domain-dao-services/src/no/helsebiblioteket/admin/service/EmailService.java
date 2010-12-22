package no.helsebiblioteket.admin.service;

import no.helsebiblioteket.admin.domain.Email;

public interface EmailService {
	public Boolean sendEmail(Email email);
}
