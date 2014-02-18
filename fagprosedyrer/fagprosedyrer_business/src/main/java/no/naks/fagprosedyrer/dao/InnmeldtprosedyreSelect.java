/**
 * 
 */
package no.naks.fagprosedyrer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import no.naks.fagprosedyrer.model.Innmeldtprosedyre;
import no.naks.fagprosedyrer.model.InnmeldtprosedyreImpl;
import no.naks.rammeverk.kildelag.dao.AbstractSelect;

/**
 * @author ojn
 *
 */
public class InnmeldtprosedyreSelect extends AbstractSelect {

	
	/**
	 * @param dataSource
	 * @param sql
	 * @param tableDefs
	 */
	public InnmeldtprosedyreSelect(DataSource dataSource, String sql, String[] tableDefs) {
		super(dataSource, sql, tableDefs);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
	 */  
	@Override
	protected Object mapRow(ResultSet rs, int index) throws SQLException {
		Innmeldtprosedyre innmeldtProsedyre = new InnmeldtprosedyreImpl();
		
		innmeldtProsedyre.setInnmeldtprosedyre_Id(new Long(rs.getLong(tableDefs[0])));
		Date tDate = rs.getDate(tableDefs[1]);
		if (tDate != null)
			innmeldtProsedyre.setFerdigdato(tDate);
		Date pDate =  rs.getDate(tableDefs[2]);
		if (pDate != null)
			innmeldtProsedyre.setOppstartdato(pDate);
		String formal = rs.getString(tableDefs[3]);
		if (formal != null)
			innmeldtProsedyre.setFormal(formal);
		else 
			innmeldtProsedyre.setFormal("");
		return innmeldtProsedyre;
	}

}
