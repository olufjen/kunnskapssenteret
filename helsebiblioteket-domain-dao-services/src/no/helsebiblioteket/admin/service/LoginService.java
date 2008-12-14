package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;

public interface LoginService extends Serializable {
    public User logInUser(User user);
    public boolean sendPasswordEmail(User user);
    public Organization logInIpAddress(IpAddress address);
}
