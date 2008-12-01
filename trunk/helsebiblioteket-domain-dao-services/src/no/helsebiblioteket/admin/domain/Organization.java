package no.helsebiblioteket.admin.domain;

import java.util.List;

public class Organization {
	private Integer id = null;
	private Organization parentOrganization = null;
	private String name = null;
	private String description = null;
	private String nameShort = null;
	private OrganizationType type = null;
	private List<IpRange> ipRangeList = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<IpRange> getIpRangeList() {
		return ipRangeList;
	}
	public void setIpRangeList(List<IpRange> ipRangeList) {
		this.ipRangeList = ipRangeList;
	}
	public Organization getParentOrganization() {
		return parentOrganization;
	}
	public void setParentOrganization(Organization parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNameShort() {
		return nameShort;
	}
	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType organizationType) {
		this.type = organizationType;
	}
}
