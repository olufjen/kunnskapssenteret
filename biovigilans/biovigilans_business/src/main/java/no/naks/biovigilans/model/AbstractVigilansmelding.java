package no.naks.biovigilans.model;


import java.security.Timestamp;
import java.sql.Time;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;



import org.apache.taglibs.standard.tag.common.fmt.ParseDateSupport;

import no.naks.rammeverk.kildelag.model.AbstractModel;



/**
 * En AbstractVigilansmelding er en melding om uønskede hendelser eller komplikasjoner ved blodoverføringer, celler og vev, eller organsdonasjoner. 
 * En AbstractVigilansmelding er enten en Giverkomplikasjon eller en PasientkomplikasjonImpl.
 * 
 */

public class AbstractVigilansmelding extends AbstractModel implements Vigilansmelding{

	/**
	 * Id til meldingen
	 */
	private Long meldeid;
	/**
	 * Dato for hendelsen
	 */
	protected String[]keys;
	
	private Date datoforhendelse;
	/**
	 * Klokkeslett for hendelsen
	 */
	private Time klokkesletthendelse;
	/**
	 * Dato når komplikasjonen ble oppdaget
	 */
	private Date datooppdaget;
	/**
	 * Tidspunkt for donasjon eller overføring
	 */
	private Date donasjonoverforing;
	/**
	 * Denne sjekklisten omfatter følgende definisjoner:
	 * Skal meldes videre til HDIR
	 * Skal diskuteres på neste møte
	 * Egnet som eksempler i rapport
	 * Egnet som oppgave på seminar
	 * Trenger ytterligere opplysninger
	 * Trenger å følges opp
	 * Ferdig behandlet
	 */
	private String sjekklistesaksbehandling;
	/**
	 * Eventuelle supplerende opplysninger ved meldingen.
	 */
	private String supplerendeopplysninger;
	/**
	 * Dato meldingen er mottatt
	 */
	private Date meldingsdato;

	private String meldingsnokkel;
	
	protected String[]vigilansKeys;	
	protected Map<String,String>vigilansFields;


	public Long getMeldeid() {
		return meldeid;
	}
	public void setMeldeid(Long meldeid) {
		this.meldeid = meldeid;
	}
	public Date getDatoforhendelse() {
		return datoforhendelse;
	}
	public void setDatoforhendelse(Date datoforhendelse) {
		if(datoforhendelse==null){
			DateFormat dateFormat = 
			            new SimpleDateFormat("yyyy-MM-dd");
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				String strDate = dateFormat.format(date);
				datoforhendelse =   dateFormat.parse(strDate);
			} catch (ParseException e) {
				System.out.println("date format problem: " + e.toString());
			}
		}
		this.datoforhendelse = datoforhendelse;
	}
	
	public Time getKlokkesletthendelse() {
		return klokkesletthendelse;
	}
	public void setKlokkesletthendelse(Time klokkesletthendelse) {
		if(klokkesletthendelse==null){
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			Date date = new Date();
			try {
				Time time = Time.valueOf(dateFormat.format(date));
				System.out.print(time);
				klokkesletthendelse=time;
				
			} catch (Exception e) {
				System.out.println("date format problem: " + e.toString());
			}
		}
		this.klokkesletthendelse = klokkesletthendelse;
	}
	public Date getDatooppdaget() {
		return datooppdaget;
	}
	public void setDatooppdaget(Date datooppdaget) {
		if(datooppdaget==null){
			DateFormat dateFormat = 
			            new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				String strDate = dateFormat.format(date);
				datooppdaget =   dateFormat.parse(strDate);
			} catch (ParseException e) {
				System.out.println("date format problem: " + e.toString());
			}
		}
		this.datooppdaget = datooppdaget;
	}
	public Date getDonasjonoverforing() {
		return donasjonoverforing;
	}
	public void setDonasjonoverforing(Date donasjonoverforing) {
		if(donasjonoverforing==null){
			DateFormat dateFormat = 
			            new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				String strDate = dateFormat.format(date);
				donasjonoverforing =   dateFormat.parse(strDate);
			} catch (ParseException e) {
				System.out.println("date format problem: " + e.toString());
			}
		}
		this.donasjonoverforing = donasjonoverforing;
	}
	public String getSjekklistesaksbehandling() {
		return sjekklistesaksbehandling;
	}
	public void setSjekklistesaksbehandling(
			String sjekklistesaksbehandling) {
		this.sjekklistesaksbehandling = sjekklistesaksbehandling;
	}
	public String getSupplerendeopplysninger() {
		return supplerendeopplysninger;
	}
	public void setSupplerendeopplysninger(String supplerendeopplysninger) {
		this.supplerendeopplysninger = supplerendeopplysninger;
	}
	public Date getMeldingsdato() {
		return meldingsdato;
	}
	
	public String getMeldingsnokkel() {
		return meldingsnokkel;
	}
	public void setMeldingsnokkel(String meldingsnokkel) {
		if(meldingsnokkel == null){
			meldingsnokkel = "Hem " + getMeldeid() + " " + getMeldingsdato() ;
		}
		this.meldingsnokkel = meldingsnokkel;
	}
	public void setMeldingsdato(Date meldingsdato) {
		if(meldingsdato==null){
			 SimpleDateFormat dateFormat = 
			            new SimpleDateFormat("yyyy/MM/dd");
		//	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				String strDate = dateFormat.format(date);
				meldingsdato =   dateFormat.parse(strDate);
			} catch (ParseException e) {
				System.out.println("date format problem: " + e.toString());
			}
		}
		this.meldingsdato = meldingsdato;
	}
	
	public void setMeldingTypes(){
		types = new int[] {Types.DATE,Types.TIME,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE};
		utypes = new int[] {Types.DATE,Types.TIME,Types.DATE,Types.DATE,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.INTEGER};
	}
	public void setMeldingParams(){
		Long id = getMeldeid();
		if (id == null){
			params = new Object[]{getDatoforhendelse(),getKlokkesletthendelse(),getDatooppdaget(),getDonasjonoverforing(),getSjekklistesaksbehandling(),getSupplerendeopplysninger(),getMeldingsdato()};
		}else
			params = new Object[]{getDatoforhendelse(),getKlokkesletthendelse(),getDatooppdaget(),getDonasjonoverforing(),getSjekklistesaksbehandling(),getSupplerendeopplysninger(),getMeldingsdato(),getMeldingsnokkel(),getMeldeid()};
	}
	
	/**
	 * setVigilansmeldingfieldMaps
	 * Denne rutinen setter opp hvilke skjermbildefelter som hører til hvilke databasefelter
	 * @param userFields En liste over skjermbildefelter
	 */
	public void setVigilansmeldingfieldMaps(String[]userFields){
		keys = userFields;
		int size =userFields.length;
		for (int i = 0;i<size;i++){
			vigilansFields.put(userFields[i],null);
		}
	}
	
	/**
	 * saveField
	 * Denne rutinen lagrer skjermbildefelter til riktig databasefelt
	 * @param userField
	 * @param userValue
	 */
	public void saveField(String userField, String userValue) {
		if (vigilansFields.containsKey(userField) && userValue != null && !userValue.equals("")){
			vigilansFields.put(userField,userValue);	
	
		}
		
	}

	public void saveToVigilansmelding(){
		setDatoforhendelse(null);
		setKlokkesletthendelse(null);
		setDatooppdaget(null);
		setDonasjonoverforing(null);
		setMeldingsdato(null);
	}

	
}