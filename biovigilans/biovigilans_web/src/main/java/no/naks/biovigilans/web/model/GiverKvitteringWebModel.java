package no.naks.biovigilans.web.model;

import java.util.Map;

public class GiverKvitteringWebModel extends VigilansModel {
	
	private String kjonn;
	private String alder;
	private String givervektkg;
	private String givererfaring;
	private String tapping;
	private String tidlTapp;
	private String reaksjonBeskriv;
	private String datoDonasjon;
	private String donasjonSted;
	private String maaltid;
	private String tabVene;
	private String formControl;
	private String tabHvor;
	private String datoHendelse;
	private String utenForBlodbanken;
	private String tappReak;
	private String tabVarighet;
	private String alvorMang;
	private String tabKlinisk;
	private String tabArm;
	private String tabskadearm;
	private String tabBivirk;
	private String systemiskbivirkning;
	private String annenreaksjon;
	private String annenreaksjonBeskriv;
	private String strakstiltak;
	private String forbedringstiltak;
	private String forebyggbar;
	private String annenreak;
	private String avreg;
	
	
	
	public String getAvreg() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-avreg";
		avreg = userEntries.get(field);
		if (avreg == null){
			avreg = "-";
		}
		return avreg;
	}
	public void setAvreg(String avreg) {
		this.avreg = avreg;
	}
	
	public String getAnnenreak() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-annenreak";
		annenreak = userEntries.get(field);
		if (annenreak == null){
			annenreak = "-";
		}
		return annenreak;
	}
	public void setAnnenreak(String annenreak) {
		this.annenreak = annenreak;
	}
	
	public String getForebyggbar() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-forebyggbar";
		forebyggbar = userEntries.get(field);
		if (forebyggbar == null){
			forebyggbar = "-";
		}
		return forebyggbar;
	}
	public void setForebyggbar(String forebyggbar) {
		this.forebyggbar = forebyggbar;
	}
	public String getForbedringstiltak() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-forbedringstiltak";
		forbedringstiltak = userEntries.get(field);
		if (forbedringstiltak == null){
			forbedringstiltak = "-";
		}
		return forbedringstiltak;
	}
	public void setForbedringstiltak(String forbedringstiltak) {
		this.forbedringstiltak = forbedringstiltak;
	}
	public String getStrakstiltak() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-strakstiltak";
		strakstiltak = userEntries.get(field);
		if (strakstiltak == null){
			strakstiltak = "-";
		}
		return strakstiltak;
	}
	public void setStrakstiltak(String strakstiltak) {
		this.strakstiltak = strakstiltak;
	}
	
	public String getAnnenreaksjonBeskriv() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-annet-beskriv";
		annenreaksjonBeskriv = userEntries.get(field);
		if (annenreaksjonBeskriv == null){
			annenreaksjonBeskriv = "-";
		}
		return annenreaksjonBeskriv;
	}
	public void setAnnenreaksjonBeskriv(String annenreaksjonBeskriv) {
		this.annenreaksjonBeskriv = annenreaksjonBeskriv;
	}
	public String getAnnenreaksjon() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-annenreaksjon";
		annenreaksjon = userEntries.get(field);
		if (annenreaksjon == null){
			annenreaksjon = "-";
		}
		return annenreaksjon;
	}
	public void setAnnenreaksjon(String annenreaksjon) {
		this.annenreaksjon = annenreaksjon;
	}
	
	public String getSystemiskbivirkning() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-systemiskbivirkning";
		systemiskbivirkning = userEntries.get(field);
		if (systemiskbivirkning == null){
			systemiskbivirkning = "-";
		}
		return systemiskbivirkning;
	}
	public void setSystemiskbivirkning(String systemiskbivirkning) {
		this.systemiskbivirkning = systemiskbivirkning;
	}
	public String getTabBivirk() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-bivirk";
		tabBivirk = userEntries.get(field);
		if (tabBivirk == null){
			tabBivirk = "-";
		}
		return tabBivirk;
	}
	public void setTabBivirk(String tabBivirk) {
		this.tabBivirk = tabBivirk;
	}
	
	public String getTabskadearm() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-tabskadearm";
		tabskadearm = userEntries.get(field);
		if (tabskadearm == null){
			tabskadearm = "-";
		}
		return tabskadearm;
	}
	public void setTabskadearm(String tabskadearm) {
		this.tabskadearm = tabskadearm;
	}
	
	public String getTabArm() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-arm";
		tabArm = userEntries.get(field);
		if (tabArm == null){
			tabArm = "-";
		}
		return tabArm;
	}
	public void setTabArm(String tabArm) {
		this.tabArm = tabArm;
	}
	
	public String getTabKlinisk() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-klinisk";
		tabKlinisk = userEntries.get(field);
		if (tabKlinisk == null){
			tabKlinisk = "-";
		}
		return tabKlinisk;
	}
	public void setTabKlinisk(String tabKlinisk) {
		this.tabKlinisk = tabKlinisk;
	}
	
	public String getAlvorMang() {
		Map<String,String> userEntries = getFormMap();
		String field = "alvor-mang";
		alvorMang = userEntries.get(field);
		if (alvorMang == null){
			alvorMang = "-";
		}
		return alvorMang;
	}
	public void setAlvorMang(String alvorMang) {
		this.alvorMang = alvorMang;
	}
	public String getTabVarighet() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-varighet";
		tabVarighet = userEntries.get(field);
		if (tabVarighet == null){
			tabVarighet = "-";
		}
		return tabVarighet;
	}
	public void setTabVarighet(String tabVarighet) {
		this.tabVarighet = tabVarighet;
	}
	
	public String getTappReak() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-tapp-reak";
		tappReak = userEntries.get(field);
		if (tappReak == null){
			tappReak = "-";
		}
		return tappReak;
	}
	public void setTappReak(String tappReak) {
		this.tappReak = tappReak;
	}
	
	public String getUtenForBlodbanken() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-utenForBlodbanken";
		utenForBlodbanken = userEntries.get(field);
		if (utenForBlodbanken == null){
			utenForBlodbanken = "-";
		}
		return utenForBlodbanken;
	}
	public void setUtenForBlodbanken(String utenForBlodbanken) {
		this.utenForBlodbanken = utenForBlodbanken;
	}
	
	public String getDatoHendelse() {
		Map<String,String> userEntries = getFormMap();
		String field = "dato-hendelse";
		datoHendelse = userEntries.get(field);
		if (datoHendelse == null){
			datoHendelse = "-";
		}
		return datoHendelse;
	}
	public void setDatoHendelse(String datoHendelse) {
		this.datoHendelse = datoHendelse;
	}
	public String getTabHvor() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-hvor";
		tabHvor = userEntries.get(field);
		if (tabHvor == null){
			tabHvor = "-";
		}
		return tabHvor;
	}
	public void setTabHvor(String tabHvor) {
		this.tabHvor = tabHvor;
	}
	public String getFormControl() {
		Map<String,String> userEntries = getFormMap();
		String field = "form-control";
		formControl = userEntries.get(field);
		if (formControl == null){
			formControl = "-";
		}
		return formControl;
	}
	public void setFormControl(String formControl) {
		this.formControl = formControl;
	}
	public String getTabVene() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-vene";
		tabVene = userEntries.get(field);
		if (tabVene == null){
			tabVene = "-";
		}
		return tabVene;
	}
	public void setTabVene(String tabVene) {
		this.tabVene = tabVene;
	}
	public String getMaaltid() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-maltid";
		maaltid = userEntries.get(field);
		if (maaltid == null){
			maaltid = "-";
		}
		return maaltid;
	}
	public void setMaaltid(String maaltid) {
		this.maaltid = maaltid;
	}
	
	public String getDonasjonSted() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-sted";
		donasjonSted = userEntries.get(field);
		if (donasjonSted == null){
			donasjonSted = "-";
		}
		return donasjonSted;
	}
	public void setDonasjonSted(String donasjonSted) {
		this.donasjonSted = donasjonSted;
	}
	
	public String getDatoDonasjon() {
		Map<String,String> userEntries = getFormMap();
		String field = "dato-donasjon";
		datoDonasjon = userEntries.get(field);
		if (datoDonasjon == null){
			datoDonasjon = "-";
		}
		return datoDonasjon;
	}
	public void setDatoDonasjon(String datoDonasjon) {
		this.datoDonasjon = datoDonasjon;
	}
	
	public String getReaksjonBeskriv() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-reaksjon-beskriv";
		reaksjonBeskriv = userEntries.get(field);
		if (reaksjonBeskriv == null){
			reaksjonBeskriv = "-";
		}
		return reaksjonBeskriv;
	}
	public void setReaksjonBeskriv(String reaksjonBeskriv) {
		this.reaksjonBeskriv = reaksjonBeskriv;
	}
	
	public String getTidlTapp() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-tidl-tapp";
		tidlTapp = userEntries.get(field);
		if (tidlTapp == null){
			tidlTapp = "-";
		}
		return tidlTapp;
	}
	public void setTidlTapp(String tidlTapp) {
		this.tidlTapp = tidlTapp;
	}
	
	public String getTapping() {
		Map<String,String> userEntries = getFormMap();
		String field = "type-tapping";
		tapping = userEntries.get(field);
		if (tapping == null){
			tapping = "-";
		}else if(tapping.equalsIgnoreCase("fullblod"))	{
			String fullblodOption = userEntries.get("fullblod");
			if(fullblodOption.equalsIgnoreCase("fullblod-ja")){
				tapping = "Fullblod-Ja";
			}else{
				tapping = "Fullblod-Nei";
			}
		}else if(tapping.equalsIgnoreCase("aferese"))	{
			String fullblodOption = userEntries.get("aferese-options");
			if(fullblodOption.equalsIgnoreCase("aferese-ja")){
				tapping = "Aferese-Ja";
			}else{
				tapping = "Aferese-Nei";
			}
		}
		return tapping;
	}
	public void setTapping(String tapping) {
		this.tapping = tapping;
	}

	public String getAlder() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-alder";
		alder = userEntries.get(field);
		if (alder == null){
			alder = "-";
		}	
		return alder;
	}
	
	
	

	public String getGivervektkg() {
		Map<String,String> userEntries = getFormMap();
		String field = "givervektkg";
		givervektkg = userEntries.get(field);
		if (givervektkg == null){
			givervektkg = "-";
		}	
		return givervektkg;
	}




	public void setGivervektkg(String givervektkg) {
		this.givervektkg = givervektkg;
	}




	public String getGivererfaring() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-givererfaring";
		givererfaring = userEntries.get(field);
		if (givererfaring == null){
			givererfaring = "-";
		}	
		return givererfaring;
	}




	public void setGivererfaring(String givererfaring) {
		this.givererfaring = givererfaring;
	}




	public void setAlder(String alder) {
		this.alder = alder;
	}

	public String getKjonn() {
		Map<String,String> userEntries = getFormMap();
		String field = "tab-kjonn";
		kjonn = userEntries.get(field);
		if (kjonn == null){
			kjonn = "-";
		}else if(kjonn.equalsIgnoreCase("K")){
			kjonn="Kvinne";
		}else if(kjonn.equalsIgnoreCase("M")){
			kjonn="Mann";
		}
		
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	
	

}
