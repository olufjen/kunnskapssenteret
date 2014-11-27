/**
 * 
 */
package no.naks.web.nhn.bean;




import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.faces.component.UISelectItems;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneListbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;



import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.services.nhn.client.ICommunicationPartyServiceGetCommunicationPartyDetailsGenericFaultFaultFaultMessage;
import no.naks.services.nhn.client.Organization;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;
import no.naks.web.jsf.SetEncodingFilter;
import no.naks.web.nhn.control.WebPagesService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Denne klassen ivaretar oppgaver knyttet til 
 * 
 * @author ojn
 *
 */

public class TableMaintenanceBean extends MasterBean {

	private static Log log = LogFactory.getLog(TableMaintenanceBean.class);
	protected WebPagesService webPagesService;
	protected UISelectItems foretak = null; //Nedtrekk for foretak/organisasjoner
	protected UISelectItems avdeling = null; //Nedtrekk for avdelinger
	protected HtmlSelectOneListbox foretakValg;
	protected HtmlSelectOneListbox avdelingValg;
	protected boolean foretakChosen = false;
	protected Boolean bforetakChosen = null;
	protected String foretakChosenKey = "foretakchosen";
	protected String fsInit = "x";
	protected List foretakUIlist = null;
	protected List avdelingUIlist = null;
	
	protected HtmlInputText seksjonInput;
	private String dognkode ="dag";
	private String seksjon = "x";
	private String arfodt = "";
	private String finit = "";

	private Date todayDate;
	private boolean ikkeSykehus=false;
	private boolean datoHidden;
	private boolean klokkeHidden;
	private boolean aarHidden;
	private boolean spesialistHidden;
	private boolean sykehusHidden;
	private boolean blAvdeling=true;


	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public TableMaintenanceBean() {
		super();
		log.info("Tablemaintenance bean created" );
		System.out.println("Tablemaintenance bean created");
	}

	public List<String> fillOrganization(String suggest){
		ArrayList<String> result = new ArrayList<String>();
		List<CommunicationParty> organisations = null;
		organisations = webPagesService.getOrganisations();
		Iterator<CommunicationParty> iterator = organisations.iterator();
		while(iterator.hasNext()){
			CommunicationParty  organisation = iterator.next();
			String name = organisation.getName();
			if(name!= null){
				String[] subName = name.split(" ");
				int size = subName.length;
				for(int i=0; i<size; i++){
					if(subName[i].toLowerCase().indexOf(suggest.toLowerCase())==0){
						result.add(name);
						i=size;
					}
				}
			}
			/*if ((name != null && name.toLowerCase().indexOf(suggest.toLowerCase()) == 0)
					|| "".equals(suggest)) {
				result.add(name);
			}*/
		}
		return result;
	}	
	
	public String getValgtCommuncationParty() {
		return webPagesService.getValgtCommuncationParty();
	}

	public void setValgtCommuncationParty(String valgtCommuncationParty) {
		webPagesService.setValgtCommuncationParty(valgtCommuncationParty);
	}

	public boolean isSpesialistHidden() {
		Object obj = getSessionObject("spesialistHidden");
		spesialistHidden = true;
		if(obj != null){
			spesialistHidden = (Boolean)obj;
		}
		return spesialistHidden;
	}

	public boolean isSykehusHidden() {
		Object obj = getSessionObject("sykehusHidden");
		sykehusHidden = false;
		if(obj != null){
			sykehusHidden = (Boolean)obj;
		}
		return sykehusHidden;
	}

