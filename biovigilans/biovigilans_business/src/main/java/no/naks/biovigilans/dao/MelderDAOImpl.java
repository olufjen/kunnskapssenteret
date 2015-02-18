package no.naks.biovigilans.dao;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.model.Melder;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

public class MelderDAOImpl extends AbstractAdmintablesDAO  implements MelderDAO {
	
	private String insertMelderSQL;
	private String updateMelderSQL;
	private String selectMeldingSQL;
	private String melderPrimaryKey;
	private String[] melderprimarykeyTableDefs;
	private String[] vigilandsMeldingTableDefs;
	private String selectvigilansMeldingSQL;
	
	private Tablesupdate tablesUpdate = null;
	private VigilansSelect vigilansSelect = null;
	
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
	
	public String getInsertMelderSQL() {
		return insertMelderSQL;
	}
	public void setInsertMelderSQL(String insertMelderSQL) {
		this.insertMelderSQL = insertMelderSQL;
	}
	public String getMelderPrimaryKey() {
		return melderPrimaryKey;
	}
	public void setMelderPrimaryKey(String melderPrimaryKey) {
		this.melderPrimaryKey = melderPrimaryKey;
	}
	public String[] getMelderprimarykeyTableDefs() {
		return melderprimarykeyTableDefs;
	}
	public void setMelderprimarykeyTableDefs(String[] melderprimarykeyTableDefs) {
		this.melderprimarykeyTableDefs = melderprimarykeyTableDefs;
	}
	public Tablesupdate getTablesUpdate() {
		return tablesUpdate;
	}
	public void setTablesUpdate(Tablesupdate tablesUpdate) {
		this.tablesUpdate = tablesUpdate;
	}
	
	public String getUpdateMelderSQL() {
		return updateMelderSQL;
	}
	public void setUpdateMelderSQL(String updateMelderSQL) {
		this.updateMelderSQL = updateMelderSQL;
	}
	
	public String getSelectMeldingSQL() {
		return selectMeldingSQL;
	}
	public void setSelectMeldingSQL(String selectMeldingSQL) {
		this.selectMeldingSQL = selectMeldingSQL;
	}
	public void saveMelder(Melder melder){
		melder.setParams();
		int[] types= melder.getTypes();
		Object[] params = melder.getParams();
		String sql=insertMelderSQL;
		Long id = melder.getMelderId();
		if(id!=null){
			sql = updateMelderSQL;
			types = melder.getUtypes();
		}
		
		tablesUpdate = new TablesUpdateImpl(getDataSource(), sql, types);
		tablesUpdate.insert(params);
		
		if(id==null){
			melder.setMelderId(getPrimaryKey(melderPrimaryKey,melderprimarykeyTableDefs));
		}
		
	}
	
	public List<Map<String, Object>> selectMelder(String epost){
		String sql = selectMeldingSQL;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,epost);
		return rows;
	}
	/**
	 * selectMeldinger
	 * Denne rutinen henter alle meldinger til en melder basert på en meldingsnøkkel
	 * @param meldingsNokkel
	 * @return
	 */
	public List selectMeldinger (String meldingsNokkel){
		int type = Types.VARCHAR;
		vigilansSelect = new VigilansSelect(getDataSource(),selectvigilansMeldingSQL,vigilandsMeldingTableDefs);
		vigilansSelect.declareParameter(new SqlParameter(type));
		List meldinger = vigilansSelect.execute(meldingsNokkel);
		return meldinger;
	}
	
}	
