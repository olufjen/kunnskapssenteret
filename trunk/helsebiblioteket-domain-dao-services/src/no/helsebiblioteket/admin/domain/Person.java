package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Person {
	private Integer id;
	private String firstName = "";
	private String lastName = "";
	private String employer = "";
	private Position position = new Position();
	private boolean isStudent;
	private Integer studentNumber;
	private Integer hprNumber;
	private ContactInformation contactInformation = new ContactInformation();
	private Profile profile = new Profile();
	private User user;
	private Date lastChanged = null;
	
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
	public Integer getHprNumber() {
		return hprNumber;
	}
	public void setHprNumber(Integer hprNumber) {
		this.hprNumber = hprNumber;
	}
	public boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	public Integer getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Integer studentNumber) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	// FIXME: Hva i alle dager er dette? Skaper problemer for Axis!
//	public void setStudent(boolean isStudent) {
//		this.isStudent = isStudent;
//	}
}
