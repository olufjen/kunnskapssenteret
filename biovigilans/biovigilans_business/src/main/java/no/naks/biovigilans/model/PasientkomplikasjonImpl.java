package no.naks.biovigilans.model;

import java.sql.Types;



/**
 * En PasientkomplikasjonImpl er en komplikasjon som har oppstått hos pasienten som følge av en transfusjon (overføring).
 * 
 */

public class PasientkomplikasjonImpl extends AbstractVigilansmelding implements Pasientkomplikasjon,Vigilansmelding {

	/**
	 * Klassifikasjon av komplikasjon, hentes fra AbstractSykdom?
	 */
	private String klassifikasjon;
	/**
	 * Tiden (antall timerr) fra påbegynt transfusjon til at komplikasjonen oppstod.
	 */
	private int tidfrapabegynttrasfusjontilkomplikasjon;
	/**
	 * Må beskrives
	 */
	private String alvorlighetsgrad;
	/**
	 * Er det samme som utredning i skjema!?
	 */
	private String kliniskresultat;
	/**
	 * Må beskrives
	 */
	private String arsakssammenheng;
	
	
	public PasientkomplikasjonImpl() {
		super();
		types = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		utypes = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	}
	
	public void setParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{};
		}else
			params = new Object[]{getMeldeid()};
		
	}
	public String getKlassifikasjon() {
		return klassifikasjon;
	}
	public void setKlassifikasjon(String klassifikasjon) {
		this.klassifikasjon = klassifikasjon;
	}
	public int getTidfrapabegynttrasfusjontilkomplikasjon() {
		return tidfrapabegynttrasfusjontilkomplikasjon;
	}
	public void setTidfrapabegynttrasfusjontilkomplikasjon(
			int tidfrapabegynttrasfusjontilkomplikasjon) {
		this.tidfrapabegynttrasfusjontilkomplikasjon = tidfrapabegynttrasfusjontilkomplikasjon;
	}
	public String getAlvorlighetsgrad() {
		return alvorlighetsgrad;
	}
	public void setAlvorlighetsgrad(String alvorlighetsgrad) {
		this.alvorlighetsgrad = alvorlighetsgrad;
	}
	public String getKliniskresultat() {
		return kliniskresultat;
	}
	public void setKliniskresultat(String kliniskresultat) {
		this.kliniskresultat = kliniskresultat;
	}
	public String getArsakssammenheng() {
		return arsakssammenheng;
	}
	public void setArsakssammenheng(String arsakssammenheng) {
		this.arsakssammenheng = arsakssammenheng;
	}
	
}