package no.naks.rammeverk.kildelag.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertyDAO
 * Dette grensesnittet representerer DAO for property filer
 * @author olj
 *
 */
public interface PropertyDAO {

	public Properties getProperty();

	public void setProperty(Properties property);

	public String getPropFilename();

	public void setPropFilename(String propFilename);
	/**
	 * loadPropertyfile
	 * Denne rutinen henter properties fra et property file
	 */

	/**
	 * storePropery
	 * Denne rutinen lagrer et property file
	 */
	public void storeProperyfile();
	/**
	 * changProperty
	 * Denne rutinen setter en proprty med ny ny verdi
	 * @param propertyName Navn pø property
	 * @param newValue Ny verdi for property
	 */
	public void changeProperty(String propertyName,String newValue);
	public void loadPropertyfile(InputStream inStream);
	public String getPathFile();

	public void setPathFile(String pathFile);
//	public void loadPropertyfile();
	
}
