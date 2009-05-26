package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;

public interface LoginService extends Serializable {
    public LoggedInUserResult loginUserByUsernamePassword(String username, String password);
    public LoggedInOrganizationResult loginOrganizationByIpAddress(IpAddress ipAddress);
    
    public SendPasswordEmailResult sendPasswordEmail(String emailAddress, Email email);
}
