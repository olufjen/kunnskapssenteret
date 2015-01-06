package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

/**
 * Denne klassen håndterer DAO mot tabellen vigilansmelding
 * @author olj
 *
 */
public class HendelsehemovigilansDAOImpl extends AbstractAdmintablesDAO
		implements HendelsehemovigilansDAO {
	private Tablesupdate tablesUpdate = null;
	private String updateVigilansmeldingSQL;
	
	public String getUpdateVigilansmeldingSQL() {
		return updateVigilansmeldingSQL;
	}
	public void setUpdateVigilansmeldingSQL(String updateVigilansmeldingSQL) {
		this.updateVigilansmeldingSQL = updateVigilansmeldingSQL;
	}
	
	public void updateVigilansMelding(Vigilansmelding melding){
		
		melding.setMelderParams();
		melding.setMelderTypes();
		int[] types = melding.getTypes();
		Object[] params = melding.getParams();
		String sql = updateVigilansmeldingSQL;
		
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
	}
}
