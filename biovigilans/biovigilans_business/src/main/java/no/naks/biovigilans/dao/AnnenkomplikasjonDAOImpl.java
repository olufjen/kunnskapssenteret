package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

public class AnnenkomplikasjonDAOImpl extends AbstractAdmintablesDAO implements AnnenKomplikasjonDAO {

	private String insertAnnenKomplikasjonSQL;
	private String annenKomplikasjonPrimaryKey ;
	private String[] annenKomplikasjonprimarykeyTableDefs;
	private Tablesupdate tablesUpdate = null;
	
	public String getInsertAnnenKomplikasjonSQL() {
		return insertAnnenKomplikasjonSQL;
	}


	public void setInsertAnnenKomplikasjonSQL(String insertAnnenKomplikasjonSQL) {
		this.insertAnnenKomplikasjonSQL = insertAnnenKomplikasjonSQL;
	}

	public String getAnnenKomplikasjonPrimaryKey() {
		return annenKomplikasjonPrimaryKey;
	}


	public void setAnnenKomplikasjonPrimaryKey(String annenKomplikasjonPrimaryKey) {
		this.annenKomplikasjonPrimaryKey = annenKomplikasjonPrimaryKey;
	}


	public String[] getAnnenKomplikasjonprimarykeyTableDefs() {
		return annenKomplikasjonprimarykeyTableDefs;
	}


	public void setAnnenKomplikasjonprimarykeyTableDefs(
			String[] annenKomplikasjonprimarykeyTableDefs) {
		this.annenKomplikasjonprimarykeyTableDefs = annenKomplikasjonprimarykeyTableDefs;
	}


	public void saveAnnenKomplikasjon(Annenkomplikasjon annenKomplikasjon){
		annenKomplikasjon.setParams();
		
		int[] types = annenKomplikasjon.getTypes();
		Object[] params = annenKomplikasjon.getParams();
		String sql = insertAnnenKomplikasjonSQL;
		
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		
	}
}
