
package no.naks.rammeverk.kildelag.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import no.naks.rammeverk.kildelag.model.LineKey;
import no.naks.rammeverk.kildelag.model.LineKeyImpl;

/**
 *
 * Denne klassen er ansvarlig for
 * oppslag mot tabeller representerer mange til mange relasjonen mellom to tabeller
 * @author ojn
 *
 */
public class LineKeySelect extends AbstractSelect {

	
	public LineKeySelect(DataSource dataSource, String sql, String[] tableDefs) {
		super(dataSource, sql, tableDefs);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	protected Object mapRow(ResultSet rs, int index) throws SQLException {
		LineKey linekey = new LineKeyImpl();
		linekey.setPrimaryKey(new Long(rs.getLong(tableDefs[0])));
		linekey.setTableoneKey(new Long(rs.getLong(tableDefs[1])));
		linekey.setTabletwoKey(new Long(rs.getLong(tableDefs[2])));
		return linekey;
	}

}
