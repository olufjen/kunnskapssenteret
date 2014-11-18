package no.naks.rammeverk.kildelag.dao;

import java.util.Properties;

/**
 * @author olj
 * Denne klassen og konkrete subklasser benyttes til ø lese og endre pø .property filer
 */
public abstract class AbstractPropertyDAO {

	protected Properties property;

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}
	
}
