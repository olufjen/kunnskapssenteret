package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.domain.User;

public interface LoginService extends Serializable {
    public boolean logInUser(User user);
    public void sendPasswordEmail(User user);
}
