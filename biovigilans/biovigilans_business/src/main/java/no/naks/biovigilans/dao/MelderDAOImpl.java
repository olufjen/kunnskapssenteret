package no.naks.biovigilans.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.model.Melder;
import no.naks.biovigilans.model.Vigilansmelding;
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
	private String selectannenKomplikasjonSQL;
	private String[] annenkomplikasjonTableDefs;
	private String selectpasientKomplikasjonSQL;
	private String[] pasientkomplikasjonTableDefs;
	
	
	private String pasientKey = "pasientKomp"; // Nøkkel dersom melding er av type pasientkomplikasjon
	private String giverKey = "giverkomp"; 	// Nøkkel dersom melding er at type giverkomplikasjon
	private String andreKey = "annenKomp";
	private String delMeldingKey = null;
	
	private Tablesupdate tablesUpdate = null;
	private VigilansSelect vigilansSelect = null;
	private AnnenkomplikasjonSelect annenmeldingSelect = null;
	private PasientkomplikasjonSelect pasientmeldingSelect = null;
	
	public String getSelectpasientKomplikasjonSQL() {
		return selectpasientKomplikasjonSQL;
	}
	public void setSelectpasientKomplikasjonSQL(String selectpasientKomplikasjonSQL) {
		this.selectpasientKomplikasjonSQL = selectpasientKomplikasjonSQL;
	}
	public String[] getPasientkomplikasjonTableDefs() {
		return pasientkomplikasjonTableDefs;
	}
	public void setPasientkomplikasjonTableDefs(
			String[] pasientkomplikasjonTableDefs) {
		this.pasientkomplikasjonTableDefs = pasientkomplikasjonTableDefs;
	}
	public String[] getAnnenkomplikasjonTableDefs() {
		return annenkomplikasjonTableDefs;
	}
	public void setAnnenkomplikasjonTableDefs(String[] annenkomplikasjonTableDefs) {
		this.annenkomplikasjonTableDefs = annenkomplikasjonTableDefs;
	}
	public String getSelectannenKomplikasjonSQL() {
		return selectannenKomplikasjonSQL;
	}
	public void setSelectannenKomplikasjonSQL(String selectannenKomplikasjonSQL) {
		this.selectannenKomplikasjonSQL = selectannenKomplikasjonSQL;
	}

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
	public Map<String,List> selectMeldinger (String meldingsNokkel){
		int type = Types.VARCHAR;
		Map alleMeldinger = new HashMap<String,List>();
		vigilansSelect = new VigilansSelect(getDataSource(),selectvigilansMeldingSQL,vigilandsMeldingTableDefs);
		vigilansSelect.declareParameter(new SqlParameter(type));
		List meldinger = vigilansSelect.execute(meldingsNokkel);
		alleMeldinger.put(meldingsNokkel, meldinger);
		if (!meldinger.isEmpty()){
			Vigilansmelding melding = (Vigilansmelding)meldinger.get(0);
			Long mId = melding.getMeldeid();
			int nType = Types.INTEGER;
			List delMelding = velgMeldinger(mId,nType);
			alleMeldinger.put(delMeldingKey, delMelding);
		}
		return alleMeldinger;
	}
	/**
	 * velgMeldinger
	 * Denne rutinen henter en delmelding til en vigilansmelding basert på meldingsid.
	 * En delmelding er enten av type annenkomplikasjon,pasientkomplikasjon eller giverkomplikasjon
	 * @param mId  meldingsid
	 * @param nType
	 * @return
	 */
	private List velgMeldinger(Long mId,int nType){
		annenmeldingSelect = new AnnenkomplikasjonSelect(getDataSource(),selectannenKomplikasjonSQL, annenkomplikasjonTableDefs);
		annenmeldingSelect.declareParameter(new SqlParameter(nType));
		List delMeldinger = annenmeldingSelect.execute(mId);
		delMeldingKey = andreKey;
		if (delMeldinger.isEmpty()){
			annenmeldingSelect = null;
			delMeldingKey = pasientKey;
			pasientmeldingSelect = new PasientkomplikasjonSelect(getDataSource(),selectpasientKomplikasjonSQL,pasientkomplikasjonTableDefs);
			pasientmeldingSelect.declareParameter(new SqlParameter(nType));
			delMeldinger = pasientmeldingSelect.execute(mId);
		}
		
		return delMeldinger;
		
	}
}	
