package no.helsebiblioteket.admin.domain;

import java.util.Date;
import java.util.List;

public class User  {
	// Primary key
	private Integer userId;
	
	// Local value
	private String username;
	private String password;
	private Date lastChanged;

	// References
	// This is the only one we know that need to be initialized.
	// May drop initialization when we learn more about Ibatis.
	private Organization organization = new UnknownOrganization();
	private Person person;

	// Values loaded with UserRoleLine
	private List<Role> roleList;
	
	// Helpers
	public boolean hasRole(Role role){
		for (Role userRole : this.roleList) {
			if(userRole.getKey().equals(role.getKey())){
				return true;
			}
		}
		return false;
	}

	// GET/SET
	public Integer getId() {
		return userId;
	}
	public void setId(Integer userId) {
		this.userId = userId;
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
