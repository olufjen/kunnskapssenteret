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
		this.meldernavn = meldernavn;
	}
	public String getMelderepost() {
		return melderepost;
	}
	public void setMelderepost(String melderepost) {
		this.melderepost = melderepost;
	}
	public String getMeldertlf() {
		return meldertlf;
	}
	public void setMeldertlf(String meldertlf) {
		this.meldertlf = meldertlf;
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