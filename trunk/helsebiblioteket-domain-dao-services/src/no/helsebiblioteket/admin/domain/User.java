package no.helsebiblioteket.admin.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	protected Integer id = null;
	protected String username = null;
	protected String password = null;
	protected Organization organization = null;
	// FIXME: Set this in UserDAO or service!
	protected List<Role> roleList = new ArrayList<Role>();
	protected List<Access> accessList = null;
	private Person person;
//	public String getName(){
//		// FIXME: Remove this!
//		return "Name " + this.username;
//	}
	public boolean hasRole(Role someRole){
		for (Role role : this.roleList) {
			if(role.getKey().equals(someRole.getKey())){return true;}
		}
		return false;
	}
	public String getRoleText(){
		// TODO: Remove this?
		StringBuffer result = new StringBuffer();
		for (int i=0; i<this.roleList.size(); i++) {
			Role role = this.roleList.get(i);
			result.append(role.getRoleName());
			if(i < this.roleList.size() - 1) { result.append(", "); }
		}
		return result.toString();
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
