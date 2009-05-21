package no.helsebiblioteket.admin.domain.key;

import java.io.Serializable;

public class UserRoleKey implements Serializable {
	public static final UserRoleKey none = new UserRoleKey("none");
	
	public static final UserRoleKey administrator = new UserRoleKey("administrator");
	public static final UserRoleKey health_personnel = new UserRoleKey("health_personnel");
	public static final UserRoleKey student = new UserRoleKey("student");
	public static final UserRoleKey health_personnel_other = new UserRoleKey("health_personnel_other");

	private String value;
	public UserRoleKey() { }
	public UserRoleKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