	/**
	 * spesialhiddenValue
	 * Denne rutinen kalles enten man har krysset av på annet sted enn sykehus eller ikke.
	 * @param val
	 */
	public void spesialHiddenValue(ValueChangeEvent val) {
		String strSpesialistHidden = (String)val.getNewValue();
		boolean blSpesialistHidden=true;
		boolean blSykehusHidden=true;
		setSessionObject("sykehusHidden", blSykehusHidden);
		if(strSpesialistHidden != null && strSpesialistHidden !=""){
			blSpesialistHidden = Boolean.parseBoolean(strSpesialistHidden);
		}
		setSessionObject("spesialistHidden", blSpesialistHidden);
	}

	
	public void sykehusHiddenValue(ValueChangeEvent val) {
		String strSykehusHidden = (String)val.getNewValue();
		boolean blSykehusHidden=false;
		if(strSykehusHidden != null && strSykehusHidden !=""){
			blSykehusHidden = Boolean.parseBoolean(strSykehusHidden);
		}
		setSessionObject("sykehusHidden", blSykehusHidden);
	}

	
	public boolean getDatoHidden() {
		Object obj = getSessionObject("datoHidden");
		datoHidden = true;
		if(obj != null){
			datoHidden = (Boolean)obj;
		}
		return datoHidden;
	}
	
	public void setDatoHidden(boolean datoHidden) {
		setSessionObject("datoHidden", datoHidden);
	}
	
	public void datoHiddenValue(ValueChangeEvent val){
		String strDatoHidden = (String)val.getNewValue();
		boolean blDatoHidden=true;
		if(strDatoHidden != null && strDatoHidden !=""){
			blDatoHidden = Boolean.parseBoolean(strDatoHidden);
		}
		setDatoHidden(blDatoHidden) ;
	}

	public boolean getKlokkeHidden() {
		Object obj= getSessionObject("klokkeHidden");
		klokkeHidden = true;
		if(obj!=null){
			klokkeHidden=(Boolean)obj;
		}
		return klokkeHidden;
	}
	public void setKlokkeHidden(boolean klokkeHidden) {
		setSessionObject("klokkeHidden", klokkeHidden);
	}
	
	public void klokkenHiddenValue(ValueChangeEvent val){
		String strKlokkenHidden = (String)val.getNewValue();
		boolean blKlokkenHidden=true;
		if(strKlokkenHidden != null && strKlokkenHidden !=""){
			blKlokkenHidden = Boolean.parseBoolean(strKlokkenHidden);
		}
		setKlokkeHidden(blKlokkenHidden) ;

	}
	
	
	public boolean getAarHidden() {
		Object obj= getSessionObject("aarHidden");
		aarHidden = true;
		if(obj!=null){
			aarHidden = (Boolean)obj;
		}
		return aarHidden;
	}
	public void setAarHidden(boolean aarHidden) {
		setSessionObject("aarHidden", aarHidden);
	}

	
	public void aarHiddenValue(ValueChangeEvent val){
		String strAarHidden = (String)val.getNewValue();
		boolean blAarHidden=true;
		if(strAarHidden != null && strAarHidden !=""){
			blAarHidden = Boolean.parseBoolean(strAarHidden);
		}
		setAarHidden(blAarHidden) ;
	}
	
	
	public boolean isIkkeSykehus() {
		Object obj = getSessionObject("ikkeSykehus");
		ikkeSykehus=false;
		if(obj != null)
			ikkeSykehus = (Boolean)obj;
		webPagesService.setIkkeSykehus(ikkeSykehus);	
		return ikkeSykehus;
	}
	public void setIkkeSykehus(boolean ikkeSykehus) {
		setSessionObject("ikkeSykehus", ikkeSykehus);
		webPagesService.setIkkeSykehus(ikkeSykehus);
	}

	public String getFinit() {
		return finit;
	}

	public void setFinit(String finit) {
		this.finit = finit;
	}


	public String getKlokkeSlett() {
		return webPagesService.getKlokkeSlett();
	}

	public void setKlokkeSlett(String klokkeSlett) {
		webPagesService.setKlokkeSlett(klokkeSlett);
	}

	public String getDognkode() {
		return webPagesService.getMelding().getDognkode().getDN();
	}
	public void setDognkode(String dognkode) {
		this.dognkode = dognkode;
		webPagesService.getMelding().getDognkode().setDN(dognkode);
		webPagesService.dognkodeIndex();
	}

