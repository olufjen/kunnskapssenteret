package no.naks.nhn.model;

import java.io.Serializable;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;
import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * @author olj
 * Denne klassen representerer person som melder
 * 
 */
public abstract class AbstractPerson extends AbstractModel implements Person,Serializable  {

	protected String navn;
	protected String ePost;
	protected String telefonNummer;
	protected CS yrke;
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public String getEPost() {
		return ePost;
	}
	public void setEPost(String post) {
		ePost = post;
	}
	public String getTelefonNummer() {
		return telefonNummer;
	}
	public void setTelefonNummer(String telefonNummer) {
		this.telefonNummer = telefonNummer;
	}
	public CS getYrke() {
		return yrke;
	}
	public void setYrke(CS yrke) {
		this.yrke = yrke;
	}
	
}
