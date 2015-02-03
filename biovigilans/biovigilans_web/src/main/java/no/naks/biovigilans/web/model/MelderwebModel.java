package no.naks.biovigilans.web.model;

import java.util.ArrayList;
import java.util.HashMap;
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
	private String anonymEpost = "";
	
	private String melderRegion = "";
	private String melderSykehus = "";
	private String[] helseRegioner;
	private List<String>helseRegionene; // Nedtrekk for helseregioner
	private String[] hfeneNord;
	private List<String> helseforetakNord; // Nedtrekk for helseforetak Nord
	private String[]hfeneMidt;
	private List<String> helseforetakMidt; //Nedtrekk for helseforetak Midtnorge
	private String[]hfeneVest;
	private List<String> helseforetakVest; //Nedtrekk for helseforetak helse Vest
	private String[]hfeneSor;
	private List<String> helseforetakSorost; //Nedtrekk for helseforetak sørøst
	
	private String[]sykehusHFfinnmark;
	private String[]sykehusHFNord;
	private String[]sykehusHFnordland;
	private String[]sykehusHFHelgeland;
	private String[]sykehusHFNtrond;
	private String[]sykehusHFOlav;
	private String[]sykehusHFMRoms;
	private String[]sykehusHFForde;
	private String[]sykehusHFbergen;
	private String[]sykehusHFFonna;
	private String[]sykehusHFstav;
	private String[]sykehusHFVpriv;
	private String[]sykehusHFsorland;
	private String[]sykehusHFtele;
	private String[]sykehusHFvestf;
	private String[]sykehusHFvviken;
	private String[]sykehusHFostf;
	private String[]sykehusHFoslo;
	private String[]sykehusHFahus;
	private String[]sykehusHFinnl;
	private String[]sykehusHFOpriv;
	
	private List<String> hfFinnmarksykehus;
	private List<String> hfNordsykehus;
	private List<String> hfNordlandsykehus;
	private List<String> hfHelgelandsykehus;
	
	
	private Map<String,List> regioner;
	private List valgtRegion;				// Inneholder Liste over HF i Valgt region
	private List valgtHF;					// Inneholder liste over sykehus i valgt HF
	private List<Map<String, Object>> melderInfo = null;

	public MelderwebModel(){
		super();
		melder = new MelderImpl();
		helseRegionene = new ArrayList<String>();
		helseforetakNord = new ArrayList<String>();
		helseforetakMidt = new ArrayList<String>();
		helseforetakVest = new ArrayList<String>();
		helseforetakSorost = new ArrayList<String>();
		valgtRegion = new ArrayList<String>();
		valgtHF = new ArrayList<String>();
		regioner = new HashMap<String,List>(); 
	}

	
	public String[] getSykehusHFfinnmark() {
		return sykehusHFfinnmark;
	}


	public void setSykehusHFfinnmark(String[] sykehusHFfinnmark) {
		this.sykehusHFfinnmark = sykehusHFfinnmark;
	}


	public String[] getSykehusHFNord() {
		return sykehusHFNord;
	}


	public void setSykehusHFNord(String[] sykehusHFNord) {
		this.sykehusHFNord = sykehusHFNord;
	}


	public String[] getSykehusHFnordland() {
		return sykehusHFnordland;
	}


	public void setSykehusHFnordland(String[] sykehusHFnordland) {
		this.sykehusHFnordland = sykehusHFnordland;
	}


	public String[] getSykehusHFHelgeland() {
		return sykehusHFHelgeland;
	}


	public void setSykehusHFHelgeland(String[] sykehusHFHelgeland) {
		this.sykehusHFHelgeland = sykehusHFHelgeland;
	}


	public String[] getSykehusHFNtrond() {
		return sykehusHFNtrond;
	}


	public void setSykehusHFNtrond(String[] sykehusHFNtrond) {
		this.sykehusHFNtrond = sykehusHFNtrond;
	}


	public String[] getSykehusHFOlav() {
		return sykehusHFOlav;
	}


	public void setSykehusHFOlav(String[] sykehusHFOlav) {
		this.sykehusHFOlav = sykehusHFOlav;
	}


	public String[] getSykehusHFMRoms() {
		return sykehusHFMRoms;
	}


	public void setSykehusHFMRoms(String[] sykehusHFMRoms) {
		this.sykehusHFMRoms = sykehusHFMRoms;
	}


	public String[] getSykehusHFForde() {
		return sykehusHFForde;
	}


	public void setSykehusHFForde(String[] sykehusHFForde) {
		this.sykehusHFForde = sykehusHFForde;
	}


	public String[] getSykehusHFbergen() {
		return sykehusHFbergen;
	}


	public void setSykehusHFbergen(String[] sykehusHFbergen) {
		this.sykehusHFbergen = sykehusHFbergen;
	}


	public String[] getSykehusHFFonna() {
		return sykehusHFFonna;
	}


	public void setSykehusHFFonna(String[] sykehusHFFonna) {
		this.sykehusHFFonna = sykehusHFFonna;
	}


	public String[] getSykehusHFstav() {
		return sykehusHFstav;
	}


	public void setSykehusHFstav(String[] sykehusHFstav) {
		this.sykehusHFstav = sykehusHFstav;
	}


	public String[] getSykehusHFVpriv() {
		return sykehusHFVpriv;
	}


	public void setSykehusHFVpriv(String[] sykehusHFVpriv) {
		this.sykehusHFVpriv = sykehusHFVpriv;
	}


	public String[] getSykehusHFsorland() {
		return sykehusHFsorland;
	}


	public void setSykehusHFsorland(String[] sykehusHFsorland) {
		this.sykehusHFsorland = sykehusHFsorland;
	}


	public String[] getSykehusHFtele() {
		return sykehusHFtele;
	}


	public void setSykehusHFtele(String[] sykehusHFtele) {
		this.sykehusHFtele = sykehusHFtele;
	}


	public String[] getSykehusHFvestf() {
		return sykehusHFvestf;
	}


	public void setSykehusHFvestf(String[] sykehusHFvestf) {
		this.sykehusHFvestf = sykehusHFvestf;
	}


	public String[] getSykehusHFvviken() {
		return sykehusHFvviken;
	}


	public void setSykehusHFvviken(String[] sykehusHFvviken) {
		this.sykehusHFvviken = sykehusHFvviken;
	}


	public String[] getSykehusHFostf() {
		return sykehusHFostf;
	}


	public void setSykehusHFostf(String[] sykehusHFostf) {
		this.sykehusHFostf = sykehusHFostf;
	}


	public String[] getSykehusHFoslo() {
		return sykehusHFoslo;
	}


	public void setSykehusHFoslo(String[] sykehusHFoslo) {
		this.sykehusHFoslo = sykehusHFoslo;
	}


	public String[] getSykehusHFahus() {
		return sykehusHFahus;
	}


	public void setSykehusHFahus(String[] sykehusHFahus) {
		this.sykehusHFahus = sykehusHFahus;
	}


	public String[] getSykehusHFinnl() {
		return sykehusHFinnl;
	}


	public void setSykehusHFinnl(String[] sykehusHFinnl) {
		this.sykehusHFinnl = sykehusHFinnl;
	}


	public String[] getSykehusHFOpriv() {
		return sykehusHFOpriv;
	}


	public void setSykehusHFOpriv(String[] sykehusHFOpriv) {
		this.sykehusHFOpriv = sykehusHFOpriv;
	}


	public String[] getHelseRegioner() {
		return helseRegioner;
	}


	public void setHelseRegioner(String[] helseRegioner) {
		this.helseRegioner = helseRegioner;
		for (String reg : helseRegioner){
			this.helseRegionene.add(reg);
		
		}
	}


	public List<String> getHelseRegionene() {
		return helseRegionene;
	}


	public void setHelseRegionene(List<String> helseRegionene) {
		this.helseRegionene = helseRegionene;
	}


	public String[] getHfeneNord() {
		return hfeneNord;
	}


	public void setHfeneNord(String[] hfeneNord) {
		this.hfeneNord = hfeneNord;
		for (String hf : hfeneNord){
			this.helseforetakNord.add(hf);
		}
		regioner.put(this.helseRegionene.get(0), this.helseforetakNord);
//		valgtRegion = this.helseforetakNord;
	}


	public List<String> getHelseforetakNord() {
		return helseforetakNord;
	}


	public void setHelseforetakNord(List<String> helseforetakNord) {
		this.helseforetakNord = helseforetakNord;
	}


	public String[] getHfeneMidt() {
		return hfeneMidt;
	}


	public void setHfeneMidt(String[] hfeneMidt) {
		this.hfeneMidt = hfeneMidt;
		for (String hf : hfeneMidt){
			this.helseforetakMidt.add(hf);
		}
		regioner.put(this.helseRegionene.get(1), this.helseforetakMidt);
	}


	public List<String> getHelseforetakMidt() {
		return helseforetakMidt;
	}


	public void setHelseforetakMidt(List<String> helseforetakMidt) {
		this.helseforetakMidt = helseforetakMidt;
	}


	public String[] getHfeneVest() {
		return hfeneVest;
	}


	public void setHfeneVest(String[] hfeneVest) {
		this.hfeneVest = hfeneVest;
		for (String hf : hfeneVest){
			this.helseforetakVest.add(hf);
		}
		regioner.put(this.helseRegionene.get(2), this.helseforetakVest);
	}


	public List<String> getHelseforetakVest() {
		return helseforetakVest;
	}


	public void setHelseforetakVest(List<String> helseforetakVest) {
		this.helseforetakVest = helseforetakVest;
	}


	public String[] getHfeneSor() {
		return hfeneSor;
	}


	public void setHfeneSor(String[] hfeneSor) {
		this.hfeneSor = hfeneSor;
		for (String hf : hfeneSor){
			this.helseforetakSorost.add(hf);
		}
		regioner.put(this.helseRegionene.get(3), this.helseforetakSorost);
	}


	public List<String> getHelseforetakSorost() {
		return helseforetakSorost;
	}


	public void setHelseforetakSorost(List<String> helseforetakSorost) {
		this.helseforetakSorost = helseforetakSorost;
	}


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
		if (melderepost == null && anonymEpost.equals("")){
			melderepost = "";
		}else if (!anonymEpost.equals("")) {
			melderepost = anonymEpost;
		}
		return melderepost;
	}
	public String getAnonymEpost(){
		return anonymEpost;
	}
	

	public void setAnonymEpost(String anonymEpost) {
		this.anonymEpost = anonymEpost;
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

	public Melder getMelder() {
		return melder;
	}

	public void setMelder(Melder melder) {
		this.melder = melder;
	}

	public List getValgtRegion() {
		return valgtRegion;
	}


	public void setValgtRegion(List valgtRegion) {
		this.valgtRegion = valgtRegion;
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
		
		// For å kunne vise om kontaktskjema er forhåndsutfylt Må sjekkes !!!
/*		
		this.setMeldernavn(melder.getMeldernavn());
		this.setMeldertlf(melder.getMeldertlf());
		this.setHelseregion(melder.getHelseregion());
		this.setHelseforetak(melder.getHelseforetak());
		this.setSykehus(melder.getSykehus());
*/		
		
	}
	public void saveAnonym(){
		melder.setMelderepost(melderepost);
		melder.setMeldernavn("nn");
		melder.setMeldertlf("11");
		melder.setHelseforetak("nn");
		melder.setHelseregion("nn");
		melder.setSykehus("nn");
	}
	public void kontaktValues(List<Map<String, Object>>  rows){
			melderInfo = rows;
			for(Map row:rows){
				Long id = Long.parseLong(row.get("melderid").toString());
				melder.setMelderId(id);
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
	/**
	 * setValgtregion
	 * Denne rutinen kjøres når bruker har valgt en helseregion. Da lages det en korrekt liste av HF til regionen
	 */
	public void setValgtregion(){
		setKontaktinfo();
		Map<String,String> userEntries = getFormMap();
		String field = "k-helseregion";
		melderRegion = userEntries.get(field);
		valgtRegion = null;
		valgtRegion = regioner.get(melderRegion);
	}
	
	/**
	 * setValgtsykehus
	 * Denne rutinen kjøres når bruker har valgt et HF og skal ha liste over tilgjengelige sykehus for dette HF 
	 */
	public void setValgtsykehus(){
		setKontaktinfo();
		Map<String,String> userEntries = getFormMap();
		String field = "k-helseforetak";
		melderSykehus = userEntries.get(field);
		valgtHF = null;
		
		
	}
	private void setKontaktinfo(){
		if (melderInfo != null){
			for(Map row:melderInfo){
				Long id = Long.parseLong(row.get("melderid").toString());
				melder.setMelderId(id);
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
		if (melderInfo == null){
			if (melder.getMeldernavn() != null)
				this.setMeldernavn(melder.getMeldernavn());
			if (melder.getMeldertlf() != null)
				this.setMeldertlf(melder.getMeldertlf());
			if (melder.getHelseregion() != null)
				this.setHelseregion(melder.getHelseregion());
			if (melder.getHelseforetak() != null)
				this.setHelseforetak(melder.getHelseforetak());
			if (melder.getSykehus() != null)
				this.setSykehus(melder.getSykehus());
		}
	}
}
