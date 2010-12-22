package no.helsebiblioteket.admin.dao.keys;

public class RoleSystemCompositeKey {
	private String systemKey;
	private String roleKey;
	public String getSystemKey() {
		return systemKey;
	}
	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
}
