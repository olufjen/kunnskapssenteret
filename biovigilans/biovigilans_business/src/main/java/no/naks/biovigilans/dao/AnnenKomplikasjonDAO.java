package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Annenkomplikasjon;

public interface AnnenKomplikasjonDAO {
	
	public String getInsertAnnenKomplikasjonSQL();
	public void setInsertAnnenKomplikasjonSQL(String insertGiverSQL);
	public String getAnnenKomplikasjonPrimaryKey() ;
	public void setAnnenKomplikasjonPrimaryKey(String giverPrimaryKey);
	public String[] getAnnenKomplikasjonprimarykeyTableDefs();
	public void setAnnenKomplikasjonprimarykeyTableDefs(String[] giverprimarykeyTableDefs);
	public void saveAnnenKomplikasjon(Annenkomplikasjon annenKomplikasjon);

}
