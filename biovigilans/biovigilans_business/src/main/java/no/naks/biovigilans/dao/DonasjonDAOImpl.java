package no.naks.biovigilans.dao;

import com.hp.hpl.jena.sparql.engine.http.Params;

import no.naks.biovigilans.model.Donasjon;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

public class DonasjonDAOImpl extends AbstractAdmintablesDAO implements DonasjonDAO {


	private String insertDonasjonSQL;
	private String updateDonasjonSQL;
	private String donasjonPrimaryKey;
	private String[] donasjonprimarykeyTableDefs;
	private Tablesupdate tablesUpdate = null;
	
	
	

	
	public String getInsertDonasjonSQL() {
		return insertDonasjonSQL;
	}



	public String getUpdateDonasjonSQL() {
		return updateDonasjonSQL;
	}



	public void setUpdateDonasjonSQL(String updateDonasjonSQL) {
		this.updateDonasjonSQL = updateDonasjonSQL;
	}



	public void setInsertDonasjonSQL(String insertDonasjonSQL) {
		this.insertDonasjonSQL = insertDonasjonSQL;
	}



	public String getDonasjonPrimaryKey() {
		return donasjonPrimaryKey;
	}



	public void setDonasjonPrimaryKey(String donasjonPrimaryKey) {
		this.donasjonPrimaryKey = donasjonPrimaryKey;
	}


	public String[] getDonasjonprimarykeyTableDefs() {
		return donasjonprimarykeyTableDefs;
	}



	public void setDonasjonprimarykeyTableDefs(String[] donasjonprimarykeyTableDefs) {
		this.donasjonprimarykeyTableDefs = donasjonprimarykeyTableDefs;
	}



	public void saveDonasjonDAO(Donasjon donasjon) {
		donasjon.setParams();
		int[] types= donasjon.getTypes();
		Object[] params = donasjon.getParams();
		String sql=insertDonasjonSQL;
		Long id = donasjon.getDonasjonsId();
		if(id!=null){
			sql = updateDonasjonSQL;
		}

		tablesUpdate = new TablesUpdateImpl(getDataSource(), sql, types);
		tablesUpdate.insert(params);
		
		if(id==null){
			donasjon.setDonasjonsId(getPrimaryKey(donasjonPrimaryKey,donasjonprimarykeyTableDefs));
		}
		
	}

}
