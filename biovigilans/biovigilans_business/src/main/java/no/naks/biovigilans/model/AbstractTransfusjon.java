package no.naks.biovigilans.model;
import java.util.Date;
import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * Denne klassen representerer transfusjonene som er gjort ved en gitt pasientkomplikasjonen.
 * 
 */

public abstract class AbstractTransfusjon extends AbstractModel implements Transfusjon{

	private Long transfusjonsId;
	/**
	 * Dato for transfusjonen
	 */
	private Date transfusjondato;
	/**
	 * Klokkeslett for transfusjonen
	 */
	private String transfusjonsklokkeslett;
	/**
	 * En verdi for hastegrad ved transfusjonen. Den er en av f�lgende:
	 * - Planlagt
	 * - �yeblikkelig hjelp
	 * - Vet ikke
	 */
	private String hastegrad;
	private String feiltranfudert;
	/**
	 * Dette er grunnen til at pasienten f�r en transfusjon, for eksempel jernmangelanemi, 
	 * bl�dning fra tarm� De samme betraktninger som under 
	 * Sykdom av betydning for komplikasjonen (ovenfor). M� diskuteres.
	 */
	private String indikasjon;
	/**
	 * Totalt antall poser som er benyttet ved transfusjonen.
	 */
	private int antalenheter;
/**
 * En transfunsjon kan føre til flere pasientkomplikasjoner.
*/	
	protected Map<String,Pasientkomplikasjon> pasientKomplikasjoner;
/**
 * En transfunsjon benytter flere blodprodukter.
*/		
	protected Map<String,Blodprodukt> blodProdukter;
	
/**
* Inneholder navn på input felt i skjermbildet	
*/	
	protected Map<String,String> transfusjonsFields;
	
	protected String[]keys;

	public Long getTransfusjonsId() {
		return transfusjonsId;
	}

	public void setTransfusjonsId(Long transfusjonsId) {
		this.transfusjonsId = transfusjonsId;
	}

	public Date getTransfusjondato() {
		return transfusjondato;
	}

	public void setTransfusjondato(Date transfusjondato) {
		this.transfusjondato = transfusjondato;
	}

	public String getTransfusjonsklokkeslett() {
		return transfusjonsklokkeslett;
	}

	public void setTransfusjonsklokkeslett(String transfusjonsklokkeslett) {
		this.transfusjonsklokkeslett = transfusjonsklokkeslett;
	}

	public String getHastegrad() {
		return hastegrad;
	}

	public void setHastegrad(String hastegrad) {
		this.hastegrad = hastegrad;
	}

	public String getFeiltranfudert() {
		return feiltranfudert;
	}

	public void setFeiltranfudert(String feiltranfudert) {
		this.feiltranfudert = feiltranfudert;
	}

	public String getIndikasjon() {
		return indikasjon;
	}

	public void setIndikasjon(String indikasjon) {
		this.indikasjon = indikasjon;
	}

	public int getAntalenheter() {
		return antalenheter;
	}

	public void setAntalenheter(int antalenheter) {
		this.antalenheter = antalenheter;
	}

	public Map<String, Pasientkomplikasjon> getPasientKomplikasjoner() {
		return pasientKomplikasjoner;
	}

	public void setPasientKomplikasjoner(
			Map<String, Pasientkomplikasjon> pasientKomplikasjoner) {
		this.pasientKomplikasjoner = pasientKomplikasjoner;
	}
	

	public Map<String, Blodprodukt> getBlodProdukter() {
		return blodProdukter;
	}

	public void setBlodProdukter(Map<String, Blodprodukt> blodProdukter) {
		this.blodProdukter = blodProdukter;
	}

	public Map<String, String> getTransfusjonsFields() {
		return transfusjonsFields;
	}

	public void setTransfusjonsFields(Map<String, String> transfusjonsFields) {
		this.transfusjonsFields = transfusjonsFields;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}


	
	
}