package no.helsebiblioteket.evs.plugin.result;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLTranslator {
	public static Document translate(org.w3c.dom.Document document) throws TransformerException, JDOMException, IOException {
		// TODO Fase2: Different JDOM classes. Fix!
		Source source = new DOMSource(document);
        StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.transform(source, result);
		
		SAXBuilder parser = new SAXBuilder();
    	StringReader sr = new StringReader(stringWriter.getBuffer().toString());
    	Document doc = parser.build(sr);
		return doc;
		
	}
}
