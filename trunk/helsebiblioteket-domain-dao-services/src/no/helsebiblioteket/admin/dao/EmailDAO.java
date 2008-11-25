package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Email;

public interface EmailDAO {
	public void sendEmail(Email email);
}
