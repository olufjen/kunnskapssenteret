package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class User  {
	// Primary key
	private Integer id;
	
	// Local value
	private String username;
	private String password;
	private Date lastChanged;

	// References
	// This is the only one we know that need to be initialized.
	// May drop initialization when we learn more about Ibatis.
	// FIXME: Insert something?
	private Organization organization = null;//new UnknownOrganization();
	private Person person;

	// Values loaded with UserRoleLine
	private Role[] roleList;
	
	// Helpers
	@Override
	public String toString() {
		return "[" + id + ": " + username + "(" + password + ", " + lastChanged + ")]";
	}
	@Override
	public int hashCode() {
		// FOR LATER: Make this a little bit smarter
//		return toString().hashCode();
		return getId(); //BIT_STUFF
	}
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

	public Role[] getRoleList() {
		return roleList;
	}

	public void setRoleList(Role[] roleList) {
		this.roleList = roleList;
	}
}
