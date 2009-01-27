package no.helsebiblioteket.admin.domain;

import java.util.Date;
import java.util.List;

public class User  {
	private Integer id;
	private String username;
	private String password;
	private Date lastChanged;

	private Organization organization;
	private Person person;

//	private List<Access> accessList;
	private List<Role> roleList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean hasRole(Role role){
		for (Role userRole : this.roleList) {
			if(userRole.getKey().equals(role.getKey())){
				return true;
			}
		}
		return false;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
