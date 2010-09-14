package no.helsebiblioteket.admin.service;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.AccessResult;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultString;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUrl;

public interface URLService extends Serializable {
    public Boolean isAffected(Url url);
    public SingleResultUrl translateUrlNone(Url url);
    public SingleResultUrl translateUrlNational(Url url);
    public SingleResultUrl translateUrlUser(UserListItem user, Url url);
    public SingleResultUrl translateUrlOrganization(OrganizationListItem organization, Url url);
    public SingleResultUrl translateUrlUserOrganization(UserListItem user, OrganizationListItem organization, Url url);
    public AccessResult hasAccessNone(Url url);
    public AccessResult hasAccessNational(Url url);
    public AccessResult hasAccessUser(UserListItem user, Url url);
    public AccessResult hasAccessOrganization(OrganizationListItem organization, Url url);
    public AccessResult hasAccessUserOrganization(UserListItem user, OrganizationListItem organization, Url url);
    public SingleResultString group(Url url);
}
