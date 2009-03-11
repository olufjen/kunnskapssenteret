package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;

public interface URLService extends Serializable {
    public Boolean isAffected(Url url);
    public SingleResultUrl translateUrlUser(User user, Url url);
    public SingleResultUrl translateUrlOrganization(MemberOrganization organization, Url url);
    public SingleResultUrl translateUrlUserOrganization(User user, MemberOrganization organization, Url url);
    public Boolean hasAccessUser(User user, Url url);
    public Boolean hasAccessOrganization(MemberOrganization organization, Url url);
    public Boolean hasAccessUserOrganization(User user, MemberOrganization organization, Url url);
    public SingleResultString group(Url url);
}
