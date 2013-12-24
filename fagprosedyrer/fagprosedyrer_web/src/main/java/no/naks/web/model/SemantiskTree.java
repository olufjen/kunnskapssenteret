package no.naks.web.model;

import java.io.Serializable;
import java.util.ArrayList;

import edu.unc.ils.mrc.hive2.api.HiveConcept;

/**
 * SemantiskTree
 * Denne klassen inneholder MeSH termer knyttet til et ferdig definert begrepsapparat. Dette begrepsapparatet er definert med følgende termer:
 * 			Anatomi
			Organisme
			Sykdommer
			Kjemikalier og medisiner
			Analyse, diagnoser og terapeutiske teknikker og utstyr
			Psykiatri og Psykologi
			Antropologi Utdanning og sosiologi
			Teknologi, Industri, Jordbruk
			Humanoria
			Informasjonsvitenskap
			Navngitte grupper
			Helsevesenet
			Publikasjonkarakteristiska
			Geografi
 * @author olj
 *
 */
public class SemantiskTree implements Serializable{

	private String baseName; //Definert begrep (f. eks. Anatomi)
	private ArrayList<HiveConcept> hiveConcepts; // MeSH termer som tilhører Anatomi
	
	
	public SemantiskTree() {
		super();
		// TODO Auto-generated constructor stub
		hiveConcepts = new ArrayList();
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public ArrayList<HiveConcept> getHiveConcepts() {
		return hiveConcepts;
	}
	public void setHiveConcepts(ArrayList<HiveConcept> hiveConcepts) {
		this.hiveConcepts = hiveConcepts;
	}
	
	
}
