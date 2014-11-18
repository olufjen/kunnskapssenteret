/**
*
 */
package no.naks.rammeverk.kildelag.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

/**
 * Denne klassen er ansvarlig for alle tabelloppdateringer gjennom bruk av superklassen SqlUpdate
 * @author ojn
 *
 */
public class TablesUpdateImpl extends SqlUpdate implements Tablesupdate {

	
	public TablesUpdateImpl(DataSource source,String sql) {
		super(source,sql);
		
		// TODO Auto-generated constructor stub
	}
	public TablesUpdateImpl(DataSource source,String sql,int[] types) {
		super(source,sql,types);
		
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see no.naks.business.dao.Tablesupdate#insert(java.lang.Object[])
	 */
	public void insert(Object[] params) {
		// TODO Auto-generated method stub
		update(params);
		
	}

	/* (non-Javadoc)
	 * @see no.naks.business.dao.Tablesupdate#update(java.lang.Object[])
	 */
	public void updateRow(Object[] params) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * delete 
	 * This routine removes a particular number of records from the specified table
	 * @param id
	 */
	public void delete(long id){
		update(id);
	}
}
