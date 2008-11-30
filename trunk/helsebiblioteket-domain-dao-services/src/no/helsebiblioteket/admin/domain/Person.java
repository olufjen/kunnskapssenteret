package no.helsebiblioteket.admin.domain;

public class Person {
	private Integer id = null;
	private String firstName = null;
	private String lastName = null;
	private String employer = null;
	private Position position = new Position();
	private boolean isStudent;
	private Integer studentNumber = null;
	private Integer hprNumber = null;
	private ContactInformation contactInformation = new ContactInformation();
	private Profile profile = new Profile();
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
}
