package no.naks.biovigilans.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import no.naks.biovigilans.model.Giverkomplikasjon;
import no.naks.biovigilans.model.GiverkomplikasjonImpl;
import no.naks.rammeverk.kildelag.dao.AbstractSelect;

public class GiverkomplikasjonSelect extends AbstractSelect {

	public GiverkomplikasjonSelect(DataSource dataSource, String sql,
			String[] tableDefs) {
		super(dataSource, sql, tableDefs);
		
	}

	@Override
	protected Object mapRow(ResultSet rs, int index) throws SQLException {
		Giverkomplikasjon melding = new GiverkomplikasjonImpl();
		melding.setMeldeid(new Long(rs.getLong(tableDefs[0])));
		melding.setDonasjonid(new Long(rs.getLong(tableDefs[1])));
		String stedforkomplikasjon =  rs.getString(tableDefs[2]);
		if (stedforkomplikasjon != null)
			melding.setStedforkomplikasjon(stedforkomplikasjon);
		
		return null;
	}

}
