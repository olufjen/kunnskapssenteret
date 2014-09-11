package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * Denne klassen representerer Giver eller  Donor
 * N�r en giver melder seg til ny avtale, vil eventuelle tidligere komplikasjoner bli vist.
 * 
 */

public abstract class AbstractGiver extends AbstractModel implements Giver{

	protected Long giverid;
	
	protected String kjonn;
	protected String alder;
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
	
	public Long getGiverid(){
		return giverid;
	}
	public void setGiverid(Long giverid) {
		this.giverid = giverid;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		if(kjonn==null)
			kjonn = giverFields.get(keys[0]);
		this.kjonn = kjonn;
	}
	public String getAlder() {
		return alder;
	}
	public void setAlder(String alder) {
		if(alder==null)
				alder= giverFields.get(keys[1]);
		this.alder = alder;
	}
	public String getVekt() {
		return vekt;
	}
	public void setVekt(String vekt) {
		if(vekt== null)
			vekt = giverFields.get(keys[2]);
		this.vekt = vekt;
	}
	public String getGivererfaring() {
		return givererfaring;
	}
	public void setGivererfaring(String givererfaring) {
		if(givererfaring==null)
			givererfaring  = giverFields.get(keys[3]);
		this.givererfaring = givererfaring;
	}
	public String getTidligerekomlikasjonjanei() {
		return tidligerekomlikasjonjanei;
	}
	public void setTidligerekomlikasjonjanei(String tidligerekomlikasjonjanei) {
		if(tidligerekomlikasjonjanei==null)
			tidligerekomlikasjonjanei = giverFields.get(keys[4]);
		this.tidligerekomlikasjonjanei = tidligerekomlikasjonjanei;
	}
	public String getTidligerekomplikasjonforklaring() {
		return tidligerekomplikasjonforklaring;
	}
	public void setTidligerekomplikasjonforklaring(
			String tidligerekomplikasjonforklaring) {
		
		if(tidligerekomplikasjonforklaring==null)
			tidligerekomplikasjonforklaring = giverFields.get(keys[5]);
		this.tidligerekomplikasjonforklaring = tidligerekomplikasjonforklaring;
	}
	public String getGivererfaringaferese() {
		return givererfaringaferese;
	}
	public void setGivererfaringaferese(String givererfaringaferese) {
		if(givererfaringaferese==null)
			 givererfaringaferese="temp";  // give temporary value to avoid null point exception
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