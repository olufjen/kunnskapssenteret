package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
	// Primary key
	private Integer id;
	
	// Local value
	private String username;
	private String password;
	private Date lastChanged;
	private boolean deleted;

	private Person person;

	// Values loaded with UserRoleLine
	private Role[] roleList;
	
	
	// Helpers
	@Override
	public String toString() {
		return "[" + id + ": " + username + " (," + lastChanged + ")]";
	}
	@Override
	public int hashCode() {
		// FOR LATER: Make this a little bit smarter. Maybe.
		return this.id != null ? this.id.hashCode() : this.getUsername() != null ? this.getUsername().hashCode() : -1;
	}
	public boolean equals(Object object) {
		boolean equals = false;
		User userObject = null;
		if (object != null && object instanceof User) {
			userObject = (User) object;
			if (userObject.getId() != null && this.id != null) {
				equals = userObject.getId().equals(this.id);
			} else if (userObject.getUsername() != null && this.username != null) {
				equals = userObject.getUsername().equals(this.username);
			}
		}
		return equals;
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
	public void setRoleListAsList(List<Role> roleList) {
		if (roleList != null) {
			this.roleList = (Role[]) roleList.toArray(new Role[roleList.size()]);
		} else {
			this.roleList = null;
		}
	}
	
	public List<Role> getRoleListAsList() {
		if (this.roleList != null) {
			return Arrays.asList(this.roleList);
		} else {
			return null;
		}
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
