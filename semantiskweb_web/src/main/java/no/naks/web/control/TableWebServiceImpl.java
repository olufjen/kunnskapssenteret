package no.naks.web.control;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBElement;

import org.apache.myfaces.webapp.filter.ExtensionsResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;


import no.naks.emok.model.Basismelding;
import no.naks.emok.model.IBasismelding;
import no.naks.framework.web.control.MasterWebService;
import no.naks.nhn.model.Person;
import no.naks.nhn.model.PersonImpl;
import no.naks.nhn.service.MelderService;
import no.naks.nhn.service.NHNServiceClient;
import no.naks.services.nhn.client.Organization;
import no.naks.web.bean.PdfCreator;
import no.naks.web.model.Diskusjon;
import no.naks.web.model.Hendelse;
import no.naks.web.model.HvorHvem;
import no.naks.web.model.Kontaktinfo;
import no.naks.web.model.Kontroll;

/**
 * WEB-FLOW
 * Denne tjenesten fungerer som en controller for web-flow
 * Den initialiserer alle modellobjekter som benyttes for sidene i webskjema.
 * Det er et modellobjekt for hver side.
 * 
 * Denne tjenesten henter inn alle nødvendige lister fra Norsk Helsenett
 * Det skjer enten ved bruk av webtjenester eller ved oppslag mot database
 * 
 * @author olj
 *
 */

