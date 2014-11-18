
package no.naks.rammeverk.kildelag.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 
 * Denne klassen er ansvarlig for ø hente frem en primørnøkkel fra en valgt tabell 
  * @author ojn
 *
 */
public class PrimarykeySelect extends AbstractSelect {

	/**
	 * @param dataSource
	 * @param sql
	 * @param tableDefs
	 */
	public PrimarykeySelect(DataSource dataSource, String sql, String[] tableDefs) {
		super(dataSource, sql, tableDefs);
	
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	protected Object mapRow(ResultSet rs, int index) throws SQLException {
		
		Long primaryKey = rs.getLong(tableDefs[0]);
		return primaryKey;
	}

}
