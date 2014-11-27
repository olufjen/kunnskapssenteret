package no.naks.web.nhn.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;

import no.naks.emok.model.Basismelding;
import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.nhn.model.PersonImpl;
import no.naks.nhn.service.MelderService;
import no.naks.services.nhn.client.ArrayOfDepartment;
import no.naks.services.nhn.client.Department;
import no.naks.services.nhn.client.Organization;
import no.naks.services.nhn.client.ObjectFactory;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;

/**
 * @author olj
 * nne klassen er hjelpeklasse for å lage innhold til web sider
 */
public class WebPagesServiceImpl implements WebPagesService{
	protected TableWebService tableWebService;
	protected SelectItem[] foretakItems; //Nedtrekk obligatorisk
	protected UISelectItems foretak = null; //Nedtrekk for foretak
	protected List foretakUIlist = null;
	protected MelderService melderService;
	protected SelectItem[] avdelingItems; //Nedtrekk obligatorisk
	protected UISelectItems avdeling = null; //Nedtrekk for avdelinger
	protected List avdelingUIlist = null;
	protected IBasismelding melding;
	protected Person person;
	protected Person leder;
	protected List<CommunicationParty> organisations = null;
	protected List<Department> departments = null; // Liste av mulige departments som kan velges fra
	protected ArrayOfDepartment avdelinger = null;
	protected CommunicationParty chosenOrganisation; //Valgt organisasjon fra nedtrekk/søk
	protected String stedforHendelsen = "";
	protected String lareavHendelse;
	protected Date tidForhendelsen = null;
	int orgSize = 0; // Antall organisasjoner
	int avdSize = 0; // Antall avdelinger
	protected String chosenOrgname = null;
	protected String valgtOrganisasjon = ""; //Teksfelt for valgt organisasjon
	protected String valgtDepartment = "";	 // Tekstfelt for valgt avdeling
	protected Organization foretakOrganisasjon = null; // Organisasjon skapt når bruker har skrevet navnet eller valgt fra et utvalg. 
	protected Organization foretakDepartment = null; // Department valgt som organisasjon
	private String male = "1";
	private String female = "2";
	private String ikkeKjent = "0";
	private String caseNr = "";
	private String kjonnKode = "";
	private String klokkeSlett = "";
	protected String spesialSted = "";
	private int ihours = 0;
	private int iminutes = 0;
	private String tempAr = "";
	private String orgHendelse;
	protected JAXBElement<String> organisationName = null;
	protected JAXBElement<String>displayName = null;
	protected JAXBElement<String> departmentName = null;
	protected JAXBElement<ArrayOfDepartment> valgteAvdelinger;
	protected Department department = null;
	protected ObjectFactory factory = null;
	protected boolean ikkeSykehus=false;
	protected String sessionId = "";
	protected String clientIP = "";
	private String valgtCommuncationParty = "";
	protected final int antAvdelinger = 20; 
	
