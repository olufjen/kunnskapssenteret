package no.naks.biovigilans.model;

import java.util.Date;

/**
 * Tiltak
 * Dette er grenssnittet fr alle typer tiltak for pasient
 * @author oluf
 *
 */
public interface Tiltak {

	public Date getTiltaksDato();
	public void setTiltaksDato(Date tiltaksDato);
	public Date getGjennomfortDato();
	public void setGjennomfortDato(Date gjennomfortDato);
	public String getBeskrivelse();
	public void setBeskrivelse(String beskrivelse);
	public Long getPasient_id();
	public void setPasient_id(Long pasient_id);
	public Long getTiltakid();
	public void setTiltakid(Long tiltakid);
		
	public String[] getKeys();
	public void setKeys(String[] keys);
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();		

	
}
