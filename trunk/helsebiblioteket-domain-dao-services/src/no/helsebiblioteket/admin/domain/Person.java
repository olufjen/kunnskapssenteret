package no.helsebiblioteket.admin.domain;

public class Person {
	private Integer id = null;
	private String firstName = null;
	private String lastName = null;
	private Integer studentNumber = null;
	private Integer hprNumber = null;
	private ContactInformation contactInformation = null;
	private Profile profile = null;
	public String getName() {
		return this.firstName + " " + lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
