package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewEndUserBean extends NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
    private String firstname;
    private String lastname;
    private String studentHprNo;
    private String employer;
    private boolean newsletter;
    private boolean questionnaire;
    private String emailaddress;
    private String username;
    private String password;
    private List<SelectItem> available;
    private List<SelectItem> availableValues;
    private List<SelectItem> chosen;
    private List<SelectItem> chosenValues;
    
    public List<SelectItem> getAvailableValues(){
    	List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem option = new SelectItem("ch1", "choice1", "This bean is for selectItems tag", true);
		list.add(option);
		return list;
    }
    public void setAvailableValues(List<SelectItem> list){
    	
    }
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getStudentHprNo() {
		return studentHprNo;
	}
	public void setStudentHprNo(String studentHprNo) {
		this.studentHprNo = studentHprNo;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public boolean isNewsletter() {
		return newsletter;
	}
	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}
	public boolean isQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(boolean questionnaire) {
		this.questionnaire = questionnaire;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
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
	public List<SelectItem> getAvailable() {
		return available;
	}
	public void setAvailable(List<SelectItem> available) {
		this.available = available;
	}
	public List<SelectItem> getChosen() {
		return chosen;
	}
	public void setChosen(List<SelectItem> chosen) {
		this.chosen = chosen;
	}
	public List<SelectItem> getChosenValues() {
    	List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem option = new SelectItem("ch1", "choice1", "This bean is for selectItems tag", true);
		list.add(option);
		return list;
	}
	public void setChosenValues(List<SelectItem> chosenValues) {
		this.chosenValues = chosenValues;
	}
	public Log getLogger() {
		return logger;
	}
}
