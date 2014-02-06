package no.naks.semweb.control;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.xml.namespace.QName;

import edu.unc.ils.mrc.hive.HiveException;
import edu.unc.ils.mrc.hive.api.impl.elmo.SKOSSchemeImpl;
import edu.unc.ils.mrc.hive2.api.HiveConcept;
import edu.unc.ils.mrc.hive2.api.impl.HiveVocabularyImpl;

public interface HiveService {

	public void initializeVocabulary();
	public SKOSSchemeImpl getScheme();
	public void setScheme(SKOSSchemeImpl scheme);
	public HiveVocabularyImpl getVocabulary();
	public void setVocabulary(HiveVocabularyImpl vocabulary);
	public String getHivePath();
	public void setHivePath(String hivePath);
	public Map<String, QName> getConcepts();
	public void setConcepts(Map<String, QName> concepts);
	public String[] getTreeElements();
	public void setTreeElements(String[] treeElements);
	
	public String[] getAnatomi();
	public void setAnatomi(String[] anatomi);
	public ArrayList<HiveConcept> findconcepts(HiveConcept concept);
	public ArrayList<HiveConcept> findBroader(HiveConcept concept);
}
