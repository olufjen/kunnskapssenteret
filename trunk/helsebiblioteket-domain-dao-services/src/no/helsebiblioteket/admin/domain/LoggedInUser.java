package no.helsebiblioteket.admin.domain;

public class LoggedInUser {
	private Integer id;
	private String username;
	private String roleKey;
	private String roleName;
	private String firstName;
	private String lastName;
	private String employer;
	private String studentNumber;
	private String hprNumber;
	private String dateOfBirth;
	private Boolean isStudent;
	private String positionText;
	private Boolean receiveNewsletter;
	private Boolean participateSurvey;
	private String email;
	private String positionKey;
	private String positionName;
	private String positionDescription;

	public String getName() {
		if(this.lastName.equals("")){
			return this.firstName;
		} else if(this.firstName.equals("")){
			return this.lastName;
		} else {
			return this.firstName + " " + this.lastName;
		}
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
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getHprNumber() {
		return hprNumber;
	}
	public void setHprNumber(String hprNumber) {
		this.hprNumber = hprNumber;
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
	public Boolean getReceiveNewsletter() {
		return receiveNewsletter;
	}
	public void setReceiveNewsletter(Boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}
	public Boolean getParticipateSurvey() {
		return participateSurvey;
	}
	public void setParticipateSurvey(Boolean participateSurvey) {
		this.participateSurvey = participateSurvey;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPositionKey() {
		return positionKey;
	}
	public void setPositionKey(String positionKey) {
		this.positionKey = positionKey;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionDescription() {
		return positionDescription;
	}
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}