	public String getKjonnKode() {
		return webPagesService.getKjonnKode();
	}
	public String getKjonn(){
		String strKjonnId = getKjonnKode();
		if(strKjonnId != null && strKjonnId !=""){
			int iKjonnId = Integer.parseInt(strKjonnId);
			if(iKjonnId==1){
				return "Mann";
			}else if(iKjonnId == 2){
				return "Kvinne";
			}else if(iKjonnId == 3){
				return "Flere pasienter involvert";
			}else{
				return"Ingen pasienter involvert";
			}
		}else{
			return "";
		}
		
	}

	public void setKjonnKode(String kjonnKode) {
		webPagesService.setKjonnKode(kjonnKode);
	}

	public String getCaseNr() {
		return webPagesService.getCaseNr();
	}



	public void setCaseNr(String caseNr) {
		webPagesService.setCaseNr(caseNr);
	}



	public HtmlInputText getSeksjonInput() {
		return seksjonInput;
	}



	public String getMale() {
		return webPagesService.getMale();
	}



	public void setMale(String male) {
		webPagesService.setMale(male);
	}



	public String getFemale() {
		return webPagesService.getFemale();
	}



	public void setFemale(String female) {
		webPagesService.setFemale(female);
	}



	public String getIkkeKjent() {
		return webPagesService.getIkkeKjent();
	}



	public void setIkkeKjent(String ikkeKjent) {
		webPagesService.setIkkeKjent(ikkeKjent);
	}



	public void setSeksjonInput(HtmlInputText seksjonInput) {
		this.seksjonInput = seksjonInput;
	}



	public String getSeksjon() {
		return webPagesService.getStedforHendelsen();
	}




	public void setSeksjon(String seksjon) {
		webPagesService.setStedforHendelsen(seksjon);
	}


	public String getArfodt() {
		Integer aar = new Integer(webPagesService.getMelding().getArfodt());
		String tAr = webPagesService.getTempAr();
		if (!tAr.equals(""))
			return tAr;
		if(aar==0)
			return "";
		else
			return aar.toString();
	}



	public void setArfodt(String arfodt) {
		this.arfodt = arfodt;
		Integer naar = null;
		if(arfodt != null && arfodt !=""){
			try {
				naar = new Integer(Integer.parseInt(arfodt));
			}catch (NumberFormatException ec){
				naar = new Integer(0);
				webPagesService.setTempAr(arfodt);
			}
		
			
			webPagesService.getMelding().setArfodt(naar.intValue());
		}
	}



	public String collectHNH(){
		bforetakChosen = (Boolean) tableWebService.getMasterDictionary().getSessionObject(foretakChosenKey);
		if (bforetakChosen != null)
			foretakChosen = bforetakChosen.booleanValue();
		return "";
	}

	public String saveSchemaElements(){
		
		return "";
	}


	public List getForetakUIlist() {
		if (foretakUIlist == null)
			foretakUIlist = webPagesService.getForetakUIlist();
		return foretakUIlist;
	}

	public void setForetakUIlist(List foretakUIlist) {
		this.foretakUIlist = foretakUIlist;
	}

	public List getAvdelingUIlist() {
		if (avdelingUIlist == null)
			avdelingUIlist = webPagesService.getAvdelingUIlist();
		return avdelingUIlist;
	}

	public void setAvdelingUIlist(List avdelingUIlist) {
		this.avdelingUIlist = avdelingUIlist;
	}

	public boolean isForetakChosen() {
		return foretakChosen;
	}

	public void setForetakChosen(boolean foretakChosen) {
		this.foretakChosen = foretakChosen;
	}

	public WebPagesService getWebPagesService() {
		return webPagesService;
	}

