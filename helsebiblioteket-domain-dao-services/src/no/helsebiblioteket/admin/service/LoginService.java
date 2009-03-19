package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;

public interface LoginService extends Serializable {
    public SingleResultUser loginUserByUsernamePassword(String username, String password);
    public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress);

    // TODO: Remove:
    public SingleResultPosition hmmm(Person person);
    
    public Boolean sendPasswordEmail(String emailAddress);
}
