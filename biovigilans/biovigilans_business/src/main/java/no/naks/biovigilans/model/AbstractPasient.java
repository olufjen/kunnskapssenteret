package no.naks.biovigilans.model;

import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * Denne klassen representerer pasienten i pasientkomplikasjoner
 * @author olj
 *
 */
public abstract class AbstractPasient extends AbstractModel implements Pasient{

	protected Long pasient_Id;
	protected String kjonn;
	protected String aldersGruppe;
	protected String antiStoff;
	protected String inneliggendePoli;
	protected String avdeling;
	

	public Long getPasient_Id() {
		return pasient_Id;
	}
	public void setPasient_Id(Long pasient_Id) {
		this.pasient_Id = pasient_Id;
	}
	public String getKjonn() {
		return kjonn;
	}
	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	public String getAldersGruppe() {
		return aldersGruppe;
	}
	public void setAldersGruppe(String aldersGruppe) {
		this.aldersGruppe = aldersGruppe;
	}
	public String getAntiStoff() {
		return antiStoff;
	}
	public void setAntiStoff(String antiStoff) {
		this.antiStoff = antiStoff;
	}
	public String getInneliggendePoli() {
		return inneliggendePoli;
	}
	public void setInneliggendePoli(String inneliggendePoli) {
		this.inneliggendePoli = inneliggendePoli;
	}
	public String getAvdeling() {
		return avdeling;
	}
	public void setAvdeling(String avdeling) {
		this.avdeling = avdeling;
	}
	
}
