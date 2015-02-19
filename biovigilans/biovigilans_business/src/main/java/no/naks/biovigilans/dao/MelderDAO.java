package no.naks.biovigilans.dao;

import java.util.List;
import java.util.Map;
import no.naks.biovigilans.model.Melder;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

	public interface MelderDAO {
		
	public void setInsertMelderSQL(String insertMelderSQL) ;
	public String getMelderPrimaryKey() ;
	public void setMelderPrimaryKey(String melderPrimaryKey) ;
	public String[] getMelderprimarykeyTableDefs();
	public void setMelderprimarykeyTableDefs(String[] melderprimarykeyTableDefs) ;
	public Tablesupdate getTablesUpdate() ;
	public void setTablesUpdate(Tablesupdate tablesUpdate) ;
	public void saveMelder(Melder melder);
	public String getUpdateMelderSQL() ;
	public void setUpdateMelderSQL(String updateMelderSQL);
	public List selectMelder(String epost);
	public Map<String,List> selectMeldinger (String meldingsNokkel);
	public String[] getVigilandsMeldingTableDefs();
	public void setVigilandsMeldingTableDefs(String[] vigilandsMeldingTableDefs);
	public String getSelectvigilansMeldingSQL();
	public void setSelectvigilansMeldingSQL(String selectvigilansMeldingSQL);
	public String getSelectannenKomplikasjonSQL();
	public void setSelectannenKomplikasjonSQL(String selectannenKomplikasjonSQL);
	public String[] getAnnenkomplikasjonTableDefs();
	public void setAnnenkomplikasjonTableDefs(String[] annenkomplikasjonTableDefs);
}
