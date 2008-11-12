package no.helsebiblioteket.domain;

import java.util.ArrayList;

public class User {
	protected Integer id = null;
	protected String username = null;
	protected String password = null;
	protected Organization organizaion = null;
	protected ArrayList<Role> roleList = null;
	protected ArrayList<Access> accessList = null;
	
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
	public Organization getOrganizaion() {
		return organizaion;
	}
	public void setOrganizaion(Organization organizaion) {
		this.organizaion = organizaion;
	}
	public ArrayList<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(ArrayList<Role> roleList) {
		this.roleList = roleList;
	}
	public ArrayList<Access> getAccessList() {
		return accessList;
	}
	public void setAccessList(ArrayList<Access> accessList) {
		this.accessList = accessList;
	}
}
