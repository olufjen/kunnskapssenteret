package no.naks.web.control;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;

import edu.unc.ils.mrc.hive.HiveException;
import edu.unc.ils.mrc.hive.api.impl.elmo.SKOSSchemeImpl;
import edu.unc.ils.mrc.hive2.api.HiveConcept;
import edu.unc.ils.mrc.hive2.api.impl.HiveVocabularyImpl;

/**
 * @author olj
 * Denne klassen lager et vokabular 
 */
public class HiveServiceImpl implements HiveService {
	private SKOSSchemeImpl scheme = null;
	private HiveVocabularyImpl vocabulary;
	private String hivePath;
	private  Map<String,QName> concepts;
	private String[] treeElements; // Inneholder øverste definerte trestruktur for MeSH
	private String[] anatomi; // Inneholder øverste nivå definerte MeSH termer for anatomi
	
	public HiveServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("HiveService created");
	}
	/**
	 * buildConcept 
	 * Denne rutinen setter opp et Map object som hennolder alle MeSH termer henter fra vokabularet
	 */
	private void buildConcepts(){
		System.out.println("HiveService: initialisering concepts");
		//modelObj.setMelding(melding);
	
		concepts = (TreeMap) vocabulary.findAllConcepts(false);
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
	
	}
	
	/* 
	 * findConcept
	 * Denne rutinen henter frem alle smalere termer fra en gitt term.
	 * @see no.naks.web.control.HiveService#findconcepts(edu.unc.ils.mrc.hive2.api.HiveConcept)
	 */
	public ArrayList<HiveConcept> findconcepts(HiveConcept concept){
		ArrayList<String> concepts = (ArrayList)concept.getNarrowerConcepts();
		ArrayList<HiveConcept> narrowConcepts = new ArrayList<HiveConcept>();
		HiveConcept localConcept = null;
		for (String conceptName : concepts){
			QName qname = new QName(conceptName, "");
			try {
				localConcept = vocabulary.findConcept(qname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (localConcept != null){
				narrowConcepts.add(localConcept);
			}
		}
		return narrowConcepts;
	}
	
	/**
	 * findBroader
	 * Denne rutinen henter frem alle utvidede termer fra et gitt term
	 * @param concept Den gitte term
	 * @return
	 */
	public ArrayList<HiveConcept> findBroader(HiveConcept concept){
		ArrayList<String> concepts = (ArrayList)concept.getBroaderConcepts();
		ArrayList<HiveConcept> broaderConcepts = new ArrayList<HiveConcept>();
		HiveConcept localConcept = null;
		for (String conceptName : concepts){
			QName qname = new QName(conceptName, "");
			try {
				localConcept = vocabulary.findConcept(qname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (localConcept != null){
				broaderConcepts.add(localConcept);
			}
		}
		return broaderConcepts;
	}
	/* initializeVocabulary
	 * Denne rutinen henter frem valgt vokubalar
	 * 	 * @see no.naks.web.control.HiveService#initializeVocabulary()
	 */
	public void initializeVocabulary(){
		boolean firstTime = false;
		String vocabularyName = "mesh";
		vocabulary = null;
		ArrayList<HiveConcept> hiveConcepts = new ArrayList();

	
//		C:\svnroot2\nokc\semantiskweb\trunk\semantiskweb_web\src\main\webapp\WEB-INF\conf
//		String confPath = "c:/svnroot2/nokc/semantiskweb/trunk/semantiskweb_web/src/main/webapp/WEB-INF/conf/";
		String confPath = hivePath;
		SKOSSchemeImpl scheme = null;
		try {
			scheme = new SKOSSchemeImpl(confPath, vocabularyName, firstTime);
		} catch (HiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("HiveService: initialisering scheme");
		//modelObj.setMelding(melding);
		vocabulary = (HiveVocabularyImpl) scheme.getHiveVocabulary();
		buildConcepts();
	}
	
	public String[] getAnatomi() {
		return anatomi;
	}
	public void setAnatomi(String[] anatomi) {
		this.anatomi = anatomi;
	}
	public String[] getTreeElements() {
		return treeElements;
	}
	public void setTreeElements(String[] treeElements) {
		this.treeElements = treeElements;
	}
	public Map<String, QName> getConcepts() {
		return concepts;
	}
	public void setConcepts(Map<String, QName> concepts) {
		this.concepts = concepts;
	}
	public SKOSSchemeImpl getScheme() {
		return scheme;
	}
	public void setScheme(SKOSSchemeImpl scheme) {
		this.scheme = scheme;
	}
	public HiveVocabularyImpl getVocabulary() {
		return vocabulary;
	}
	public void setVocabulary(HiveVocabularyImpl vocabulary) {
		this.vocabulary = vocabulary;
	}
	public String getHivePath() {
		return hivePath;
	}
	public void setHivePath(String hivePath) {
		this.hivePath = hivePath;
	}
	
	
}
