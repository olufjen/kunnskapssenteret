package no.helsebiblioteket.evs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Common used helper methods.
 */
public final class Helper {
    
	public static void prettyPrint(org.w3c.dom.Document doc, OutputStream out) {
		TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer serializer;
        try {
            serializer = tfactory.newTransformer();
            //Setup indenting to "pretty print"
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            
            serializer.transform(new DOMSource(doc), new StreamResult(out));
        } catch (TransformerException e) {
            // this is fatal, just dump the stack and throw a runtime exception
            e.printStackTrace();
            
            throw new RuntimeException(e);
        }
	}

	/**
	 * Pretty print document.
	 */
	public static void prettyPrint(Document doc)
		throws IOException
	{
		prettyPrint(doc, System.out);		
	}
	
	/**
	 * Pretty print document.
	 */
	public static void prettyPrint(Document doc, OutputStream out)
		throws IOException
	{
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		outputter.output(doc, out);		
	}	
	
	/**
	 * Copy input to output. Return number of bytes copied.
	 */
	public static int copy(InputStream in, OutputStream out)
		throws IOException
	{
		int copied = 0;
		byte[] buffer = new byte[1024];
		
		while (true) {
			int num = in.read(buffer);
			if (num > 0) {
				out.write(buffer, 0, num);
				copied += num;
			} else {
				break;
			}
		}
		
		return copied;
	}
	
	/**
	 * Parse xml document.
	 */
	public static Document parseXml(InputStream in) 
		throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		return builder.build(in);
	}	
	
	

	/**
	 * Copy to bytes. 
	 */
	public static byte[] copyToBytes(InputStream in)
		throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}
	
	/**
	 * Load text file.
	 */
	public static String copyToString(InputStream in)
		throws IOException
	{
		byte[] bytes = copyToBytes(in);
		return new String(bytes, "UTF-8");
	}
}
