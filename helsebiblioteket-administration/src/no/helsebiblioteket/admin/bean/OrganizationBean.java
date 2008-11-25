package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.domain.Organization;

public class OrganizationBean {
	private String searchinput;
	private List<Organization> organizations;
	public String getSearchinput() {
		return searchinput;
	}
	public void setSearchinput(String searchinput) {
		this.searchinput = searchinput;
	}
	public void search() {
		
	}
	public List<Organization> getOrganizations() {
		List<Organization> myOrganizations = new ArrayList<Organization>();
		{
			Organization organization = new Organization();
			organization.setName("Sykehuset i Bodø");
			myOrganizations.add(organization);
		}
		{
			Organization organization = new Organization();
			organization.setName("Sykehuset i Bodø");
			myOrganizations.add(organization);
		}
		this.organizations = myOrganizations;
		return this.organizations;
	}
}
