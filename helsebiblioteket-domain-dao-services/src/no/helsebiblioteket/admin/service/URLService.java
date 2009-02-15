package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;

public interface URLService extends Serializable {
    public Boolean isAffected(Url url);
    public SingleResultUrl translate(User user, Url url);
    public SingleResultUrl translate(MemberOrganization organization, Url url);
    public SingleResultUrl translate(User user, MemberOrganization organization, Url url);
    public Boolean hasAccess(User user, Url url);
    public Boolean hasAccess(MemberOrganization organization, Url url);
    public Boolean hasAccess(User user, MemberOrganization organization, Url url);
    public SingleResultString group(Url url);
    public String groupWS(Url url);
}
