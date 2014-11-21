package no.naks.biovigilans.web.model;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.Melder;
import no.naks.biovigilans.model.MelderImpl;

public class MelderwebModel extends VigilansModel {
	
	private Melder melder;
	
	private String meldernavn ="" ;
	private String melderepost ="";
	private String meldertlf ="";
	private String helseregion ="";
	private String helseforetak ="";
	private String sykehus="";
	
	
	
	public String getMeldernavn() {
		return meldernavn;
	}

	public void setMeldernavn(String meldernavn) {
		this.meldernavn = meldernavn;
	}

	public String getMelderepost() {
		Map<String,String> userEntries = getFormMap();
		String field = "k-epost";
		melderepost = userEntries.get(field);
		if (melderepost == null){
			melderepost = "";
		}
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

	public String getHelseregion() {
		return helseregion;
	}

	public void setHelseregion(String helseregion) {
		this.helseregion = helseregion;
	}

	public String getHelseforetak() {
		return helseforetak;
	}

	public void setHelseforetak(String helseforetak) {
		this.helseforetak = helseforetak;
	}

	public String getSykehus() {
		return sykehus;
	}

	public void setSykehus(String sykehus) {
		this.sykehus = sykehus;
	}

	public MelderwebModel(){
		super();
		melder = new MelderImpl();
	}

	public Melder getMelder() {
		return melder;
	}

	public void setMelder(Melder melder) {
		this.melder = melder;
	}

	public void distributeTerms(){
		String[] formFields = getFormNames();
		String melderFields[] = {formFields[0],formFields[1],formFields[2],formFields[3],formFields[4],formFields[5]};
		melder.setMelderfieldMaps(melderFields);
	}
	
	public void saveValues(){
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap(); // formMap inneholder verdier angitt av bruker
		
		for(String field: formFields ){
			String userEntry = userEntries.get(field);
			melder.saveField(field, userEntry);
		}
		
		melder.saveToMelder();
	}
	
	public void kontaktValues(List<Map<String, Object>>  rows){
			for(Map row:rows){
				String name = row.get("meldernavn").toString();
				this.setMeldernavn(name);
				String tlf = row.get("meldertlf").toString();
				this.setMeldertlf(tlf);
				String helseregion = row.get("helseregion").toString();
				this.setHelseregion(helseregion);
				String helseforetak = row.get("helseforetak").toString();
				this.setHelseforetak(helseforetak);
				String sykehus = row.get("sykehus").toString();
				this.setSykehus(sykehus);
			}
	}
	
}
