package no.naks.biovigilans.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

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
	private VigilansSelect vigilansSelect = null;
	private String[] vigilandsMeldingTableDefs;
	private String selectvigilansMeldingSQL;
	
	
	public String[] getVigilandsMeldingTableDefs() {
		return vigilandsMeldingTableDefs;
	}
	public void setVigilandsMeldingTableDefs(String[] vigilandsMeldingTableDefs) {
		this.vigilandsMeldingTableDefs = vigilandsMeldingTableDefs;
	}
	public String getSelectvigilansMeldingSQL() {
		return selectvigilansMeldingSQL;
	}
	public void setSelectvigilansMeldingSQL(String selectvigilansMeldingSQL) {
		this.selectvigilansMeldingSQL = selectvigilansMeldingSQL;
	}
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
	public List<Vigilansmelding> collectMeldinger(Long melderId){
		int type = Types.INTEGER;
//		Map alleMeldinger = new HashMap<String,List>();
		vigilansSelect = new VigilansSelect(getDataSource(),selectvigilansMeldingSQL,vigilandsMeldingTableDefs);
		vigilansSelect.declareParameter(new SqlParameter(type));
		List meldinger = vigilansSelect.execute(melderId);
		return meldinger;
	}
}
