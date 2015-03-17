package no.naks.biovigilans.dao;

import java.util.List;

public interface SaksbehandlingDAO {

	public List collectMessages();
	
	public String[] getVigilandsMeldingTableDefs();


	public void setVigilandsMeldingTableDefs(String[] vigilandsMeldingTableDefs);


	public String getSelectvigilansMeldingSQL();


	public void setSelectvigilansMeldingSQL(String selectvigilansMeldingSQL);

}
