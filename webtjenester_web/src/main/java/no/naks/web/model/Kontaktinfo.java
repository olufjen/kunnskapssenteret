package no.naks.web.model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.event.ValueChangeEvent;

import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.services.nhn.client.Organization;

public class Kontaktinfo implements Serializable {
	protected String Onskerhjelp;
	private String gjentaEpost;
	private String lederGjentaEpost;
	private Person person;
	private Person leder;
	private IBasismelding melding;
	private boolean phHidden;
	private boolean blmailHidden;
	private boolean blgjentaHidden;
	private boolean bllederHidden;
	
	//person validation variables
	private boolean blEpost = false;
	private boolean blGjentaEpost = false;
	private boolean blGjyldigEpost = false;
	private boolean blPhone = false;
	
	//Leder validation variables.
	private boolean blLederGjentaEpost = false;
	private boolean blLederGjyldigEpost = false;
	private boolean blLederPhone = false;
	
	private Organization foretakOrganisasjon = null; // Organisasjon skapt når bruker har skrevet navnet eller valgt fra et utvalg. 

	public Kontaktinfo() {
		super();
		
	}
	
	public boolean isPhHidden() {
		return phHidden;
	}

	public Organization getForetakOrganisasjon() {
		return foretakOrganisasjon;
	}

	public void setForetakOrganisasjon(Organization foretakOrganisasjon) {
		this.foretakOrganisasjon = foretakOrganisasjon;
	}

	public void setPhHidden(boolean phHidden) {
		this.phHidden = phHidden;
	}

	public boolean isBlmailHidden() {
		return blmailHidden;
	}

	public void setBlmailHidden(boolean blmailHidden) {
		this.blmailHidden = blmailHidden;
	}

	public boolean isBlgjentaHidden() {
		return blgjentaHidden;
	}

	public void setBlgjentaHidden(boolean blgjentaHidden) {
		this.blgjentaHidden = blgjentaHidden;
	}

	public boolean isBllederHidden() {
		return bllederHidden;
	}

	public void setBllederHidden(boolean bllederHidden) {
		this.bllederHidden = bllederHidden;
	}

