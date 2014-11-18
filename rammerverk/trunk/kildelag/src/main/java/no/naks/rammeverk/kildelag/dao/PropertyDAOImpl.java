package no.naks.rammeverk.kildelag.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author olj
 * Denne klassen implementerer AbstractPropertyDAO
 * Det gjør det mulig ø lese/skrive og endre pø properties i et property file
 * 
 */
public class PropertyDAOImpl extends AbstractPropertyDAO implements PropertyDAO {
	
	private String propFilename; // Propertyfile to read/write
	private InputStream inStream;
	private String pathFile;	// Path to property file
	private static Log log = LogFactory.getLog(PropertyDAOImpl.class);
	
/*	
	 String string = properties.getProperty("a.b");
	    properties.setProperty("a.b", "new value");
*/



	public PropertyDAOImpl() {
		super();
		property = new Properties();
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getPropFilename() {
		return propFilename;
	}

	public void setPropFilename(String propFilename) {
		this.propFilename = propFilename;
	}
	/**
	 * loadPropertyfile
	 * Denne rutinen henter properties fra et property file
	 */
	public void loadPropertyfile(InputStream inStream){
			this.inStream = inStream;
		   try {
			   property.load(inStream);
//		        property.load(inStream(propFilename));
		    } catch (IOException e) {
		    	log.info("Cannot load file " + propFilename + " " + e.getMessage()  );
		    	
		    }
		    log.info("Loading property "+property.getProperty("study"));
	}
	
/*	@Override
	public void loadPropertyfile() {
		 try {
		        property.load(new FileInputStream(propFilename));
		    } catch (IOException e) {
		    	log.info("Cannot load file " + propFilename + " " + e.getMessage()  );
		    }
			log.info(property.
		
	}
*/
	/**
	 * storePropery
	 * Denne rutinen lagrer et property file
	 */
	public void storeProperyfile(){
		  // Write properties file.
		FileOutputStream out = null;
//		String fName = pathFile + propFilename;
		String fName = propFilename;
		try {
			out = new FileOutputStream(fName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	        property.store(out,"For application "+pathFile);
//	        out.flush();
//	        log.info("flushing property "+property.getProperty("study"));
//	        out.close();
	        log.info("closing outputstream "+property.getProperty("study")+ " "+propFilename);
	    } catch (IOException e) {
	    	log.info("Cannot store file " + propFilename + " " + e.getMessage()  );
	    	
	    }
	    log.info("saving property "+property.getProperty("study"));
	}
	/**
	 * changProperty
	 * Denne rutinen setter en property med ny verdi
	 * @param propertyName Navn pø property
	 * @param newValue Ny verdi for property
	 */
	public void changeProperty(String propertyName,String newValue){
		property.setProperty(propertyName, newValue);
	   log.info("changing property "+property.getProperty("study"));
	}
	
	
}
