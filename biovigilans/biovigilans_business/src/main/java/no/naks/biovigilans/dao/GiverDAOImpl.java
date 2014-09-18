package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Giver;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.model.Antistoff;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Sykdom;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;


public class GiverDAOImpl extends AbstractAdmintablesDAO implements GiverDAO {

	private String insertGiverSQL;
	private String updateGiverSQL;
	private Tablesupdate tablesUpdate = null;
	private String giverPrimaryKey;
	private String[] giverprimarykeyTableDefs;
	
	private String insertMeldingSQL;
	private String updateMeldingSQL;
	private String meldingPrimaryKey;
	private String[] meldingprimarykeyTableDefs; 
	
	
	public String getInsertGiverSQL() {
		return insertGiverSQL;
	}
	public void setInsertGiverSQL(String insertGiverSQL) {
		this.insertGiverSQL = insertGiverSQL;
	}

	public String getUpdateGiverSQL() {
		return updateGiverSQL;
	}
	public void setUpdateGiverSQL(String updateGiverSQL) {
		this.updateGiverSQL = updateGiverSQL;
	}
	
	public String getGiverPrimaryKey() {
		return giverPrimaryKey;
	}
	public void setGiverPrimaryKey(String giverPrimaryKey) {
		this.giverPrimaryKey = giverPrimaryKey;
	}
	public String[] getGiverprimarykeyTableDefs() {
		return giverprimarykeyTableDefs;
	}
	public void setGiverprimarykeyTableDefs(String[] giverprimarykeyTableDefs) {
		this.giverprimarykeyTableDefs = giverprimarykeyTableDefs;
	}
	
	
	public String getInsertMeldingSQL() {
		return insertMeldingSQL;
	}
	public void setInsertMeldingSQL(String insertMeldingSQL) {
		this.insertMeldingSQL = insertMeldingSQL;
	}
	public String getUpdateMeldingSQL() {
		return updateMeldingSQL;
	}
	public void setUpdateMeldingSQL(String updateMeldingSQL) {
		this.updateMeldingSQL = updateMeldingSQL;
	}
	public String getMeldingPrimaryKey() {
		return meldingPrimaryKey;
	}
	public void setMeldingPrimaryKey(String meldingPrimaryKey) {
		this.meldingPrimaryKey = meldingPrimaryKey;
	}
	public String[] getMeldingprimarykeyTableDefs() {
		return meldingprimarykeyTableDefs;
	}
	public void setMeldingprimarykeyTableDefs(String[] meldingprimarykeyTableDefs) {
		this.meldingprimarykeyTableDefs = meldingprimarykeyTableDefs;
	}
	
	
	public void saveGiver(Giver giver) {
	
		giver.setParams();
		
		int[] types = giver.getTypes();
		Object[] params = giver.getParams();
		String sql = insertGiverSQL;
		Long id = giver.getGiverid();
		if(id!=null){
			sql=updateGiverSQL;
			types = giver.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			giver.setGiverid(getPrimaryKey(giverPrimaryKey, giverprimarykeyTableDefs));
		}
		
	}
	
	public void saveVigilansmelding(Vigilansmelding melding){
		melding.setMeldingParams();
		melding.setMeldingTypes();
		int[]meldingTypes = melding.getTypes();
		Object[]meldingParams = melding.getParams();
		Long id = melding.getMeldeid();
		String meldeSQL = insertMeldingSQL;
		if (id != null){
			meldeSQL = updateMeldingSQL;
			meldingTypes = melding.getUtypes();
		}
		if (id == null){
			melding.setMeldeid(getPrimaryKey(meldingPrimaryKey,meldingprimarykeyTableDefs));
		}
	//	id = melding.getMeldeid();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),meldeSQL,meldingTypes);
		tablesUpdate.insert(meldingParams);
		
		if(id==null){
			melding.setMeldeid(getPrimaryKey(meldingPrimaryKey, meldingprimarykeyTableDefs));
		}
		
	}

}
