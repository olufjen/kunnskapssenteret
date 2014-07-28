package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer Giver eller  Donor
 * N�r en giver melder seg til ny avtale, vil eventuelle tidligere komplikasjoner bli vist.
 * 
 */

public abstract class AbstractGiver extends AbstractModel implements Giver{

	private long giverid;
	
	private String kjonn;
	private String alder;
	/**
	 * Vekt angitt i kilo
	 */
	private String vekt;
	/**
	 * Om giver er giver for første gang
	 */
	private String givererfaring;
	/**
	 * Om giver har hatt komplikasjoner tidligere (Ja/Nei)
	 */
	private String tidligerekomlikasjonjanei;
	/**
	 * Dersom ja må det være en forklaring
	 */
	private String tidligerekomplikasjonforklaring;
	/**
	 * Aferese er en egen måte å tappe blod på.
	 * Er dette første gang.
	 */
	private String givererfaringaferese;
	
	protected String[]keys;
	protected Map<String,String> giverFields;
	
	
	public long getGiverid() {
		return giverid;
	}
	public void setGiverid(long giverid) {
		this.giverid = giverid;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public String getAlder() {
		return alder;
	}
	public void setAlder(String alder) {
		this.alder = alder;
	}
	public String getVekt() {
		return vekt;
	}
	public void setVekt(String vekt) {
		this.vekt = vekt;
	}
	public String getGivererfaring() {
		return givererfaring;
	}
	public void setGivererfaring(String givererfaring) {
		this.givererfaring = givererfaring;
	}
	public String getTidligerekomlikasjonjanei() {
		return tidligerekomlikasjonjanei;
	}
	public void setTidligerekomlikasjonjanei(String tidligerekomlikasjonjanei) {
		this.tidligerekomlikasjonjanei = tidligerekomlikasjonjanei;
	}
	public String getTidligerekomplikasjonforklaring() {
		return tidligerekomplikasjonforklaring;
	}
	public void setTidligerekomplikasjonforklaring(
			String tidligerekomplikasjonforklaring) {
		this.tidligerekomplikasjonforklaring = tidligerekomplikasjonforklaring;
	}
	public String getGivererfaringaferese() {
		return givererfaringaferese;
	}
	public void setGivererfaringaferese(String givererfaringaferese) {
		this.givererfaringaferese = givererfaringaferese;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public Map<String, String> getGiverFields() {
		return giverFields;
	}
	public void setGiverFields(Map<String, String> giverFields) {
		this.giverFields = giverFields;
	}

	
}