package no.naks.biovigilans.web.control;

import java.io.File;
import java.util.List;

import javax.xml.bind.Unmarshaller;

import no.naks.biovigilans.web.xml.ICD10CMIndex;
import no.naks.biovigilans.web.xml.Letter;
import no.naks.biovigilans.web.xml.MainTerm;

public interface ICD10WebService {
	public Unmarshaller getJaxbUnmarshaller();

	public void setJaxbUnmarshaller(Unmarshaller jaxbUnmarshaller);
	public void readXml();
	public String getXmlFillocation();

	public void setXmlFillocation(String xmlFillocation);
	

	public File getXmlFile();

	public void setXmlFile(File xmlFile);

	public List<MainTerm> getTerms();

	public void setTerms(List<MainTerm> terms);

	public List<Letter> getLetters();

	public void setLetters(List<Letter> letters);


	public ICD10CMIndex getIcd10Index();

	public void setIcd10Index(ICD10CMIndex icd10Index);


}
