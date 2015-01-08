package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.model.Komplikasjonsklassifikasjon;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

public class KomplikasjonsklassifikasjonDAOImpl extends AbstractAdmintablesDAO implements KomplikasjonsklassifikasjonDAO {
	
	private String insertKomplikasjonsklassifikasjonSQL;
	private String[] komplikasjonsklassifikasjonprimarykeyTableDefs;
	private String komplikasjonsklassifikasjonPrimaryKey;
	private String updateKomplikasjonsklassifikasjonSQL;
	private Tablesupdate tablesUpdate = null;
	
	public String getInsertKomplikasjonsklassifikasjonSQL() {
		return insertKomplikasjonsklassifikasjonSQL;
	}
	public void setInsertKomplikasjonsklassifikasjonSQL(
			String insertKomplikasjonsklassifikasjonSQL) {
		this.insertKomplikasjonsklassifikasjonSQL = insertKomplikasjonsklassifikasjonSQL;
	}
	public String[] getKomplikasjonsklassifikasjonprimarykeyTableDefs() {
		return komplikasjonsklassifikasjonprimarykeyTableDefs;
	}
	public void setKomplikasjonsklassifikasjonprimarykeyTableDefs(
			String[] komplikasjonsklassifikasjonprimarykeyTableDefs) {
		this.komplikasjonsklassifikasjonprimarykeyTableDefs = komplikasjonsklassifikasjonprimarykeyTableDefs;
	}
	public String getKomplikasjonsklassifikasjonPrimaryKey() {
		return komplikasjonsklassifikasjonPrimaryKey;
	}
	public void setKomplikasjonsklassifikasjonPrimaryKey(
			String komplikasjonsklassifikasjonPrimaryKey) {
		this.komplikasjonsklassifikasjonPrimaryKey = komplikasjonsklassifikasjonPrimaryKey;
	}
	public String getUpdateKomplikasjonsklassifikasjonSQL() {
		return updateKomplikasjonsklassifikasjonSQL;
	}
	public void setUpdateKomplikasjonsklassifikasjonSQL(
			String updateKomplikasjonsklassifikasjonSQL) {
		this.updateKomplikasjonsklassifikasjonSQL = updateKomplikasjonsklassifikasjonSQL;
	}
	
	

	public void saveAnnenKomplikasjon(Komplikasjonsklassifikasjon komplikasjonsklassifikasjon){
		komplikasjonsklassifikasjon.setParams();
		
		int[] types = komplikasjonsklassifikasjon.getTypes();
		Object[] params = komplikasjonsklassifikasjon.getParams();
		String sql = insertKomplikasjonsklassifikasjonSQL;
		Long id =komplikasjonsklassifikasjon.getKlassifikasjonsid();
		if(id!=null){
			sql = updateKomplikasjonsklassifikasjonSQL;
			types = komplikasjonsklassifikasjon.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		
	}
	
	

}
