package no.naks.biovigilans.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.PasientImpl;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;
import no.naks.biovigilans.web.xml.Term;
import no.naks.biovigilans.web.xml.Title;

import javax.xml.bind.JAXBElement;

import org.restlet.data.Parameter;

/**
 * @author olj
 * Denne klassen representerer en pasientkomplikasjon ved blodoverføring 
 * Klassen kan også være kilde til nedtrekksvalg for htmlsidene.
 * Disse valgene kan hentes fra andre kilder
 */
public class PasientKomplikasjon extends VigilansModel{

	private Pasient pasient;
	private String[] aldergruppe;
	private String[] kjonnValg; 
	private String mann;
	private String kvinne;
	private String antiStoffjanei;
	private String [] antiStofflabel;
	private String [] antiStoffid;
	private String sykdom;
	private String transfusjon;
	private String transfusjonKomplikasjon;
	private List<MainTerm> terms;
	private List<MainTerm> icd10Elements;
	private List<Title> titles;

	private List<String> icd10Codes;
	private List<String> mainTerms; 
	
	private List<ICD10Component> components;
	private Map<String,Object > mapComponent;
	
	
	public PasientKomplikasjon() {
		super();
		pasient = new PasientImpl();
		icd10Elements = new ArrayList<MainTerm>();
		icd10Codes = new ArrayList<String>();
		mainTerms = new ArrayList<String>();
		titles = new ArrayList<Title>();
	
		components = new ArrayList<ICD10Component>();
		mapComponent = new HashMap<String, Object>();
		
		// TODO Auto-generated constructor stub
	}
	
	
	public String getMann() {
		return mann;
	}


	public void setMann(String mann) {
		this.mann = mann;
	}


	public String getKvinne() {
		return kvinne;
	}


	public void setKvinne(String kvinne) {
		this.kvinne = kvinne;
	}


	public Pasient getPasient() {
		return pasient;
	}


	public void setPasient(Pasient pasient) {
		this.pasient = pasient;
	}


	public String[] getAldergruppe() {
		return aldergruppe;
	}


	public void setAldergruppe(String[] aldergruppe) {
		this.aldergruppe = aldergruppe;
	}


	public String[] getKjonnValg() {
		return kjonnValg;
	}


	public void setKjonnValg(String[] kjonnValg) {
		this.kjonnValg = kjonnValg;
		kvinne = kjonnValg[0];
		mann = kjonnValg[1];
	}


	public String getAntiStoffjanei() {
		return antiStoffjanei;
	}
	public void setAntiStoffjanei(String antiStoffjanei) {
		this.antiStoffjanei = antiStoffjanei;
	}
	public String[] getAntiStofflabel() {
		return antiStofflabel;
	}
	public void setAntiStofflabel(String[] antiStofflabel) {
		this.antiStofflabel = antiStofflabel;
	}
	public String[] getAntiStoffid() {
		return antiStoffid;
	}
	public void setAntiStoffid(String[] antiStoffid) {
		this.antiStoffid = antiStoffid;
	}
	public String getSykdom() {
		return sykdom;
	}
	public void setSykdom(String sykdom) {
		this.sykdom = sykdom;
	}
	public String getTransfusjon() {
		return transfusjon;
	}
	public void setTransfusjon(String transfusjon) {
		this.transfusjon = transfusjon;
	}
	public String getTransfusjonKomplikasjon() {
		return transfusjonKomplikasjon;
	}
	public void setTransfusjonKomplikasjon(String transfusjonKomplikasjon) {
		this.transfusjonKomplikasjon = transfusjonKomplikasjon;
	}
	public List<MainTerm> getTerms() {
		return terms;
	}
	
	public List getIcd10Elements() {
		return icd10Elements;
	}
	public void setIcd10Elements(List icd10Elements) {
		this.icd10Elements = icd10Elements;
	}
	public List<Title> getTitles() {
		return titles;
	}
	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public List<String> getIcd10Codes() {
		return icd10Codes;
	}
	public void setIcd10Codes(List<String> icd10Codes) {
		this.icd10Codes = icd10Codes;
	}
	public List<String> getMainTerms() {
		return mainTerms;
	}
	public void setMainTerms(List<String> mainTerms) {
		this.mainTerms = mainTerms;
	}
	
	public List<ICD10Component> getComponents() {
		return components;
	}
	public void setComponents(List<ICD10Component> components) {
		this.components = components;
	}
	

	public Map<String, Object> getMapComponent() {
		return mapComponent;
	}
	public void setMapComponent(Map<String, Object> mapComponent) {
		this.mapComponent = mapComponent;
	}
	/**
	 * setTerms
	 * Denne rutinen setter opp ICD10 koder
	 * @param terms
	 */
	public void setTerms(List<MainTerm> terms) {
		this.terms = terms;
		 icd10Elements.addAll(terms);
	     for (MainTerm term : terms){
	    	 Title localTitle = term.getTitle();
	    	 List<Serializable>titler = localTitle.getContent();
	    	 String first = (String)titler.get(0);
	    	 String second = "";
	    	 JAXBElement otherElement = null;
	    	 if (titler.size()>=2)
	    		 otherElement = (JAXBElement)titler.get(1);
	    	if (otherElement != null)
	    		second = (String) otherElement.getValue();
//	    	 System.out.println("Titler "+first+" "+second);
	    	 String code = term.getCode();
	    	 if (code != null)
	    		 mainTerms.add(first+second+ " "+code);
	     }
	     

	}
	/**
	 * saveValues
	 * Denne rutinen lagrer feltverdier for pasientkomplikasjoner som er angitt av bruker 
	 */
	public void saveValues() {
		String[] formFields = getFormNames();
		Map<String,String> userEntries = getFormMap();
		int index = 0;
		for (String field : formFields){
			if (userEntries.containsKey(field)){
				pasient.setKjonn(userEntries.get(field));
			}
		}
	}
	
	
}
