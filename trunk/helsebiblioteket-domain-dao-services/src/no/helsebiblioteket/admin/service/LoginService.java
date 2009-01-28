package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface LoginService extends Serializable {
    public SingleResult<User> loginUserByUsernamePassword(String username, String password);
    public SingleResult<MemberOrganization> loginOrganizationByIpAddress(IpAddress ipAddress);
    public Boolean sendPasswordEmail(User user);
}
