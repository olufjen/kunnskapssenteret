package no.naks.biovigilans.web.control;
import java.io.File;  
import java.util.ArrayList;  
import java.util.List;

import javax.xml.bind.JAXBContext;  
import javax.xml.bind.JAXBException;  
import javax.xml.bind.Unmarshaller; 

import no.naks.biovigilans.web.xml.ICD10CMIndex;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

/**
 * @author olj
 * Denne klassen leser xml definisjoner av ICD10 og gjør disse tilgjengelig for søk og oppslag
 */
public class ICD10WebServiceImpl implements ICD10WebService {

	private Unmarshaller jaxbUnmarshaller ;
	private String xmlFillocation;
	private File xmlFile;
	private List<MainTerm> terms;
	private List<Letter> letters; 
	private ICD10CMIndex icd10Index;
	
	public ICD10WebServiceImpl() {
		super();
		terms = new ArrayList<MainTerm>();
		
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance(ICD10CMIndex.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		try {
			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 System.out.println("ICD10 webservice created");
	}

	public Unmarshaller getJaxbUnmarshaller() {
		return jaxbUnmarshaller;
	}

	public void setJaxbUnmarshaller(Unmarshaller jaxbUnmarshaller) {
		this.jaxbUnmarshaller = jaxbUnmarshaller;
	}

	public String getXmlFillocation() {
		return xmlFillocation;
	}

	public void setXmlFillocation(String xmlFillocation) {
		this.xmlFillocation = xmlFillocation;
	}
	

	public File getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public List<MainTerm> getTerms() {
		return terms;
	}

	public void setTerms(List<MainTerm> terms) {
		this.terms = terms;
	}
	

	public List<Letter> getLetters() {
		return letters;
	}

	public void setLetters(List<Letter> letters) {
		this.letters = letters;
	}



	@Override
	public void readXml() {
		 xmlFile = new File(xmlFillocation);  
		try {
			icd10Index = (ICD10CMIndex) jaxbUnmarshaller.unmarshal(xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		letters = icd10Index.getLetter();
		
	}

	@Override
	public ICD10CMIndex getIcd10Index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIcd10Index(ICD10CMIndex icd10Index) {
		// TODO Auto-generated method stub
		
	}
	
	
}
