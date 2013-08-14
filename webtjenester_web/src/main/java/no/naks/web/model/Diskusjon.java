package no.naks.web.model;

import java.io.Serializable;

import no.naks.emok.model.IBasismelding;
import no.naks.services.nhn.client.Organization;

public class Diskusjon implements Serializable {

	private String lareavHendelse;
	private IBasismelding melding;
	private Organization foretakOrganisasjon = null; // Organisasjon skapt når bruker har skrevet navnet eller valgt fra et utvalg. 
	
	public Diskusjon() {
		super();
		
	}
	public String getLareavHendelse() {
		return lareavHendelse;
	}
	
	public Organization getForetakOrganisasjon() {
		return foretakOrganisasjon;
	}
	public void setForetakOrganisasjon(Organization foretakOrganisasjon) {
		this.foretakOrganisasjon = foretakOrganisasjon;
	}
	public void setLareavHendelse(String lareavHendelse) {
		this.lareavHendelse = lareavHendelse;
		String beskrivelse = melding.getHendelsesbeskrivelse();
		melding.setHendelsesbeskrivelse(beskrivelse+" "+this.lareavHendelse);
		melding.setKanlareav(false);
		if (this.lareavHendelse != null && !this.lareavHendelse.equals("")){
			melding.setKanlareav(true);
	
		}
		if (melding.getNhnadresse() == null)
			melding.setNhnadresse(foretakOrganisasjon);
	}
	public IBasismelding getMelding() {
		return melding;
	}
	public void setMelding(IBasismelding melding) {
		this.melding = melding;
	}
	
}