	public String getOnskerhjelp() {
		return Onskerhjelp;
	}
	public void setOnskerhjelp(String onskerhjelp) {
		Onskerhjelp = onskerhjelp;
	}
	public String getGjentaEpost() {
		return gjentaEpost;
	}
	public void setGjentaEpost(String gjentaEpost) {
		this.gjentaEpost = gjentaEpost;
	}
	public String getLederGjentaEpost() {
		return lederGjentaEpost;
	}
	public void setLederGjentaEpost(String lederGjentaEpost) {
		this.lederGjentaEpost = lederGjentaEpost;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public IBasismelding getMelding() {
		return melding;
	}
	public void setMelding(IBasismelding melding) {
		this.melding = melding;
	}
	public Person getLeder() {
		return leder;
	}
	public void setLeder(Person leder) {
		this.leder = leder;
	}
	
	public boolean isBlEpost() {
		return blEpost;
	}

	public void setBlEpost(boolean blEpost) {
		this.blEpost = blEpost;
	}

	public boolean isBlGjentaEpost() {
		return blGjentaEpost;
	}

	public void setBlGjentaEpost(boolean blGjentaEpost) {
		this.blGjentaEpost = blGjentaEpost;
	}

	public boolean isBlGjyldigEpost() {
		return blGjyldigEpost;
	}

	public void setBlGjyldigEpost(boolean blGjyldigEpost) {
		this.blGjyldigEpost = blGjyldigEpost;
	}

	public boolean isBlLederGjentaEpost() {
		return blLederGjentaEpost;
	}

	public void setBlLederGjentaEpost(boolean blLederGjentaEpost) {
		this.blLederGjentaEpost = blLederGjentaEpost;
	}

	public boolean isBlLederGjyldigEpost() {
		return blLederGjyldigEpost;
	}

	public void setBlLederGjyldigEpost(boolean blLederGjyldigEpost) {
		this.blLederGjyldigEpost = blLederGjyldigEpost;
	}

	public boolean isBlPhone() {
		return blPhone;
	}

	public void setBlPhone(boolean blPhone) {
		this.blPhone = blPhone;
	}

	public boolean isBlLederPhone() {
		return blLederPhone;
	}

	public void setBlLederPhone(boolean blLederPhone) {
		this.blLederPhone = blLederPhone;
	}

	public void phHiddenValue(ValueChangeEvent val){
		String strphHidden = (String)val.getNewValue();
		phHidden=true;
		if(strphHidden != null && strphHidden !=""){
			phHidden = Boolean.parseBoolean(strphHidden);
		}
		
	}

	public void emailHiddenValue(ValueChangeEvent val){
		String strmailHidden = (String)val.getNewValue();
		blmailHidden=true;
		if(strmailHidden != null && strmailHidden !=""){
			blmailHidden = Boolean.parseBoolean(strmailHidden);
		}
	}
	public void gjentaHiddenValue(ValueChangeEvent val){
		String strgjentaHidden = (String)val.getNewValue();
		blgjentaHidden=true;
		if(strgjentaHidden != null && strgjentaHidden !=""){
			blgjentaHidden = Boolean.parseBoolean(strgjentaHidden);
		}
	}

	public void lederPhHiddenValue(ValueChangeEvent val){
		String strlederHidden = (String)val.getNewValue();
		bllederHidden=true;
		if(strlederHidden != null && strlederHidden !=""){
			bllederHidden = Boolean.parseBoolean(strlederHidden);
		}
		if (melding.getNhnadresse() == null)
			melding.setNhnadresse(foretakOrganisasjon);
	}
	
	public boolean isKontaktInfoError(){
		boolean error = false;
		
		// Person email validation checking
		String epost = person.getEPost();
		blGjentaEpost = false;
		blGjyldigEpost = false;
		blEpost = false;
		
		if(epost==null || epost.trim().isEmpty() ){
			blEpost = true;
			error = true;
		}else{
			if(emailValidator(epost)){
				if(!epost.equals(gjentaEpost)){
					blGjentaEpost = true;
					error = true;
				}
			}else{
				blGjyldigEpost = true;
				error = true;
			}
		}
		
		// Leder email validation checking
		
		String lederEpost = leder.getEPost();
		blLederGjyldigEpost = false;
		blLederGjentaEpost = false;
		if(!lederEpost.equals("")){
			if(emailValidator(lederEpost)){
				if(!lederEpost.equals(lederGjentaEpost)){
					blLederGjentaEpost = true;
					error = true;
				}
			}else{
				blLederGjyldigEpost = true;
				error = true;
			}
		}else if(!lederGjentaEpost.equals("")  ){
			blLederGjentaEpost = true;
			error = true;
		}
		
		
		//person phone validation
		blPhone=false;
		if(!person.getTelefonNummer().trim().isEmpty()){
			
			if(!phoneValidator(person.getTelefonNummer())){
				error = true;
				blPhone = true ;
			}
		}
		
		// Leder phone validation
		blLederPhone = false;
		if(!leder.getTelefonNummer().trim().isEmpty()){
			
			if(!phoneValidator(leder.getTelefonNummer())){
				error = true;
				blLederPhone =  true;
			}
		}
	
		
		return error;
	}
	
	/**
	 * email validator
	 * @param epost
	 * @return boolean
	 */
	private boolean emailValidator(String epost){
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(epost);
		return matcher.matches();
	}
	
	/**
	 * phone validator function
	 * @param phNumber
	 * @return boolean
	 */
	private boolean phoneValidator(String phNumber){
		Pattern pattern = Pattern.compile("\\d{8}");
	    Matcher matcher = pattern.matcher(phNumber);
		return matcher.matches();
	}
 	
}
