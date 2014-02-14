package no.naks.web.control;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;




import edu.unc.ils.mrc.hive.HiveException;
import edu.unc.ils.mrc.hive.api.impl.elmo.SKOSSchemeImpl;
import edu.unc.ils.mrc.hive2.api.HiveConcept;
import edu.unc.ils.mrc.hive2.api.HiveVocabulary;
import edu.unc.ils.mrc.hive2.api.impl.HiveVocabularyImpl;
import no.naks.emok.model.Basismelding;
import no.naks.emok.model.IBasismelding;
import no.naks.framework.web.control.MasterWebService;
import no.naks.nhn.model.Person;
import no.naks.nhn.model.PersonImpl;
import no.naks.nhn.service.MelderService;
import no.naks.nhn.service.NHNServiceClient;
import no.naks.services.nhn.client.Organization;

import no.naks.semweb.control.HiveService;
import no.naks.semweb.model.Meshtree;
import no.naks.semweb.model.SemantiskTree;


/**
 * WEB-FLOW
 * Denne tjenesten fungerer som en controller for web-flow
 * Den initialiserer alle modellobjekter som benyttes for sidene i webskjema.
 * Det er et modellobjekt for hver side.
 * 
 * 
 * @author olj
 *
 */


public class TableWebServiceImpl extends NHNMasterWebServiceImpl implements
		TableWebService,NHNMasterWebService,MasterWebService {
	protected MelderService meldingService; // Denne tjenesten sørger for lagring av meldinger til xml.
	protected HiveService hiveService;
	private ArrayList<HiveConcept> hiveConcepts;
	
	public TableWebServiceImpl() {
		super();
		
		// TODO Auto-generated constructor stub
/*		
		Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		Set<String> pathSet = FacesContext.getCurrentInstance().getExternalContext().getResourcePaths("/");
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
	
	
//		C:\svnroot2\nokc\semantiskweb\trunk\semantiskweb_web\src\main\webapp\WEB-INF\conf
//		String confPath = "c:/svnroot2/nokc/semantiskweb/trunk/semantiskweb_web/src/main/webapp/WEB-INF/conf/";
		String confPath = path + "/config/app-config";
*/

		 System.out.println("Tablewebservice created");
	}
	protected String getFacesParamValue(String name) {
	    return (String) FacesContext
	                        .getCurrentInstance()
	                            .getExternalContext()
	                                .getRequestParameterMap()
	                                    .get(name);
	
	}
	/**
	 * THis method moved to HiveService
	 * 
	 * @deprecated
	 */
	public void initializeVocabulary(){
		boolean firstTime = false;
		String vocabularyName = "mesh";
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		HiveVocabularyImpl vocabulary = null;
		ArrayList<HiveConcept> hiveConcepts = new ArrayList();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Set<String> pathSet = FacesContext.getCurrentInstance().getExternalContext().getResourcePaths("/");
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
	
	
//		C:\svnroot2\nokc\semantiskweb\trunk\semantiskweb_web\src\main\webapp\WEB-INF\conf
//		String confPath = "c:/svnroot2/nokc/semantiskweb/trunk/semantiskweb_web/src/main/webapp/WEB-INF/conf/";
		String confPath = path + "/config/app-config";
		SKOSSchemeImpl scheme = null;
		try {
			scheme = new SKOSSchemeImpl(confPath, vocabularyName, firstTime);
		} catch (HiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("Tablewebservice: initialisering scheme");
		//modelObj.setMelding(melding);
		vocabulary = (HiveVocabularyImpl) scheme.getHiveVocabulary();
		session.setAttribute("meshvocabulary",vocabulary);
//		sessionMap.put("meshvocabulary",vocabulary);
//		return vocabulary;
	}
	/**
	 * @deprecated
	 * @return
	 */
	public Map<String,QName> initializeScheme(){
/*
		boolean firstTime = false;
		String vocabularyName = "mesh";
		HiveVocabularyImpl vocabulary = null;
		ArrayList<HiveConcept> hiveConcepts = new ArrayList();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Set<String> pathSet = FacesContext.getCurrentInstance().getExternalContext().getResourcePaths("/");
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		MutableAttributeMap  conversation = reqContext.getConversationScope();
		vocabulary = (HiveVocabularyImpl)session.getAttribute("meshvocabulary"); 
*/		
//		vocabulary = (HiveVocabularyImpl)sessionMap.get("meshvocabulary");
//		vocabulary = (HiveVocabularyImpl)conversation.get("meshVocabulary");
		System.out.println("Tablewebservice: initialisering concepts");
		//modelObj.setMelding(melding);
	
		Map<String,QName> concepts = (TreeMap) hiveService.getVocabulary().findAllConcepts(false);
//		QName qname = new QName("http://www.nlm.nih.gov/mesh/D000715#concept", "");
		QName qname = new QName("http://www.nlm.nih.gov/mesh/D001690#concept", "");	
		HiveConcept concept = null; 
	
		try {
			concept = hiveService.getVocabulary().findConcept(qname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*			
		for (Map.Entry entry : concepts.entrySet()){
		
			 System.out.println("Key = " + entry.getKey() + ", Value = " +
                     entry.getValue());
			 nameSpace = (QName)entry.getValue();
			 System.out.println("Key = "+nameSpace.getNamespaceURI());
	
			 nameSpace = (QName)entry.getValue();
			 try {
					concept = vocabulary.findConcept(nameSpace);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (concept != null){
					List broad = concept.getBroaderConcepts();
					if (broad.isEmpty() || broad.size() == 0)
						hiveConcepts.add(concept);
			}
		}
*/			
//		System.out.println("Pref label = "+concept.getPrefLabel());
	
		return concepts;
	}


	/**
	 * collectScope
	 * Denne rutinen setter sammen alle objektene som er aktive i flow
	 * Den kalles når flow starter
	 */
	public ArrayList collectScope(){

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		Map<String,QName> concepts = hiveService.getConcepts();
		ArrayList<HiveConcept> hiveConcepts = new ArrayList();
		QName nameSpace = null;
		
		Collection values = concepts.values();
		HiveConcept concept = null; 
		for (Map.Entry entry : concepts.entrySet()){
/*			
			 System.out.println("Key = " + entry.getKey() + ", Value = " +
                     entry.getValue());
			 nameSpace = (QName)entry.getValue();
			 System.out.println("Key = "+nameSpace.getNamespaceURI());
*/		
			 nameSpace = (QName)entry.getValue();
			 try {
					concept = hiveService.getVocabulary().findConcept(nameSpace);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (concept != null){
					List broad = concept.getBroaderConcepts();
					if (broad.isEmpty() || broad.size() == 0)
						hiveConcepts.add(concept);
			}
		}

		this.hiveConcepts = hiveConcepts;
		return hiveConcepts;
	}

	public Meshtree initializeMeshtree(){

		Map<String,QName> concepts = hiveService.getConcepts();
	
		Meshtree meshTree = new Meshtree(hiveConcepts, concepts);
		meshTree.setTreeElements(hiveService.getTreeElements());
		meshTree.setAnatomi(hiveService.getAnatomi());
		return meshTree;
		
	}


	/**
	 * getmeshDetail
	 * Denne rutinen henter detaljer om et valgt MeSH begrep
	 * Den utføres når bruker velger en MeSH term
	 * @return
	 */
	public String getmeshDetail(){
		String kurSId = getFacesParamValue("qnameid");
	
		Meshtree meshTree = initializeMeshtree();
	
		HiveConcept concept = null; 
		if (!kurSId.contains("concept"))
			kurSId = kurSId + "concept";
		QName qname = new QName(kurSId, "");
		try {
			concept =  hiveService.getVocabulary().findConcept(qname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (concept != null){
			meshTree.setChosenConcept(concept);
			meshTree.setConceptDetail(true);
			meshTree.setNarrowConcepts(hiveService.findconcepts(concept));
			if(concept.getBroaderConcepts() != null && !concept.getBroaderConcepts().isEmpty()){
				meshTree.setBroaderConcepts(hiveService.findBroader(concept));
				
			}
		}
		return "detail";
	}
	
	/**
	 * getToptreeDetail
	 * Denne rutinene henter MeSH termer samlet under en hovedterm
	 * Den utføres når bruker har valgt en hoverterm (f. eks. anatomi)
	 * @return
	 */
	public String getToptreeDetail(){
		String treeTop = getFacesParamValue("treeName");

		Meshtree meshTree = initializeMeshtree();
		ArrayList<SemantiskTree> trees = meshTree.getTrees();
		int i = 0;
		SemantiskTree tree = null;
		String baseName = "";
		do
		{
			tree = trees.get(i);
			baseName = tree.getBaseName();
			i++;
		}while(i<trees.size() && !baseName.equals(treeTop));
		if (tree != null)
			meshTree.setChosenTree(tree);
		return "meshdetail";
	}

	
	/**
	 * presentNarrow
	 * Denne rutinen viser underordnede begreper eller skjuler dem.
	 * @return
	 */
	public String presentNarrow(){

		Meshtree meshTree = initializeMeshtree();
		meshTree.setBroader(false);
		meshTree.setBroaderText(false);
		if (meshTree.getNarrowConcepts() != null){
			if(meshTree.isNarrow())
				meshTree.setNarrow(false);
			else
				meshTree.setNarrow(true);
		}
		if (meshTree.getNarrowConcepts() == null){
			if(meshTree.isNarrowText())
				meshTree.setNarrowText(false);
			else
				meshTree.setNarrowText(true);
		}
		
		return "";
	}

	/**
	 * presentBroader
	 * Denne rutinen viser overordnede begreper eller skjuler dem.
	 * @return
	 */
	public String presentBroader(){

		Meshtree meshTree = initializeMeshtree();
		meshTree.setNarrow(false);
		meshTree.setNarrowText(false);
		if (meshTree.getBroaderConcepts() != null){
			if(meshTree.isBroader())
				meshTree.setBroader(false);
			else
				meshTree.setBroader(true);
		}
		if (meshTree.getBroaderConcepts() == null){
			if(meshTree.isBroaderText())
				meshTree.setBroaderText(false);
			else
				meshTree.setBroaderText(true);
		}
		return "";
	}
	/**
	 * getSearch
	 * Denne rutinen søker etter en gitt term utfra tekst
	 * @return
	 */
	public String getSearch(){

		Meshtree meshTree = initializeMeshtree();
	
		return "";
	}	public NHNServiceClient getNhnClient() {
		return nhnClient;
	}

	public void setNhnClient(NHNServiceClient nhnClient) {
		this.nhnClient = nhnClient;
	
		nhnClient.setNhnFlag(nhnFlag);
		nhnClient.initializeTables();
		organisationName = nhnClient.getOrganisationName();
	}

	

	public HiveService getHiveService() {
		return hiveService;
	}
	public void setHiveService(HiveService hiveService) {
		this.hiveService = hiveService;
		this.hiveService.initializeVocabulary();
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


	public ArrayList<HiveConcept> getHiveConcepts() {
		return hiveConcepts;
	}
	public void setHiveConcepts(ArrayList<HiveConcept> hiveConcepts) {
		this.hiveConcepts = hiveConcepts;
	}
	public void hendelseHiddenValue(ValueChangeEvent val){
		String strHendelse =(String) val.getNewValue();
		int x = 0;
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
