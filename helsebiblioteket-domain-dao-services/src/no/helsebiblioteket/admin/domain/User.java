package no.helsebiblioteket.admin.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	protected Integer id;
	protected String username;
	protected String password;
	protected Organization organization;
	protected Role role;
	protected List<Access> accessList;
	private Person person;
	public List<Role> getRoleList(){
		// TODO: Is this correct?
		ArrayList<Role> list = new ArrayList<Role>();
		list.add(this.role);
		return list;
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
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Access> getAccessList() {
		return accessList;
	}
	public void setAccessList(List<Access> accessList) {
		this.accessList = accessList;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
