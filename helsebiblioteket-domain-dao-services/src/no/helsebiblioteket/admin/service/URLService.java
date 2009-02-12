package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.requestresult.SingleResult;

public interface URLService extends Serializable {
    public Boolean isAffected(Url url);
    public SingleResult<Url> translate(User user, Url url);
    public SingleResult<Url> translate(MemberOrganization organization, Url url);
    public SingleResult<Url> translate(User user, MemberOrganization organization, Url url);
    public Boolean hasAccess(User user, Url url);
    public Boolean hasAccess(MemberOrganization organization, Url url);
    public Boolean hasAccess(User user, MemberOrganization organization, Url url);
    public SingleResult<String> group(Url url);
    public String groupWS(Url url);
}
