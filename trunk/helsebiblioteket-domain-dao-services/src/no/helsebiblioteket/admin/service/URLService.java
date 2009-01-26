package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface URLService extends Serializable {
    public Boolean isAffected(Url url);
    public SingleResult<Url> translate(User user, Url url);
    public SingleResult<Url> translate(Organization organization, Url url);
    public SingleResult<Url> translate(User user, Organization organization, Url url);
    public Boolean hasAccess(User user, Url url);
    public Boolean hasAccess(Organization organization, Url url);
    public Boolean hasAccess(User user, Organization organization, Url url);
    public SingleResult<String> group(Url url);
}
