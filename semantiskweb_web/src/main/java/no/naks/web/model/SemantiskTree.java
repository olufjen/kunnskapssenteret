package no.naks.web.model;

import java.io.Serializable;
import java.util.ArrayList;

import edu.unc.ils.mrc.hive2.api.HiveConcept;

public class SemantiskTree implements Serializable{

	private String baseName;
	private ArrayList<HiveConcept> hiveConcepts;
	
	
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
