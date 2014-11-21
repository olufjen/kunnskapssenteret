package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * AbstractMelder til Biovigilans har tilknytning til og jobber i en helseinstitusjon.
 * Hun melder om u�nskede hendelser eller komplikasjoner ved givning eller mottak av blod, celler og vev, eller organer. 
 * AbstractMelder har ogs� rollen som Oppf�lger.
 * 
 */

public abstract class AbstractMelder extends AbstractModel implements Melder{

	private Long melderId;
	private String meldernavn;
	private String melderepost;
	private String meldertlf;
	private String helseregion;
	private String helseforetak;
	private String sykehus;
	
	protected Map<String,String>melderFields;
	protected String[]keys;
	
	
	public Long getMelderId() {
		return melderId;
	}
	public void setMelderId(Long melderId) {
		this.melderId = melderId;
	}
	public String getMeldernavn() {
		return meldernavn;
	}
	public void setMeldernavn(String meldernavn) {
		if(meldernavn ==  null){
			meldernavn = melderFields.get(keys[3]);
		}
		this.meldernavn = meldernavn;
	}
	public String getMelderepost() {
		return melderepost;
	}
	public void setMelderepost(String melderepost) {
		if(melderepost == null){
			melderepost = melderFields.get(keys[4]) ;
		}
		this.melderepost = melderepost;
	}
	public String getMeldertlf() {
		return meldertlf;
	}
	public void setMeldertlf(String meldertlf) {
		if(meldertlf == null){
			meldertlf = melderFields.get(keys[5]);
		}
		this.meldertlf = meldertlf;
	}
	public String getHelseregion() {
		return helseregion;
	}
	public void setHelseregion(String helseregion) {
		if(helseregion == null){
			helseregion = melderFields.get(keys[0]);
		}
		this.helseregion = helseregion;
	}
	public String getHelseforetak() {
		return helseforetak;
	}
	public void setHelseforetak(String helseforetak) {
		if(helseforetak == null) {
			helseforetak = melderFields.get(keys[1]);
		}
		this.helseforetak = helseforetak;
	}
	public String getSykehus() {
		return sykehus;
	}
	public void setSykehus(String sykehus) {
		if(sykehus == null){
			sykehus = melderFields.get(keys[2]);
		}
		this.sykehus = sykehus;
	}
	public Map<String, String> getMelderFields() {
		return melderFields;
	}
	public void setMelderFields(Map<String, String> melderFields) {
		this.melderFields = melderFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
}