package no.naks.biovigilans.model;

import java.security.Timestamp;
import java.sql.Time;
import java.sql.Types;
import java.util.Date;
import java.util.Map;

public interface Vigilansmelding {

	public Long getMeldeid();
	public void setMeldeid(Long meldeid);
	
	public Long getMelderId();
	public void setMelderId(Long melderId);
	public Date getDatoforhendelse();
	public void setDatoforhendelse(Date datoforhendelse);
	public Time getKlokkesletthendelse();
//	public java.lang.String getKlokkesletthendelse();
	public void setKlokkesletthendelse(Time klokkesletthendelse);
	public Date getDatooppdaget();
	public void setDatooppdaget(Date datooppdaget);
	public Date getDonasjonoverforing();
	public void setDonasjonoverforing(Date donasjonoverforing);
	public java.lang.String getSjekklistesaksbehandling();
	public void setSjekklistesaksbehandling(
			java.lang.String sjekklistesaksbehandling);
	public java.lang.String getSupplerendeopplysninger();
	public void setSupplerendeopplysninger(java.lang.String supplerendeopplysninger);
	public Date getMeldingsdato();
	public void setMeldingsdato(Date meldingsdato);
	public String getKladd();
	public void setKladd(String kladd);
	public String getGodkjent();
	public void setGodkjent(String godkjent);
	
	public void setMeldingTypes();
	public void setMeldingParams();
	public int[] getTypes();
	public Object[] getParams();
	public int[] getUtypes();
	public void setMelderTypes();
	public void setMelderParams();
	//public void setVigilansmeldingfieldMaps(String[]userFields);
	public void saveToVigilansmelding();
	public String getMeldingsnokkel();
	public void setMeldingsnokkel(String meldingsnokkel) ;
	public Map<String, String> getVigilansFields() ;
	public void setVigilansFields(Map<String, String> vigilansFields) ;

}