	public void setWebPagesService(WebPagesService webPagesService) {
		this.webPagesService = webPagesService;
		foretak = webPagesService.getForetak();
		foretakUIlist = webPagesService.getForetakUIlist();
		tableWebService = webPagesService.getTableWebService();
		
	}
	public String getValgtOrganisasjon() {
		return webPagesService.getValgtOrganisasjon();
	}
	public void setValgtOrganisasjon(String valgtOrganisasjon) {
		webPagesService.setValgtOrganisasjon(valgtOrganisasjon);
		
	}
	public void changeOrganisation(ValueChangeEvent event){
		String old = (String) event.getOldValue();
		String ny = (String) event.getNewValue();
//		HtmlSelectOneListbox organisationType = (HtmlSelectOneListbox) event
//		.getComponent();
		
		
	}
	public String getValgtDepartment() {
		return webPagesService.getValgtDepartment();
	}
	public void setValgtDepartment(String valgtDepartment) {
		webPagesService.setValgtDepartment(valgtDepartment);
	}
	public Person getPerson() {
		return webPagesService.getPerson();
	}

	public void setPerson(Person person) {
		webPagesService.setPerson(person);
	}
	public IBasismelding getMelding() {
		return webPagesService.getMelding();
	}

	public void setMelding(IBasismelding melding) {
		webPagesService.setMelding(melding); 
	}
	public HtmlSelectOneListbox getForetakValg() {
		return foretakValg;
	}

	public void setForetakValg(HtmlSelectOneListbox foretakValg) {
		this.foretakValg = foretakValg;
	}

	public HtmlSelectOneListbox getAvdelingValg() {
		return avdelingValg;
	}

	public void setAvdelingValg(HtmlSelectOneListbox avdelingValg) {
		this.avdelingValg = avdelingValg;
	}

	public Boolean getBforetakChosen() {
		return bforetakChosen;
	}

	public void setBforetakChosen(Boolean bforetakChosen) {
		this.bforetakChosen = bforetakChosen;
	}

	public String getForetakChosenKey() {
		return foretakChosenKey;
	}

	public void setForetakChosenKey(String foretakChosenKey) {
		this.foretakChosenKey = foretakChosenKey;
	}

	public UISelectItems getForetak() {
		return foretak;
	}


	public void setForetak(UISelectItems foretak) {
		this.foretak = foretak;
	}	
	
	
	public UISelectItems getAvdeling() {
		if (avdeling == null)
			avdeling = webPagesService.getAvdeling();
		return avdeling;
	}

	public void setAvdeling(UISelectItems avdeling) {
		this.avdeling = avdeling;
	}

	public String getFsInit() {
		avdeling = webPagesService.getAvdeling();
		return fsInit;
	}

	public void setFsInit(String fsInit) {
		this.fsInit = fsInit;
	}

	/**
	 * setOrganisations
	 * Denne rutinen utføres når bruker har valgt en organisasjon fra en liste.
	 * Ref tilsvarende kall til collectOrganisation (kalles når man velger fra nedtrekk)
	 */
	public void setOrganisations(){
		
		String strOrganisasjon =webPagesService.getValgtOrganisasjon();
		boolean blSykehusHidden=false;
	
		if (strOrganisasjon != null && !strOrganisasjon.equals("") && !strOrganisasjon.equals("0") ) {
			webPagesService.searchOrganisation(strOrganisasjon);
			avdeling = webPagesService.getAvdeling();
			avdelingUIlist = webPagesService.getAvdelingUIlist();
			List items = (ArrayList)avdeling.getValue();
			if (items != null && !items.isEmpty()){
				foretakChosen = true;
				bforetakChosen = new Boolean(foretakChosen);
				tableWebService.getMasterDictionary().addtoSessionDictionary(bforetakChosen, foretakChosenKey);

				setBlAvdeling(true);
			}else{
				setBlAvdeling(false);
			}
			blSykehusHidden=true;
			
			setSessionObject("sykehusHidden", blSykehusHidden);
			System.out.println("Tablemaintenance collectOrganisation: collected organisations");
		}
//		avdelingValg.setValue(null);
//		FacesContext.getCurrentInstance().renderResponse();
//		avdelingValg.setValue(avdelingUIlist);
		
	}
	
