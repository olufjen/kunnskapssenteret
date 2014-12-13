package no.naks.biovigilans.web.model;

import java.util.Map;

import no.naks.biovigilans.model.AbstractVigilansmelding;
import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.GiverImpl;
import no.naks.biovigilans.model.Giverkomplikasjon;
import no.naks.biovigilans.model.GiverkomplikasjonImpl;
import no.naks.biovigilans.model.Giveroppfolging;
import no.naks.biovigilans.model.GiveroppfolgingImpl;
import no.naks.biovigilans.model.Vigilansmelding;


public class GiverKomplikasjonwebModel extends VigilansModel {
	private Giver giver;
	private Vigilansmelding vigilansmelding;
	private Giverkomplikasjon giverKomplikasjon;
	private Giveroppfolging giveroppfolging;
	
	private String[] reaksjonengruppe; 
	private String[] utenforBlodbankengruppe;
	private String[] donasjonsstedgruppe;
	protected String[] systemiskgruppe;
	protected String[] skadeiarmen;
	protected String[] sykemeldinggruppe;
	
	public GiverKomplikasjonwebModel() {
		super();
		giver = new GiverImpl();
		vigilansmelding = new AbstractVigilansmelding();
		giverKomplikasjon = new GiverkomplikasjonImpl();
		giveroppfolging = new GiveroppfolgingImpl();
	//	giver.setGiverfieldMaps(userFields);
		
	}
	
	

	public String[] getSykemeldinggruppe() {
		return sykemeldinggruppe;
	}
	public void setSykemeldinggruppe(String[] sykemeldinggruppe) {
		this.sykemeldinggruppe = sykemeldinggruppe;
	}
	public String[] getSystemiskgruppe() {
		return systemiskgruppe;
	}
	public void setSystemiskgruppe(String[] systemiskgruppe) {
		this.systemiskgruppe = systemiskgruppe;
	}
	public String[] getSkadeiarmen() {
		return skadeiarmen;
	}
	public void setSkadeiarmen(String[] skadeiarmen) {
		this.skadeiarmen = skadeiarmen;
	}
	public Vigilansmelding getVigilansmelding() {
		return vigilansmelding;
	}
	public void setVigilansmelding(Vigilansmelding vigilansmelding) {
		this.vigilansmelding = vigilansmelding;
	}
	public Giver getGiver() {
		return giver;
	}
	public void setGiver(Giver giver) {
		this.giver = giver;
	}

	public Giverkomplikasjon getGiverKomplikasjon() {
		return giverKomplikasjon;
	}
	public void setGiverKomplikasjon(Giverkomplikasjon giverKomplikasjon) {
		this.giverKomplikasjon = giverKomplikasjon;
	}

	public Giveroppfolging getGiveroppfolging() {
		return giveroppfolging;
	}
	public void setGiveroppfolging(Giveroppfolging giveroppfolging) {
		this.giveroppfolging = giveroppfolging;
	}



	private String[] aldergruppe;

	public String[] getAldergruppe() {
		return aldergruppe;
	}

	public void setAldergruppe(String[] aldergruppe) {
		this.aldergruppe = aldergruppe;
	}
	
	public String[] getReaksjonengruppe() {
		return reaksjonengruppe;
	}
	public void setReaksjonengruppe(String[] reaksjonengruppe) {
		this.reaksjonengruppe = reaksjonengruppe;
	}



	public String[] getUtenforBlodbankengruppe() {
		return utenforBlodbankengruppe;
	}



	public void setUtenforBlodbankengruppe(String[] utenforBlodbankengruppe) {
		this.utenforBlodbankengruppe = utenforBlodbankengruppe;
	}


	
	public String[] getDonasjonsstedgruppe() {
		return donasjonsstedgruppe;
	}



	public void setDonasjonsstedgruppe(String[] donasjonsstedgruppe) {
		this.donasjonsstedgruppe = donasjonsstedgruppe;
	}



	public void distributeTerms(){
			String[] formFields = getFormNames();
			String giverFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5],formFields[11]};
			giver.setGiverfieldMaps(giverFields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			giver.saveField(field, userEntry);
			giverKomplikasjon.saveField(field, userEntry);
			giveroppfolging.saveField(field, userEntry);
		}
		
		giver.saveToGiver();
		vigilansmelding.saveToVigilansmelding();
		giverKomplikasjon.saveToGiverkomplikasjon();
		giveroppfolging.saveToField();
		
	}
	
	
	public void meldingDistributeTerms(){
		/*
		String[] formFields = getFormNames();
		String meldingFields[] = {formFields[6],formFields[7],formFields[8],formFields[9],formFields[10],formFields[12],formFields[13]};
		vigilansmelding.setVigilansmeldingfieldMaps(meldingFields);
		*/
	}
	
	public void giverKomplikasjonDistribute(){
		String[] formFields = getFormNames();
		String meldingFields[] = {formFields[13],formFields[14],formFields[15],formFields[33],formFields[17],formFields[18],formFields[19],formFields[20],formFields[21],formFields[34],formFields[35]};
		giverKomplikasjon.setGiverkomplicationfieldMaps(meldingFields);
	}
	
	public void giveroppfolgingDistribute(){
		String[] formFields = getFormNames();
		String giveroppfolgingFields[]={formFields[22],formFields[16],formFields[32],formFields[31],formFields[37]};
		giveroppfolging.setGiveroppfolgingfieldMaps(giveroppfolgingFields);
	}
	
}
