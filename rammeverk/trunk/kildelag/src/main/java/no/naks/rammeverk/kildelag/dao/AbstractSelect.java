package no.naks.rammeverk.kildelag.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;;;
/**
 *  Denne klassen er superklasse for Selectklasser som henter data fra tabeller i databasen
 * @author ojn
 *
 */
public abstract class AbstractSelect extends MappingSqlQuery {
	protected String[]tableDefs;
	
	public String[] getTableDefs() {
		return tableDefs;
	}

	public void setTableDefs(String[] tableDefs) {
		this.tableDefs = tableDefs;
	}
	public AbstractSelect(DataSource dataSource,String sql,String[] tableDefs) {
		super(dataSource,sql);
		this.tableDefs = tableDefs;
	}
	/**
	 * resetLong
	 * This routine resets foregn keys from '0' value to null value
	 * @param key
	 * @return
	 */
	protected Long resetLong(Long key){
		Long result = null;
		if (key != null && key.longValue() > 0)
			result = key;
		
		return result;
	}



}
