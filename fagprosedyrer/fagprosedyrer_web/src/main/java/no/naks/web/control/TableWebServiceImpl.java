package no.naks.web.control;

import java.security.Principal;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.webapp.filter.ExtensionsResponseWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import org.richfaces.component.html.HtmlMessage;
import org.richfaces.event.TreeSelectionChangeEvent; 
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
import no.naks.web.bean.PdfCreator;
import no.naks.web.model.Meshtree;


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

@RequestMapping("/") 
public class TableWebServiceImpl extends NHNMasterWebServiceImpl implements
		TableWebService,NHNMasterWebService,MasterWebService {
	protected MelderService meldingService; // Denne tjenesten sørger for lagring av meldinger til xml.
	

	
	public TableWebServiceImpl() {
		super();
		
		// TODO Auto-generated constructor stub
		 System.out.println("Tablewebservice created");
	}
	protected String getFacesParamValue(String name) {
	    return (String) FacesContext
	                        .getCurrentInstance()
	                            .getExternalContext()
	                                .getRequestParameterMap()
	                                    .get(name);
	
	}
	public void initializeVocabulary(){
		boolean firstTime = false;
		String vocabularyName = "mesh";
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
		sessionMap.put("meshvocabulary",vocabulary);
//		return vocabulary;
	}
	public Map<String,QName> initializeScheme(){
		boolean firstTime = false;
		String vocabularyName = "mesh";
		HiveVocabularyImpl vocabulary = null;
		ArrayList<HiveConcept> hiveConcepts = new ArrayList();
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Set<String> pathSet = FacesContext.getCurrentInstance().getExternalContext().getResourcePaths("/");
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		MutableAttributeMap  conversation = reqContext.getConversationScope();
		vocabulary = (HiveVocabularyImpl)sessionMap.get("meshvocabulary");
//		vocabulary = (HiveVocabularyImpl)conversation.get("meshVocabulary");
		System.out.println("Tablewebservice: initialisering concepts");
		//modelObj.setMelding(melding);
	
		Map<String,QName> concepts = (TreeMap) vocabulary.findAllConcepts(true);
//		QName qname = new QName("http://www.nlm.nih.gov/mesh/D000715#concept", "");
		QName qname = new QName("http://www.nlm.nih.gov/mesh/D001690#concept", "");	
		HiveConcept concept = null; 
	
		try {
			concept = vocabulary.findConcept(qname);
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
	public ArrayList collectScope(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		MutableAttributeMap  conversation = reqContext.getConversationScope();
		HiveVocabularyImpl vocabulary = null;
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	//	vocabulary = (HiveVocabularyImpl)conversation.get("meshVocabulary");
		vocabulary = (HiveVocabularyImpl)sessionMap.get("meshvocabulary");
		Map<String,QName> concepts = (Map)conversation.get("meshConcepts");
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

		
		return hiveConcepts;
	}

	public Meshtree initializeMeshtree(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		MutableAttributeMap  conversation = reqContext.getConversationScope();
		Map<String,QName> concepts = (Map)conversation.get("meshConcepts");
		ArrayList<HiveConcept> hiveConcepts = (ArrayList)conversation.get("hiveConcepts");
		
		Meshtree meshTree = new Meshtree(hiveConcepts, concepts);
		return meshTree;
		
	}
	public TreeNode produceTree(){
		RequestContext reqContext = RequestContextHolder.getRequestContext();
		MutableAttributeMap  flow = reqContext.getFlowScope();
		MutableAttributeMap  conversation = reqContext.getConversationScope();
		Meshtree meshTree = (Meshtree)conversation.get("meshTree");
		return meshTree.getRootNode();
	}

	public String getmeshDetail(){
		String kurSId = getFacesParamValue("qnameid");
		return "";
	}
	public void processSelection(TreeSelectionChangeEvent event) { 
/*	     
		HtmlTree tree = (HtmlTree) event.getComponent(); 
	       
		nodeTitle = (String) tree.getRowData(); 
	     
		selectedNodeChildren.clear(); 
	      
		TreeNode currentNode = tree.getModelTreeNode(tree.getRowKey()); 
	       
		if (currentNode.isLeaf()){ 
	            
		 
	           
		selectedNodeChildren.add((String)currentNode.getData()); 
	      
		}else
	       
		{ 
	            
		Iterator<Map.Entry<Object, TreeNode>> it = currentNode.getChildren(); 
	            
		while (it!=null &&it.hasNext()) { 
	                
		Map.Entry<Object, TreeNode> entry = it.next(); 
	            
		selectedNodeChildren.add(entry.getValue().getData().toString());  
	           
		} 
		       
		} 
*/	
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