	public WebPagesServiceImpl() {
		super();
		 System.out.println("Webpagesservice created");
		foretak = new UISelectItems();
		foretakUIlist = new ArrayList<SelectItem>();
		avdelingUIlist = new ArrayList<SelectItem>();
		avdeling = new UISelectItems();
		avdeling.setValue(avdelingUIlist);
		melding = new Basismelding();
		person = new PersonImpl(); 

		leder = new PersonImpl();
		factory = new ObjectFactory();
		sessionId = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getId();
		clientIP = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getHeader("X-FORWARDED-FOR");
		if (clientIP == null || clientIP.equals("")){
			((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
		}
	}
	/**
	 * buildDepartments
	 * Denne rutinen lager en liste over avdelinger til et sykehus/foretak som er valgt
	 * @param herId
	 */
	private void buildDepartments(int herId){
		int itp = 0;
		if (departments != null)
			departments = null;
		departments = new ArrayList<Department>(); // Lag en liste til å inneholde avdelinger man kan velge fra
		boolean found = false;
		if (avdelingUIlist != null)
			avdelingUIlist.clear();
		CommunicationParty department = null;
		if (avdelingItems == null)
			avdelingItems = new SelectItem[antAvdelinger];
		else {
			avdelingItems = null;
			avdelingItems = new SelectItem[antAvdelinger];
		}
		int itd = 0;
		String name = "none";
		do {
			department = organisations.get(itp);
			itp++;
			int idx = department.getParentHerId();
			
			if (idx == herId){
				if (itd < antAvdelinger){
					if (department.getName() != null){
						name = department.getName();
						if (name.equals(""))
							name = "x";
					}
					String ids = Integer.toString(department.getHerId());
					avdelingItems[itd] = new SelectItem(ids,name);
					avdelingUIlist.add(avdelingItems[itd]);
			
					Department avdeling = new Department();
					Integer avdHerid = department.getHerId();
					avdeling.setHerId(avdHerid);
					JAXBElement<String> depName = factory.createOrganizationCreateDisplayName("");
				
					depName.setValue(name);
					avdeling.setDisplayName(depName);
					departments.add(itd,avdeling); // Legg til i listen av departments man kan velge fra
					itd++;
				}
				
			}
		}while (itp < orgSize && department != null);
		avdeling.setValue(avdelingUIlist);
	}
	/**
	 * sethoursminutes
	 * Denne rutinen splitter et oppgit klokkeslett i timer og minutter
	 * 
	 */
	private void sethoursminutes(){
		String hours = "00";
		String minutes = "00";		
		if (!klokkeSlett.equals("")){
			char kolon = ':';
			int inx = klokkeSlett.indexOf(kolon);
			if (inx > 0){
				hours = klokkeSlett.substring(0,inx);
				minutes = klokkeSlett.substring(inx+1);
				ihours = Integer.parseInt(hours);
				iminutes = Integer.parseInt(minutes);
			}
		}
	}
	
	/**
	 * dognkodeIndex
	 * Denne rutinen setter index for døgnkode:
	 *  ! = Morgen, " = 2 = Ettermiddag, 3 = Kveld, 4 = Natt, 5 Vet ikke
	 *  Kalles fra Tablemaintenancebean når døgnkode settes
	 */
	public void dognkodeIndex(){
		String dognKode = melding.getDognkode().getDN();
		if (dognKode != null && !dognKode.equals("")){
			if (dognKode.equals("Morgen"))
				melding.getDognkode().setV("1");
			if (dognKode.equals("Ettermiddag"))
				melding.getDognkode().setV("2");
			if (dognKode.equals("Kveld"))
				melding.getDognkode().setV("3");
			if (dognKode.equals("Natt"))
				melding.getDognkode().setV("4");
			if (dognKode.equals("Vet ikke"))
				melding.getDognkode().setV("5");
	}

	}
	public String getValgtCommuncationParty() {
		return valgtCommuncationParty;
	}
	/* setValgtCommunicationParty
	 * Denne rutinen setter opp valgt organisasjon til meldingen
	 * @see no.naks.web.nhn.control.WebPagesService#setValgtCommuncationParty(java.lang.String)
	 */
	public void setValgtCommuncationParty(String valgtCommuncationParty) {
		this.valgtCommuncationParty = valgtCommuncationParty;
		CommunicationParty organisation = null;
		for(CommunicationParty p : organisations){
			if (p.getName().equals(this.valgtCommuncationParty)){
				chosenOrganisation = p; // type CommuncationParty
				if (foretakOrganisasjon == null)
					foretakOrganisasjon = new Organization();
				foretakOrganisasjon.setHerId(chosenOrganisation.getHerId());
				organisationName = factory.createOrganizationCreateDisplayName("");
				
				String name = chosenOrganisation.getName();
				organisationName.setValue(name);
				foretakOrganisasjon.setDisplayName(organisationName);
				break;
			}
		}
		Integer hIdx = chosenOrganisation.getHerId();
		String hId = hIdx.toString();
		setOrganisationElements(hId);
		melding.setMelder_enhet(Integer.parseInt(hId));
		melding.setNhnadresse(foretakOrganisasjon);
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public boolean isIkkeSykehus() {
		return ikkeSykehus;
	}
	public void setIkkeSykehus(boolean ikkeSykehus) {
		this.ikkeSykehus = ikkeSykehus;
	}
	
	
	public String getValgtOrganisasjon() {
		return valgtOrganisasjon;
	}
	/* setValgtOrganisasjon
	 * @see no.naks.web.nhn.control.WebPagesService#setValgtOrganisasjon(java.lang.String)
	 * Denne rutinen lager et organisasjonselement basert på tekstlig navn
	 */
	public void setValgtOrganisasjon(String valgtOrganisasjon) {
		this.valgtOrganisasjon = valgtOrganisasjon;
		if (foretakOrganisasjon == null){
			foretakOrganisasjon = new Organization();
			foretakOrganisasjon.setHerId(0);
			organisationName = factory.createOrganizationCreateDisplayName("");
			if (organisationName != null)
				organisationName.setValue(this.valgtOrganisasjon);
			foretakOrganisasjon.setDisplayName(organisationName);

			melding.setMelder_enhet(0);
			melding.setNhnadresse(foretakOrganisasjon);
		}
		
	}
	
	public String getValgtDepartment() {
		return valgtDepartment;
	}
	/* setValgtDepartment
	 * @see no.naks.web.nhn.control.WebPagesService#setValgtDepartment(java.lang.String)
	 * Denne rutinen lager et avdelingselement basert på tekstlig navn
	 */
	public void setValgtDepartment(String valgtDepartment) {
		this.valgtDepartment = valgtDepartment;
		if (department == null)
			department = new Department();
		if (departmentName == null)
			departmentName = factory.createDepartmentCreateDisplayName(valgtDepartment);
		departmentName.setValue(valgtDepartment);
		department.setDisplayName(departmentName);
		department.setHerId(new Integer(0));
		if (avdelinger == null)
			avdelinger = factory.createArrayOfDepartment();
		avdelinger.getDepartment().add(department);
		if (valgteAvdelinger == null)
			valgteAvdelinger = factory.createArrayOfDepartment(null);
		valgteAvdelinger.setValue(avdelinger);
		foretakOrganisasjon.setDepartments(valgteAvdelinger);
		
		
	}
	public String getSpesialSted() {
		return spesialSted;
	}
	public void setSpesialSted(String spesialSted) {
		this.spesialSted = spesialSted;
		if (ikkeSykehus){
			Organization organisation = new Organization();
			organisation.setHerId(0);
			organisationName = tableWebService.getOrganisationName();
			if (organisationName == null)
				organisationName = factory.createOrganizationCreateDisplayName("");
			if (organisationName != null)
				organisationName.setValue(this.spesialSted);
			organisation.setDisplayName(organisationName);
			melding.setStedforhendelsen(this.spesialSted);
	/*
	 *  Dette blir gjort for at man kan lagre to personer - melder og leder
	 * 		
	 */
			if (department == null)
				department = new Department();
			if (departmentName == null){
				valgtDepartment = "avdeling";
				departmentName = factory.createDepartmentCreateDisplayName(valgtDepartment);
			}
			departmentName.setValue(valgtDepartment);
			department.setDisplayName(departmentName);
			department.setHerId(new Integer(0));
			if (avdelinger == null)
				avdelinger = factory.createArrayOfDepartment();
			avdelinger.getDepartment().add(department);
			if (valgteAvdelinger == null)
				valgteAvdelinger = factory.createArrayOfDepartment(null);
			valgteAvdelinger.setValue(avdelinger);
			organisation.setDepartments(valgteAvdelinger);
/*
 * 			
 */
			melding.setMelder_enhet(0);
			melding.setNhnadresse(organisation);
		}
		
	}
	/**
	 * setOrganisationElements
	 * Denne rutinen setter opp nedtrekkslister over organisasjoner
	 * 
	 */
	private void setOrganisationElements(String hId){
		if (foretakUIlist != null)
			foretakUIlist.clear();

		if (orgSize > 0){
			foretakItems = new SelectItem[orgSize+3];
			String sId = "";String sname ="--Velg--";
			if (hId == null){
				foretakItems[0] = new SelectItem(sId,sname);
				foretakUIlist.add(0,foretakItems[0]);
			}
		
			for (int itp = 0;itp<orgSize-1;itp++) {
				CommunicationParty  organisation = organisations.get(itp);
				Integer idx = organisation.getHerId();
				String ids = idx.toString();
				 String name = "none";
				 if (organisation.getName() !=null){
					 name = organisation.getName();
					 if (name == null || name.equals(""))
						 name = "x";
				 }	 
				 foretakItems[itp] = new SelectItem(ids,name);
				 if (hId != null && ids.equals(hId)){
					 foretakUIlist.add(0,foretakItems[itp]);
				 }else
					 foretakUIlist.add(foretakItems[itp]);
			 }
//			Går det an å legge inn på denne måten?
			String sId2 = "annet";String sname2 ="Annet";
			foretakItems[orgSize+1]= new SelectItem(sId2, sname2);
			foretakUIlist.add(foretakItems[orgSize+1]);
			foretak.setValue(foretakUIlist);
			
		}
		System.out.println("Webpagesservice setOrganisationElements: organisation built");
	}
	

	public List<CommunicationParty> getOrganisations() {
		return organisations;
	}
	
	public String getOrgHendelse() {
		return orgHendelse;
	}
	public void setOrgHendelse(String orgHendelse) {
		this.orgHendelse = orgHendelse;
		String temp = orgHendelse;
		if (this.lareavHendelse != null && !this.lareavHendelse.equals(""))
			temp = temp + " " + this.lareavHendelse;
		melding.setHendelsesbeskrivelse(temp);
	}
	public String getTempAr() {
		return tempAr;
	}
	public void setTempAr(String tempAr) {
		this.tempAr = tempAr;
	}
	public String getLareavHendelse() {
		return lareavHendelse;
	}
	public void setLareavHendelse(String lareavHendelse) {
		this.lareavHendelse = lareavHendelse;
		String beskrivelse = melding.getHendelsesbeskrivelse();
		melding.setHendelsesbeskrivelse(beskrivelse+" "+this.lareavHendelse);
		melding.setKanlareav(true);
	}
	public String getKlokkeSlett() {
		return klokkeSlett;
	}

	public void setKlokkeSlett(String klokkeSlett) {
		this.klokkeSlett = klokkeSlett;
		sethoursminutes();
	}

	public String getKjonnKode() {
		return melding.getKjonn().getV();
	}

	public void setKjonnKode(String kjonnKode) {
		this.kjonnKode = kjonnKode;
		melding.getKjonn().setV(kjonnKode);
	}

	public String getCaseNr() {
		return caseNr;
	}

	public void setCaseNr(String caseNr) {
		this.caseNr = caseNr;

			/*
			 * OBS: Det er ikke et eget felt for entydig saksnummer fra melders enhet i xml meldingen!!
			 * Avdelingens Ident får type SAKSNR og DN settes til saksnummeret. 		
			 */
			melding.setEntydigidk(caseNr);

	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getFemale() {
		return female;
	}

	public void setFemale(String female) {
		this.female = female;
	}

	public String getIkkeKjent() {
		return ikkeKjent;
	}

	public void setIkkeKjent(String ikkeKjent) {
		this.ikkeKjent = ikkeKjent;
	}

	public Date getTidForhendelsen() {
		return tidForhendelsen;
	}

	public void setTidForhendelsen(Date tidForhendelsen) {
		this.tidForhendelsen = tidForhendelsen;
		
		melding.setTidforhendelsen(tidForhendelsen);
	}

	public Person getLeder() {
		return leder;
	}

	public void setLeder(Person leder) {
		this.leder = leder;
	}

	public String getStedforHendelsen() {
		return stedforHendelsen;
	}

	public void setStedforHendelsen(String stedforHendelsen) {
		this.stedforHendelsen = stedforHendelsen;
		if (melding.getStedforhendelsen() == null)
			melding.setStedforhendelsen("");
		melding.setStedforhendelsen(melding.getStedforhendelsen()+" "+this.stedforHendelsen);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public IBasismelding getMelding() {
		return melding;
	}

	public void setMelding(IBasismelding melding) {
		this.melding = melding;
	}

	public List getAvdelingUIlist() {
		return avdelingUIlist;
	}

	public void setAvdelingUIlist(List avdelingUIlist) {
		this.avdelingUIlist = avdelingUIlist;
	}

	public UISelectItems getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(UISelectItems avdeling) {
		this.avdeling = avdeling;
	}

	public TableWebService getTableWebService() {
		return tableWebService;
	}

	/* 
	 * setTableWebSerive
	 * Denne rutinen setter opp tablewebserive og henter inn foretak/orgainsasjoner til nedtrekk
	 * @see no.naks.web.nhn.control.WebPagesService#setTableWebService(no.naks.web.nhn.control.TableWebService)
	 */
	public void setTableWebService(TableWebService tableWebService) {
		this.tableWebService = tableWebService;
		tableWebService.collectMasterTables();
		String key = tableWebService.getforetakKey();
		organisations = tableWebService.getMasterDictionary().getDictionaryTable(key);
		if (organisations != null && !organisations.isEmpty()){
			orgSize = organisations.size();
			setOrganisationElements(null);
		}
		
	}

	public SelectItem[] getForetakItems() {
		return foretakItems;
	}

	public void setForetakItems(SelectItem[] foretakItems) {
		this.foretakItems = foretakItems;
	}

	public UISelectItems getForetak() {
		return foretak;
	}

	public void setForetak(UISelectItems foretak) {
		this.foretak = foretak;
	}

	public List getForetakUIlist() {
		return foretakUIlist;
	}

	public void setForetakUIlist(List foretakUIlist) {
		this.foretakUIlist = foretakUIlist;
	}
	
	/**
	 * searchOrganisation
	 * Denne rutinen henter frem valgt organisasjon og finner ev. avdelinger.
	 * Den kalles fra TableMaintenanceBean når bruker har valgt organisasjon/foretak
	 * fra setOrganisations
	 * @param orgName
	 */
	public void searchOrganisation(String orgName){
		int itp = 0;
		boolean found = false;
		if (avdelingUIlist != null)
			avdelingUIlist.clear();
		CommunicationParty organisation = null;

		String pname = "";
		do {
			organisation = organisations.get(itp);
			itp++;
			pname = organisation.getName();
	
			found = orgName.equals(pname);
		}while (itp < orgSize && organisation != null && !orgName.equals(pname));
		if (found && organisation != null){
			chosenOrganisation = organisation; // type CommuncationParty
			if (foretakOrganisasjon == null)
				foretakOrganisasjon = new Organization();
			foretakOrganisasjon.setHerId(chosenOrganisation.getHerId());
			organisationName = factory.createOrganizationCreateDisplayName("");
			
			String name = chosenOrganisation.getName();
			valgtOrganisasjon = name; // For kvitteringsdisplay
			organisationName.setValue(name);
			foretakOrganisasjon.setDisplayName(organisationName);

			
			Integer hIdx = organisation.getHerId();
			String hId = hIdx.toString();
			setOrganisationElements(hId);
			buildDepartments(organisation.getHerId());
			melding.setMelder_enhet(Integer.parseInt(hId));
			melding.setNhnadresse(foretakOrganisasjon);
//			int xx = 0;	
		}
	}
	/**
	 * findOrganisation
	 * Denne rutinen henter frem valgt organisasjon og finner ev. avdelinger.
	 * Den kalles fra TableMaintenanceBean når bruker har valgt organisasjon/foretak
	 * fra collectOrganisation
	 * @param herId Her id til organisasjon
	 */
	public void findOrganisation(String herId){
		int itp = 0;
		boolean found = false;
		if (avdelingUIlist != null)
			avdelingUIlist.clear();
		CommunicationParty organisation = null;

		String ids = "";
		do {
			organisation = organisations.get(itp);
			itp++;
			Integer idx = organisation.getHerId();
			ids = idx.toString();
			found = ids.equals(herId);
		}while (itp < orgSize && organisation != null && !ids.equals(herId));
		if (found && organisation != null){
			chosenOrganisation = organisation; // type CommuncationParty
			if (foretakOrganisasjon == null)
				foretakOrganisasjon = new Organization();
			foretakOrganisasjon.setHerId(chosenOrganisation.getHerId());
			organisationName = factory.createOrganizationCreateDisplayName("");
			
			String name = chosenOrganisation.getName();
			valgtOrganisasjon = name; // For kvitteringsdisplay
			organisationName.setValue(name);
			foretakOrganisasjon.setDisplayName(organisationName);
/*			
			if (organisation.getDepartments() != null){
				departments = organisation.getDepartments().getValue().getDepartment();
				chosenOrgname = organisation.getDisplayName().getValue();
				if (departments != null){
					avdSize = departments.size();
					avdelingItems = new SelectItem[avdSize+1];
					if (avdSize == 0){
						avdelingItems[0] = new SelectItem("0","Ingen avdelinger tilgjengelig");
						avdelingUIlist.add(avdelingItems[0]);
					}
					for (int iavd = 0;iavd <avdSize-1;iavd++) {
						Department department = departments.get(iavd);
						String ids = department.getHerId().toString();
						String name = department.getDisplayName().getValue();
						if (name == null || name.equals(""))
							name = "x";
						avdelingItems[iavd] = new SelectItem(ids,name);
						avdelingUIlist.add(avdelingItems[iavd]);
					}
				
				}
			
			}
*/				
			
			Integer hIdx = organisation.getHerId();
			String hId = hIdx.toString();
			setOrganisationElements(hId);
			buildDepartments(organisation.getHerId());
			melding.setMelder_enhet(Integer.parseInt(hId));
			melding.setNhnadresse(foretakOrganisasjon);
			int xx = 0;
		}
		 
	}
	/* findDepartment
	 * Denne rutinen finner valgt avdeling på basis av nedtrekk over tilgjengelige avdelinger 
	 * 
	 * @see no.naks.web.nhn.control.WebPagesService#findDepartment(java.lang.String)
	 */
	public void findDepartment(String aId){
		int idp = 0;
		 Department department = null;
		if (departments!= null){
			boolean found = false;
			int depSize = departments.size();
			do {
				if (depSize > 0){
				 department = (Department)departments.get(idp);
				 found = department.getHerId().toString().equals(aId);
				 idp++;
				}
			}while (department != null && idp < depSize && !found);
			if (found){
				valgtDepartment = department.getDisplayName().getValue(); // For kvitteringsdisplay
				if (avdelinger == null)
					avdelinger = factory.createArrayOfDepartment();
				avdelinger.getDepartment().add(department);
				if (valgteAvdelinger == null)
					valgteAvdelinger = factory.createArrayOfDepartment(null);
				valgteAvdelinger.setValue(avdelinger);
				foretakOrganisasjon.setDepartments(valgteAvdelinger);
	//			melding.setStedforhendelsen(department.getDisplayName().getValue()+" "+department.getHerId().toString());
				boolean endofDep = false;
				for (SelectItem avd : avdelingItems){
					String hid = (String)avd.getValue();
					if (hid.equals(aId)){
						avdelingUIlist.set(0, avd);
						endofDep = true;
					}
					if (endofDep)
						break;
				}
				
			}
		}
	}
	public void setFullDate(){
		if (ihours > 0){
			GregorianCalendar localTime = new GregorianCalendar();
			
			localTime.setTime(tidForhendelsen);
			localTime.set(localTime.HOUR,ihours);
			localTime.set(localTime.MINUTE, iminutes);
			tidForhendelsen = localTime.getTime();
			melding.setTidforhendelsen(tidForhendelsen);
			String tiden = DateFormat.getDateInstance().format(tidForhendelsen);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM");

			System.out.println("WebpagesService tid for hendelsen "+ tiden + " cal " + sdf.format(localTime.getTime()));
		}
	}

	public void setAvdsaksnumber() {
/*		
		if (melding.getEntydigidk() > 0){
			department.setHerId(melding.getEntydigidk());
		}
*/		
	}
	
}
