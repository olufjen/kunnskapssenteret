package no.helsebiblioteket.admin.domain;

import java.util.Locale;

public class OrganizationName {
	private Integer id = null;
	private String name = null;
	private Locale locale = null;
	private OrganizationNameCategory category = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public OrganizationNameCategory getCategory() {
		return category;
	}
	public void setCategory(OrganizationNameCategory category) {
		this.category = category;
	}
	
	public boolean equals(OrganizationName orgName) {
        if (this == orgName) return true;
        if (name != null ? !name.equals(orgName.name) : orgName.name != null) return false;
        if (locale != null ? !locale.equals(orgName.locale) : orgName.locale != null) return false;
        if (category != null ? !category.equals(orgName.category) : orgName.category!= null) return false;
        return true;
    }
	
	public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}