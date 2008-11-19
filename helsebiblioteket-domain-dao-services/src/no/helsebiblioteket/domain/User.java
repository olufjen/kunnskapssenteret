package no.helsebiblioteket.domain;

import java.util.List;

public class User {
	protected Integer id = null;
	protected String username = null;
	protected String password = null;
	protected Organization organization = null;
	protected List<Role> roleList = null;
	protected List<Access> accessList = null;
	
	public String getName(){
		return "Bruker " + this.username;
	}
	public String getRoles(){
		return "Roles: " + this.roleList.size();
	}
	
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
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organizaion) {
		this.organization = organizaion;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Access> getAccessList() {
		return accessList;
	}
	public void setAccessList(List<Access> accessList) {
		this.accessList = accessList;
	}
}
