package no.helsebiblioteket.domain;

import java.net.InetAddress;

public class Organization {
	private Integer id = null;
	private Organization parentOrganization = null;
	private String name = null;
	private String description = null;
	private String nameShort = null;
	private OrganizationType organizationType = null;
}
