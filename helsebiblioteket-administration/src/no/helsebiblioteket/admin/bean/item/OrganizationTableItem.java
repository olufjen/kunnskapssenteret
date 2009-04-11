package no.helsebiblioteket.admin.bean.item;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;


public class OrganizationTableItem {
	private String text;
	private OrganizationListItem organization;
	private boolean showLinks;
	
	public boolean isShowLinks() {
		return showLinks;
	}
	public void setShowLinks(boolean showLinks) {
		this.showLinks = showLinks;
	}
	public OrganizationListItem getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationListItem organization) {
		this.organization = organization;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
