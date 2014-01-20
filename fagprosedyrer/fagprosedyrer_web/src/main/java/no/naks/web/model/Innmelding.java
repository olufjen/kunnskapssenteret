package no.naks.web.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.restlet.data.Form;
import org.restlet.data.Parameter;

/**
 * @author olj
 * For Restlet er dette representation klassen for Innmelding
 * 
 */
public class Innmelding  implements Serializable {
	private String tittel;
	private String begrunnelse;
	private Date oppstartDato;
	private Date ferdigDato;
	private String accountRef;
	private String oppstartSDato;
	private String ferdigSDato;
	private String undef;
	private String status = "";
	private String internledernavn = "";
	private String internledertittel = "";
	private String internlederepost = "";
	private String internlederinstistusjon = "";
	
	private ArrayList<String>pasientgruppe;
	private ArrayList<String>pasienterValgt;
	private ArrayList<String>helsepersonell;
	private ArrayList<String>helsepersonellValgt;
	
	private String buttonPasientvalgt = null;
	private String buttonPersonellvalgt = null;
	private String buttonPasientfjern = null;
	private String buttonPersonellfjern = null;
	
	public Innmelding() {
		super();
		tittel = "tittel";
		begrunnelse = "begrunnelse";
		oppstartDato = Calendar.getInstance().getTime();        
		ferdigDato = Calendar.getInstance().getTime();        
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		oppstartSDato = df.format(oppstartDato);
		ferdigSDato = df.format(ferdigDato)	;
		String barnogunge = "Barn og unge";
		pasientgruppe = new ArrayList<>();
		pasientgruppe.add(barnogunge);
		String eldre = "Eldre og uføre";
		pasientgruppe.add(eldre);
		pasienterValgt = new ArrayList();
		helsepersonell = new ArrayList<>();
		helsepersonellValgt = new ArrayList<>();
		String leger = "Leger";
		String sykepleier = "Sykepleier";
		helsepersonell.add(leger);
		helsepersonell.add(sykepleier);
		
	}
	/**
	 * setValues
	 * Denne rutinen setter ale verdier mottatt fra bruker.
	 * Verdier må lagres avhengig av hvilke knapper bruker har valgt
	 * @param entry
	 */
	public void setValues(Parameter entry){
		String name = entry.getName();
		String value = entry.getValue();
		switch (name)
		{
		case "tittel":
			tittel = value;
			break;
		case "begrunnelse":
			begrunnelse = value;
			break;
		case "oppstart":
			oppstartSDato = value;
			break;
		case "ferdig":
			ferdigSDato = value;
			break;
		case "pasientgruppe":
			pasienterValgt.add(value);
			break;
		case "helsepersonell":
			helsepersonellValgt.add(value);
			break;
		case "leggtilhelsepersonell":
			buttonPersonellvalgt = value;
			break;
			
		default:
			undef = value;
		}
		status = "Oppdatert";
	}
	
	public String getInternledernavn() {
		return internledernavn;
	}
	public void setInternledernavn(String internledernavn) {
		this.internledernavn = internledernavn;
	}
	public String getInternledertittel() {
		return internledertittel;
	}
	public void setInternledertittel(String internledertittel) {
		this.internledertittel = internledertittel;
	}
	public String getInternlederepost() {
		return internlederepost;
	}
	public void setInternlederepost(String internlederepost) {
		this.internlederepost = internlederepost;
	}
	public String getInternlederinstistusjon() {
		return internlederinstistusjon;
	}
	public void setInternlederinstistusjon(String internlederinstistusjon) {
		this.internlederinstistusjon = internlederinstistusjon;
	}
	public ArrayList<String> getPasienterValgt() {
		return pasienterValgt;
	}
	public void setPasienterValgt(ArrayList<String> pasienterValgt) {
		this.pasienterValgt = pasienterValgt;
	}
	public ArrayList<String> getHelsepersonell() {
		return helsepersonell;
	}
	public void setHelsepersonell(ArrayList<String> helsepersonell) {
		this.helsepersonell = helsepersonell;
	}
	public ArrayList<String> getHelsepersonellValgt() {
		return helsepersonellValgt;
	}
	public void setHelsepersonellValgt(ArrayList<String> helsepersonellValgt) {
		this.helsepersonellValgt = helsepersonellValgt;
	}
	public ArrayList<String> getPasientgruppe() {
		return pasientgruppe;
	}
	public void setPasientgruppe(ArrayList<String> pasientgruppe) {
		this.pasientgruppe = pasientgruppe;
	}
	public String getUndef() {
		return undef;
	}
	public void setUndef(String undef) {
		this.undef = undef;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOppstartSDato() {
		return oppstartSDato;
	}

	public void setOppstartSDato(String oppstartSDato) {
		this.oppstartSDato = oppstartSDato;
	}

	public String getFerdigSDato() {
		return ferdigSDato;
	}

	public void setFerdigSDato(String ferdigSDato) {
		this.ferdigSDato = ferdigSDato;
	}

	public String getAccountRef() {
		return accountRef;
	}

	public void setAccountRef(String accountRef) {
		this.accountRef = accountRef;
	}

	public String getTittel() {
		return tittel;
	}
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}
	public String getBegrunnelse() {
		return begrunnelse;
	}
	public void setBegrunnelse(String begrunnelse) {
		this.begrunnelse = begrunnelse;
	}
	public Date getOppstartDato() {
		return oppstartDato;
	}
	public void setOppstartDato(Date oppstartDato) {
		this.oppstartDato = oppstartDato;
	}
	public Date getFerdigDato() {
		return ferdigDato;
	}
	public void setFerdigDato(Date ferdigDato) {
		this.ferdigDato = ferdigDato;
	}
	
}
