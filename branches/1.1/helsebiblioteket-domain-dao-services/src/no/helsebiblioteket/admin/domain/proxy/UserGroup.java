package no.helsebiblioteket.admin.domain.proxy;

public class UserGroup implements Comparable<UserGroup> {
	private String name;

	@Override
	public int compareTo(UserGroup o) {
		return this.name.compareTo(o.name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
