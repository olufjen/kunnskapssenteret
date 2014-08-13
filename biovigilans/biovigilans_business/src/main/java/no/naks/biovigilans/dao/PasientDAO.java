package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Pasient;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;

/**
 * @author olj
 *
 */
public interface PasientDAO {

	public String getInsertPatientSQL();
	public void setInsertPatientSQL(String insertPatientSQL);
	public String[] getPatienttableDefs();
	public void setPatienttableDefs(String[] patienttableDefs);
	
	public String getUpdatePatientSQL();
	public void setUpdatePatientSQL(String updatePatientSQL);
	public void savePasient(Pasient pasient);
	public String getPasientPrimaryKey();
	public void setPasientPrimaryKey(String pasientPrimaryKey);
	public String[] getPasientprimarykeyTableDefs();
	public void setPasientprimarykeyTableDefs(String[] pasientprimarykeyTableDefs);
	
	public String getInsertsykdomSQL();
	public void setInsertsykdomSQL(String insertsykdomSQL);
	public String getUpdatesykdomSQL();
	public void setUpdatesykdomSQL(String updatesykdomSQL);
	public String[] getSykdomTableDefs();
	public void setSykdomTableDefs(String[] sykdomTableDefs);
	
	public String getInsertAntistoffSQL();
	public void setInsertAntistoffSQL(String insertAntistoffSQL);
	public String getUpdateAntistoffSQL();
	public void setUpdateAntistoffSQL(String updateAntistoffSQL);
	public String[] getAntistoffTableDefs();
	public void setAntistoffTableDefs(String[] antistoffTableDefs);
}
