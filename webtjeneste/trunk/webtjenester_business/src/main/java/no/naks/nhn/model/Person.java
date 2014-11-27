package no.naks.nhn.model;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;

/**
 * @author olj
 * Dette grensesnittet representer person som melder
 */
public interface Person {
	public void setNavn(String navn);
	public String getNavn();
	public String getEPost();
	public void setEPost(String post);
	public String getTelefonNummer();
	public void setTelefonNummer(String telefonNummer);
	public CS getYrke();
	public void setYrke(CS yrke);
}
