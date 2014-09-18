package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.Vigilansmelding;

public interface GiverDAO {
	
	public String getInsertGiverSQL();
	public void setInsertGiverSQL(String insertGiverSQL);
	public String getUpdateGiverSQL() ;
	public void setUpdateGiverSQL(String updateGiverSQL);
	public String getGiverPrimaryKey() ;
	public void setGiverPrimaryKey(String giverPrimaryKey);
	public String[] getGiverprimarykeyTableDefs();
	public void setGiverprimarykeyTableDefs(String[] giverprimarykeyTableDefs);
	public void saveGiver(Giver giver);
	public void saveVigilansmelding(Vigilansmelding melding);

}
