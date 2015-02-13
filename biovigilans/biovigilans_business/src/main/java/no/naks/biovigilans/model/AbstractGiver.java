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
	protected Map formMap; // Inneholder brukers input verdier fra skjermbildet
	
	protected String kjonn;
	protected String alder;
	/**
	 * Vekt angitt i kilo
	 */
	private Long vekt;
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
		Map<String,String> userEntries = getFormMap();
		String field = "tab-kjonn";
		kjonn = userEntries.get(field);
		if (kjonn == null){
			kjonn = "";
		}
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		/*if(kjonn==null){
			kjonn =(giverFields.get(keys[0])==null ? "" : giverFields.get(keys[0])) ;
		}*/
		this.kjonn = kjonn;
	}
	public String getAlder() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-alder";
		alder = userEntries.get(field);
		if (alder == null){
			alder = "";
		}
		return alder;
	}
	public void setAlder(String alder) {
		/*if(alder==null)
			alder = (giverFields.get(keys[1])==null ? "" : giverFields.get(keys[1])) ;
		if(alder.equalsIgnoreCase("--- Select ---"))
			alder ="";*/
		this.alder = alder;
	}
	public Long getVekt() {
		Map<String,String> userEntries = getFormMap();
		String field = "givervektkg";
		String val = userEntries.get(field);
		if(val != null){
			vekt = Long.parseLong(val);
		}else{
			vekt = (long) 0;
		}
		return vekt;
	}
	public void setVekt(Long vekt) {
		/*if(vekt== null)
			vekt = giverFields.get(keys[2]);*/
		this.vekt = vekt;
	}
	public String getGivererfaring() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-givererfaring";
		givererfaring = userEntries.get(field);
		if (givererfaring == null){
			givererfaring = "-";
		}	
		return givererfaring;
	}
	public void setGivererfaring(String givererfaring) {
		/*if(givererfaring==null)
			givererfaring  = giverFields.get(keys[3]);*/
		this.givererfaring = givererfaring;
	}
	public String getTidligerekomlikasjonjanei() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-tidl-tapp";
		tidligerekomlikasjonjanei = userEntries.get(field);
		if (tidligerekomlikasjonjanei == null){
			tidligerekomlikasjonjanei = "";
		}
		return tidligerekomlikasjonjanei;
	}
	public void setTidligerekomlikasjonjanei(String tidligerekomlikasjonjanei) {
		/*if(tidligerekomlikasjonjanei==null)
			tidligerekomlikasjonjanei = giverFields.get(keys[4]);*/
		this.tidligerekomlikasjonjanei = tidligerekomlikasjonjanei;
	}
	public String getTidligerekomplikasjonforklaring() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-reaksjon-beskriv";
		tidligerekomplikasjonforklaring = userEntries.get(field);
		if (tidligerekomplikasjonforklaring == null){
			tidligerekomplikasjonforklaring = "";
		}
		return tidligerekomplikasjonforklaring;
	}
	public void setTidligerekomplikasjonforklaring(
			String tidligerekomplikasjonforklaring) {
		
		/*if(tidligerekomplikasjonforklaring==null)
			tidligerekomplikasjonforklaring = giverFields.get(keys[5]);*/
		this.tidligerekomplikasjonforklaring = tidligerekomplikasjonforklaring;
	}
	public String getGivererfaringaferese() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-sted";
		givererfaringaferese = userEntries.get(field);
		if (givererfaringaferese == null || givererfaringaferese.equalsIgnoreCase("--- Select ---")){
			givererfaringaferese = "";
		}
		return givererfaringaferese;
	}
	public void setGivererfaringaferese(String givererfaringaferese) {
		/*if(givererfaringaferese==null)
			 givererfaringaferese=giverFields.get(keys[6]);  */
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
	public Map getFormMap() {
		return formMap;
	}
	public void setFormMap(Map formMap) {
		this.formMap = formMap;
	}

	
}