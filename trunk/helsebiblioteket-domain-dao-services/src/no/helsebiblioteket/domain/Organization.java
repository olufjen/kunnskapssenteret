package no.helsebiblioteket.domain;

import java.net.InetAddress;

import no.helsebiblioteket.admin.domain.OrganizationType;

public class Organization {
	private Integer id = null;
	private Organization parentOrganization = null;
	private String name = null;
	private String description = null;
	private String nameShort = null;
	private OrganizationType organizationType = null;

	public String getName(){
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
