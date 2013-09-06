package no.naks.web.model;

import java.io.Serializable;

import javax.faces.event.ValueChangeEvent;
import javax.xml.bind.JAXBElement;

import no.naks.emok.model.Basismelding;
import no.naks.emok.model.IBasismelding;
import no.naks.services.nhn.client.ArrayOfDepartment;
import no.naks.services.nhn.client.Department;
import no.naks.services.nhn.client.ObjectFactory;
import no.naks.services.nhn.client.Organization;

public class Hendelse implements Serializable {
	protected IBasismelding melding;
	private boolean blHendelseHidden;
	private String orgHendelse;
	
	private Department department = null;
	private transient ObjectFactory factory = null;
	private JAXBElement<String> departmentName = null;
	private ArrayOfDepartment avdelinger = null;
	private JAXBElement<ArrayOfDepartment> valgteAvdelinger;
	private Organization foretakOrganisasjon = null; // Organisasjon skapt når bruker har skrevet navnet eller valgt fra et utvalg. 
	private JAXBElement<String> organisationName = null;
	
	//Error message variables
	private boolean blOrgHendelse = false;

	public Hendelse() {
		super();
	
		 System.out.println("Hendelse created");
	
		
			
	}
	

	public Organization getForetakOrganisasjon() {
		return foretakOrganisasjon;
	}


	public void setForetakOrganisasjon(Organization foretakOrganisasjon) {
		this.foretakOrganisasjon = foretakOrganisasjon;
	}


	public IBasismelding getMelding() {
		return melding;
	}

	public void setMelding(IBasismelding melding) {
		this.melding = melding;
	}

	public boolean isBlHendelseHidden() {
		return blHendelseHidden;
	}

	public void setBlHendelseHidden(boolean blHendelseHidden) {
		this.blHendelseHidden = blHendelseHidden;
	}


	public boolean isBlOrgHendelse() {
		return blOrgHendelse;
	}


	public void setBlOrgHendelse(boolean blOrgHendelse) {
		this.blOrgHendelse = blOrgHendelse;
	}
	
	public String getOrgHendelse() {
		return orgHendelse;
	}
	public void setOrgHendelse(String orgHendelse) {
		this.orgHendelse = orgHendelse;
		melding.setHendelsesbeskrivelse(orgHendelse);
		if (melding.getNhnadresse() == null)
			melding.setNhnadresse(foretakOrganisasjon);
	}
	/**
	 * hendelseHiddenValue
	 * Denne rutinen utføres når en bruker har fylt ut første felt i hendelsessiden.
	 * Dette blir brukt for å sjekke om nødvendige felt er fylt ut, når skjema sjekkes.
	 * Er dette nødvendig med WEB-FLOW? olj Jan. 13.
	 * @param val
	 */
	public void hendelseHiddenValue(ValueChangeEvent val){
		String strHendelse =(String) val.getNewValue();
		blHendelseHidden = false;
		if(strHendelse != null && strHendelse !=""){
			blHendelseHidden=Boolean.parseBoolean(strHendelse);
		}
		setBlHendelseHidden(blHendelseHidden) ; 
	}
	
	public boolean isHendelseError(){
		boolean error = false;
		
		if(orgHendelse == null || orgHendelse.trim().isEmpty()){
			error = true;
			blOrgHendelse = true;
		}else{
			blOrgHendelse = false;
		}
		return error;
		
	}
}
