package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.parameter.UserExportParameter;

public interface UserExportDao {
	// Fetch
	public List<User> getUserExportList(UserExportParameter parameter);
}