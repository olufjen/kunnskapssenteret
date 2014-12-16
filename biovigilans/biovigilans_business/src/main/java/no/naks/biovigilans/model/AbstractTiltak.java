package no.naks.biovigilans.model;

import java.util.Date;
import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;

/**
 * Tiltak rettet mot pasient
 * @author olj
 *
 */
public class AbstractTiltak extends AbstractModel  {
	
	private Date tiltaksDato;
	private Date gjennomfortDato;
	private String beskrivelse;
	private Long pasient_id;
	private Long tiltakid;
	protected Map<String,Tiltak> forebyggendeTiltak;
	
	public Date getTiltaksDato() {
		return tiltaksDato;
	}
	public void setTiltaksDato(Date tiltaksDato) {
		this.tiltaksDato = tiltaksDato;
	}
	public Date getGjennomfortDato() {
		return gjennomfortDato;
	}
	public void setGjennomfortDato(Date gjennomfortDato) {
		this.gjennomfortDato = gjennomfortDato;
	}
	public String getBeskrivelse() {
		return beskrivelse;
	}
	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	public Long getPasient_id() {
		return pasient_id;
	}
	public void setPasient_id(Long pasient_id) {
		this.pasient_id = pasient_id;
	}
	public Long getTiltakid() {
		return tiltakid;
	}
	public void setTiltakid(Long tiltakid) {
		this.tiltakid = tiltakid;
	}

	

}
