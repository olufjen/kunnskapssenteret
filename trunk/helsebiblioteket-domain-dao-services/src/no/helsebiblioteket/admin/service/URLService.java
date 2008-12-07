package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;

public interface URLService extends Serializable {
    public Url translate(User user, Organization organization, Url url);
    public Boolean isAffected(Url url);
}
