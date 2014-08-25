package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Giver;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.model.Antistoff;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Sykdom;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;


public class GiverDAOImpl extends AbstractAdmintablesDAO implements GiverDAO {

	private String insertGiverSQL;
	private String updateGiverSQL;
	private Tablesupdate tablesUpdate = null;
	private String giverPrimaryKey;
	private String[] giverprimarykeyTableDefs;
	
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

}
