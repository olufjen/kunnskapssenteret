/**
 * Denne klassen er ansvarlig for
 */
package no.naks.rammeverk.kildelag.dao;

import org.springframework.jdbc.core.SqlParameter;

/**
 * @author ojn
 *
 */
public interface Tablesupdate {
	public void insert(Object params[]);
	public void updateRow(Object params[]); 
	public void declareParameter(SqlParameter par);
	public void setSql(String sql);
	public void setTypes(int[]types);
	public void delete(long id);

}