@RequestMapping("/") 
public class TableWebServiceImpl extends NHNMasterWebServiceImpl implements
		TableWebService,NHNMasterWebService,MasterWebService {
	protected MelderService meldingService; // Denne tjenesten sørger for lagring av meldinger til xml.
	

	
	public TableWebServiceImpl() {
		super();
		
		// TODO Auto-generated constructor stub
		 System.out.println("Tablewebservice created");
	}
	
	public HvorHvem initializeHvorHvem(){
		HvorHvem modelObj = new HvorHvem();
		System.out.println("Tablewebservice: initialisering hvorhvem");
		//modelObj.setMelding(melding);
		return modelObj;
	}
	public Hendelse initializeHendelse(){
		Hendelse modelHendelse = new Hendelse();
		System.out.println("Tablewebservice: initialisering hendelse");
		
		return modelHendelse;
	}
	public Diskusjon initializeDiskusjon(){
		Diskusjon diskusjon = new Diskusjon();
		return diskusjon;
	}
	public Kontaktinfo initializeKontaktinfo(){
		Kontaktinfo kontaktInfo = new Kontaktinfo();
		return kontaktInfo;
	}
	public IBasismelding initializeMelding(){
		IBasismelding melding = new Basismelding();
		System.out.println("Tablewebservice: initialisering melding");
		return melding;
	}
	/**
	 * initializeOrganization
	 * Denne rutinen utføres når bruker er ferdig med hvorhvem siden
	 * @return
	 */
	public Organization initializeOrganization(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj"); 
		Organization organisasjon = hvorHvem.getMelding().getNhnadresse();
		return organisasjon;
	}
	/*
	 * initializeLeder
	 * Denne rutinen utføres når bruker forlater hendelsessiden
	 * @return
	 */
	public Person initializeLeder(){
		Person leder = new PersonImpl();
		return leder;
	}
	
	/**
	 * initializePerson
	 * Denne rutinen utføres når bruker forlater diskusjonssiden
	 * @return
	 */
	public Person initializePerson(){
		Person person = new PersonImpl();
		return person;
	}
	
	
	public PdfCreator initializePdfCreator(){
		// Set kontroll button invisible 
	
		PdfCreator pdfCreator = new PdfCreator();
		return pdfCreator;
	}
	
	/**
	 * collectScope
	 * Denne rutinen setter sammen alle objektene som er aktive i flow
	 * Den kalles når flow starter
	 */
	public void collectScope(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
		Person person = (Person)flow.get("modelPerson");
		Person leder = (Person)flow.get("modelLeder");
		Hendelse hendelse = (Hendelse)flow.get("modelHendelse");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj"); 
		Diskusjon diskusjon = (Diskusjon)flow.get("modelDiskusjon");
		Kontaktinfo kontaktInfo = (Kontaktinfo)flow.get("modelKontaktinfo");
//		hendelse.setMelding(ml);
		hvorHvem.setMelding(ml);
//		diskusjon.setMelding(ml);
//		kontaktInfo.setPerson(person);
//		kontaktInfo.setLeder(leder);
//		kontaktInfo.setMelding(ml);
		
		
	}

	/**
	 * hendelsesPage
	 * Denne rutinen utføres når bruker har valgt hendelsessiden
	 * @return
	 */
	public String hendelsePage(){
	//	FacesContext.getCurrentInstance().renderResponse();
		String returnValue = "hendelse";
		
	/*	
		if (modelHendelse == null){
			//modelHendelse = new Hendelse();
			returnValue = "innledning";
		}
	*/	
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		
		Basismelding ml = (Basismelding)flow.get("melding");
//		Person person = (Person)flow.get("modelPerson");
//		Person leder = (Person)flow.get("modelLeder");

		Hendelse hendelse = (Hendelse)flow.get("modelHendelse");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj"); 
		hendelse.setForetakOrganisasjon(ml.getNhnadresse());
		hendelse.setMelding(ml);
		return returnValue;
	}
	public String innledningPage(){
		return "innledning";
	}
	public String hvorHvemPage(){
		return "hvorhvem";
	}
	
	

	/**
	 * diskusjonPage
	 * Denne rutinen utføres når bruker har valgt diskusjonssiden
	 * @return
	 */
	public String diskusjonPage(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
//		Person person = (Person)flow.get("person");
//		Person leder = (Person)flow.get("leder");
		Hendelse hendelse = (Hendelse)flow.get("modelHendelse");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj");
		Diskusjon diskusjon = (Diskusjon)flow.get("modelDiskusjon");
		Organization organisasjon = (Organization)flow.get("modelOrganization");
		diskusjon.setForetakOrganisasjon(ml.getNhnadresse());
		diskusjon.setMelding(ml);
		return "diskusjon";
	}
	public String controllPage(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Person person = (Person)flow.get("modelPerson");
		Person leder = (Person)flow.get("modelLeder");
		return "kontroll";
	}
	

	/**
	 * checkKontroll
	 * Denne rutinen utføres når bruker kommer til kontrollsiden
	 * @return
	 */
	public Kontroll checkKontroll(){
		Kontroll kontroll = new Kontroll();
		
		boolean  blHvorHvem, blHendelse, blKontaktinfo, hasError=false;
		String onsker = "Nei";
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
		boolean blOnskerhjelp = ml.isOnskerhjelp();
		if (blOnskerhjelp)
			onsker = "Ja";
		kontroll.setOnsker(onsker);	
		Person person = (Person)flow.get("modelPerson");
		Person leder = (Person)flow.get("modelLeder");
		Hendelse hendelse = (Hendelse)flow.get("modelHendelse");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj"); 
		Diskusjon diskusjon = (Diskusjon)flow.get("modelDiskusjon");
		Kontaktinfo kontaktInfo = (Kontaktinfo)flow.get("modelKontaktinfo");
		kontaktInfo.setPerson(person);
		kontaktInfo.setLeder(leder) ;
		Organization organisasjon = (Organization)flow.get("modelOrganization");
//		blHvorHvem = true;blHendelse = true;blKontaktinfo = true;hasError = true;
		blHvorHvem = hvorHvem.isHvorHvemError();
		blHendelse = hendelse.isHendelseError();
		blKontaktinfo = kontaktInfo.isKontaktInfoError();
		
		if(blHvorHvem || blHendelse || blKontaktinfo){
			hasError = true;
		}
		
/*
		if (hvorHvem.isAarHidden() && hvorHvem.isDatoHidden() && hvorHvem.isSpesialistHidden() && hvorHvem.isSykehusHidden()){
			blHvorHvem = false;
		}
		if (hendelse.isBlHendelseHidden()){
			blHendelse = false;
		}
		if (kontaktInfo.isBlmailHidden() && kontaktInfo.isBlgjentaHidden() && kontaktInfo.isPhHidden() && kontaktInfo.isBllederHidden()){
			blKontaktinfo = false;
		}

		if(!blHendelse || !blHvorHvem || !blKontaktinfo){
			hasError = false;
			
		}
		
		if(!blHendelse && !blHvorHvem && !blKontaktinfo){
			hasError = false;
			
		}
*/		
//		kontaktInfo.setLeder(leder);
//		kontaktInfo.setPerson(person);
		kontroll.setBlHendelse(blHendelse);
		kontroll.setBlHvorhvem(blHvorHvem);
		kontroll.setBlKontaktInfo(blKontaktinfo);
		kontroll.setHasError(hasError);
	/*
		kontroll.setAarHidden(hvorHvem.isAarHidden());
		kontroll.setDatoHidden(hvorHvem.isDatoHidden());
		kontroll.setKlokkeHidden(hvorHvem.isKlokkeHidden());
		kontroll.setSpesialistHidden(hvorHvem.isSpesialistHidden());
		kontroll.setSykehusHidden(hvorHvem.isSykehusHidden());
	*/	
		return kontroll;
	}
	/**
	 * kontaktinfoPage
	 * Denbne rutinen utføres når bruker har valgt siden kontaktinfo
	 * @return
	 */
	public String kontaktinfoPage(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
		Organization foretakOrganisasjon = ml.getNhnadresse();
		Person person = (Person)flow.get("modelPerson");
		Person leder = (Person)flow.get("modelLeder");
		Hendelse hendelse = (Hendelse)flow.get("modelHendelse");
		HvorHvem hvorHvem = (HvorHvem)flow.get("modelObj");
		Diskusjon diskusjon = (Diskusjon)flow.get("modelDiskusjon");
		Kontaktinfo kontaktInfo = (Kontaktinfo)flow.get("modelKontaktinfo");
		Organization organisasjon = (Organization)flow.get("modelOrganization");
		Organization foretak = diskusjon.getForetakOrganisasjon();
		if (foretakOrganisasjon == null)
			ml.setNhnadresse(organisasjon);
//		kontaktInfo.setLeder(leder);
//		kontaktInfo.setPerson(person);
		kontaktInfo.setMelding(ml);
		kontaktInfo.setForetakOrganisasjon(foretak);
		return "kontaktinfo";
	}
	
	public NHNServiceClient getNhnClient() {
		return nhnClient;
	}

	public void setNhnClient(NHNServiceClient nhnClient) {
		this.nhnClient = nhnClient;
	
		nhnClient.setNhnFlag(nhnFlag);
		nhnClient.initializeTables();
		organisationName = nhnClient.getOrganisationName();
	}
	


	public MelderService getMeldingService() {
		return meldingService;
	}

	public JAXBElement<String> getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(JAXBElement<String> organisationName) {
		this.organisationName = organisationName;
	}

	public void setMeldingService(MelderService meldingService) {
		this.meldingService = meldingService;
	}

	public boolean isNhnFlag() {
		return nhnFlag;
	}

	public void setNhnFlag(boolean nhnFlag) {
		this.nhnFlag = nhnFlag;
	}
	/**
	 * getKvittering
	 * Denne rutinen utføres når bruker ønsker å sende melding til Kunnskapssenteret
	 * @return
	 */
	public String getKvittering(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		Basismelding ml = (Basismelding)flow.get("melding");
		Person person = (Person)flow.get("modelPerson");
		Person leder = (Person)flow.get("modelLeder");
		Organization organisasjon = (Organization)flow.get("modelOrganization");
		Organization foretakOrganisasjon = ml.getNhnadresse();
		if (foretakOrganisasjon == null)
			ml.setNhnadresse(organisasjon);
		getMeldingService().sendMelding(ml,person,leder);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String sessionId = session.getId();
		Principal prinsipal = reqContext.getExternalContext().getCurrentUser();
		String pName = "noname";
		
		if (prinsipal != null && prinsipal.getName() != null && !prinsipal.getName().equals(""))
			pName = prinsipal.getName();
		System.out.println("getKvittering Session "+sessionId + " user " + pName + " har sent en melding");
		
		return "kvittering";
	}

	public String sessionKiller(){
	/*	
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ExtensionsResponseWrapper response = (ExtensionsResponseWrapper) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setHeader("Cache-control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader("Expires", -1); 
		session.invalidate();
	*/	
		return "cancel";
	}
	




}
