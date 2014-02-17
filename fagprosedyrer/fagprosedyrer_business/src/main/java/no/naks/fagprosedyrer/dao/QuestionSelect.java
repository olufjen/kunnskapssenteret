/**
 * 
 */
package no.naks.skjemabank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import no.naks.rammeverk.kildelag.dao.AbstractSelect;
import no.naks.skjemabank.model.Question;
import no.naks.skjemabank.model.QuestionImpl;

/**
 * @author ojn
 *
 */
public class QuestionSelect extends AbstractSelect {

	
	/**
	 * @param dataSource
	 * @param sql
	 * @param tableDefs
	 */
	public QuestionSelect(DataSource dataSource, String sql, String[] tableDefs) {
		super(dataSource, sql, tableDefs);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
	 */  
	@Override
	protected Object mapRow(ResultSet rs, int index) throws SQLException {
		Question question = new QuestionImpl();
		
		question.setSporsmalid(new Long(rs.getLong(tableDefs[0])));
		question.setPartid(new Long(rs.getLong(tableDefs[1])));
		question.setPartid(resetLong(question.getPartid()));
		question.setSporsmalTekst(new String(rs.getString(tableDefs[2])));
		question.setKortversjonTekst(new String(rs.getString(tableDefs[3])));
		question.setNotes(new String(rs.getString(tableDefs[4])));
		question.setShowNotes(new Integer(rs.getInt(tableDefs[5])));
		question.setQuestionNumber(new Integer(rs.getInt(tableDefs[6])));
		
		return question;
	}

}
