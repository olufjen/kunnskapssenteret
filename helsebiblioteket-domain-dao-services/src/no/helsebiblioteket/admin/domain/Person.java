package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Person {
	// Primary key
	private Integer id;
	
	// Local values
	private String firstName;
	private String lastName;
	// TODO: Does not exist in the database
	private String employer;
	private String studentNumber;
	private String hprNumber;
	private Date lastChanged;

	// References
	private Position position;
	private ContactInformation contactInformation;
	private Profile profile;
	
	// Helpers
	@Override
	public String toString() {
		return id + ": " + firstName + " " + lastName;
	}
	public String getName() {
		return this.firstName + " " + lastName;
	}
	
	// GET/SET
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
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getHprNumber() {
		return hprNumber;
	}
	public void setHprNumber(String hprNumber) {
		this.hprNumber = hprNumber;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
