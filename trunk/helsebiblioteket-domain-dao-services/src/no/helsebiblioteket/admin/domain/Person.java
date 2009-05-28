package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Person implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private String firstName;
	private String lastName;
	private String employer;
	private String studentNumber;
	private String dateOfBirth;
	private String hprNumber;
	private Date lastChanged;
	private Boolean isStudent;

	// References
	private Position position = new Position();
	private String positionText;
	private ContactInformation contactInformation = new ContactInformation();
	private Profile profile = new Profile();
	
	// Helpers
	@Override
	public String toString() {
		return id + ": " + firstName + " " + lastName;
	}
	public String getName() {
		if("".equals(this.lastName)){
			return this.firstName;
		} else if("".equals(this.firstName)) {
			return this.lastName;
		} else {
			return this.firstName + " " + this.lastName;
		}
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
	public Boolean getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
	public String getPositionText() {
		return positionText;
	}
	public void setPositionText(String positionText) {
		this.positionText = positionText;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}