	/**
	 * collectOrganisation
	 * Denne rutinen utføres når bruker har valgt en organisasjon fra nedtrekket.
	 * 
	 * @param event
	 */

	public void collectOrganisation(ValueChangeEvent event){
		String old = (String) event.getOldValue();
		String ny = (String) event.getNewValue();
		HtmlSelectOneListbox organisationType = (HtmlSelectOneListbox) event
		.getComponent();
		boolean blSykehusHidden=false;
		
	//	organisationType.setValue("");
		if (ny != null && !ny.equals("") && !ny.equals("0") && !ny.equals(old)) {
			webPagesService.findOrganisation(ny);
			avdeling = webPagesService.getAvdeling();
			avdelingUIlist = webPagesService.getAvdelingUIlist();
			List items = (ArrayList)avdeling.getValue();
			if (items != null && !items.isEmpty()){
				foretakChosen = true;
				bforetakChosen = new Boolean(foretakChosen);
				tableWebService.getMasterDictionary().addtoSessionDictionary(bforetakChosen, foretakChosenKey);
			}
			blSykehusHidden=true;
			
			setSessionObject("sykehusHidden", blSykehusHidden);
			System.out.println("Tablemaintenance collectOrganisation: collected organisations");
		}
		avdelingValg.setValue(null);
//		FacesContext.getCurrentInstance().renderResponse();

	}
	
	
	
	public boolean isBlAvdeling() {
		if(getSessionObject("blAvdeling") != null ){
			blAvdeling = (Boolean) getSessionObject("blAvdeling");
		}
		
		return blAvdeling;
	}

	public void setBlAvdeling(boolean blAvdeling) {
		setSessionObject("blAvdeling", blAvdeling) ;
	}

	/**
	 * collectDepartment
	 * Denne rutinen utføres når bruker har valgt et department (avdeling) fra nedtrekk
	 * @param event
	 */
	public void collectDepartment(ValueChangeEvent event){
		String old = (String) event.getOldValue();
		String ny = (String) event.getNewValue();
		HtmlSelectOneListbox departmentType = (HtmlSelectOneListbox) event
		.getComponent();
		if (ny != null && !ny.equals("") && !ny.equals("0") && !ny.equals(old)) {
			webPagesService.findDepartment(ny);
		}
		
	}
	public String getSpesialSted() {
		return webPagesService.getSpesialSted();
	}
	public void setSpesialSted(String spesialSted) {
		webPagesService.setSpesialSted(spesialSted);
	}
	public Date getTidForhendelsen() {
		return webPagesService.getTidForhendelsen();
	}

	public void setTidForhendelsen(Date tidForhendelsen) {
		webPagesService.setTidForhendelsen(tidForhendelsen);
	}

	public String getStedforHendelsen() {
		return webPagesService.getStedforHendelsen();
	}

	public void setStedforHendelsen(String stedforHendelsen) {
		webPagesService.setStedforHendelsen(stedforHendelsen);
	}
	public String hendelsePage(){
	//	FacesContext.getCurrentInstance().renderResponse();
		webPagesService.setFullDate();
		webPagesService.setAvdsaksnumber();
		return "hendelse";
	}
	public String innledningPage(){
		webPagesService.setFullDate();
		webPagesService.setAvdsaksnumber();
		return "innledning";
	}
	public String hvorHvemPage(){
		webPagesService.setFullDate();
		webPagesService.setAvdsaksnumber();
		return "hvorHvem";
	}
	public String diskusjonPage(){
		webPagesService.setFullDate();//vet ikke om disse trengs
		webPagesService.setAvdsaksnumber();
			return "diskusjon";
		}
	
		public String kontaktinfoPage(){
			webPagesService.setFullDate();//vet ikke om disse trengs
			webPagesService.setAvdsaksnumber();
			return "kontaktinfo";
		}
		
	
}
