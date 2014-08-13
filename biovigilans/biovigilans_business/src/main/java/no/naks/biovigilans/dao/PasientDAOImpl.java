package no.naks.biovigilans.dao;

import java.util.Iterator;

import org.springframework.jdbc.core.SqlParameter;

import no.naks.biovigilans.model.Antistoff;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Sykdom;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

/**
 * Denne klassen lagrer til og henter pasientopplysninger fra databasen
 * 
 * @author olj
 *
 */
public class PasientDAOImpl extends AbstractAdmintablesDAO implements
		PasientDAO {

	private Tablesupdate tablesUpdate = null;
	private String insertPatientSQL;
	private String updatePatientSQL;
	private String pasientPrimaryKey;
	
	private String[] patienttableDefs;
	private String[] pasientprimarykeyTableDefs;
	
	private String insertsykdomSQL;
	private String updatesykdomSQL;
	private String[] sykdomTableDefs;
	private String insertAntistoffSQL;
	private String updateAntistoffSQL;
	private String[] antistoffTableDefs;
	
	
	
	public String getInsertPatientSQL() {
		return insertPatientSQL;
	}
	public void setInsertPatientSQL(String insertPatientSQL) {
		this.insertPatientSQL = insertPatientSQL;
	}
	public String[] getPatienttableDefs() {
		return patienttableDefs;
	}
	public void setPatienttableDefs(String[] patienttableDefs) {
		this.patienttableDefs = patienttableDefs;
	}
	
	public String getUpdatePatientSQL() {
		return updatePatientSQL;
	}
	public void setUpdatePatientSQL(String updatePatientSQL) {
		this.updatePatientSQL = updatePatientSQL;
	}
	
	public String getInsertAntistoffSQL() {
		return insertAntistoffSQL;
	}
	public void setInsertAntistoffSQL(String insertAntistoffSQL) {
		this.insertAntistoffSQL = insertAntistoffSQL;
	}
	public String getUpdateAntistoffSQL() {
		return updateAntistoffSQL;
	}
	public void setUpdateAntistoffSQL(String updateAntistoffSQL) {
		this.updateAntistoffSQL = updateAntistoffSQL;
	}
	public String[] getAntistoffTableDefs() {
		return antistoffTableDefs;
	}
	public void setAntistoffTableDefs(String[] antistoffTableDefs) {
		this.antistoffTableDefs = antistoffTableDefs;
	}
	public String getPasientPrimaryKey() {
		return pasientPrimaryKey;
	}
	public void setPasientPrimaryKey(String pasientPrimaryKey) {
		this.pasientPrimaryKey = pasientPrimaryKey;
	}
	public String[] getPasientprimarykeyTableDefs() {
		return pasientprimarykeyTableDefs;
	}
	public void setPasientprimarykeyTableDefs(String[] pasientprimarykeyTableDefs) {
		this.pasientprimarykeyTableDefs = pasientprimarykeyTableDefs;
	}

	
	public String getInsertsykdomSQL() {
		return insertsykdomSQL;
	}
	public void setInsertsykdomSQL(String insertsykdomSQL) {
		this.insertsykdomSQL = insertsykdomSQL;
	}
	public String getUpdatesykdomSQL() {
		return updatesykdomSQL;
	}
	public void setUpdatesykdomSQL(String updatesykdomSQL) {
		this.updatesykdomSQL = updatesykdomSQL;
	}
	public String[] getSykdomTableDefs() {
		return sykdomTableDefs;
	}
	public void setSykdomTableDefs(String[] sykdomTableDefs) {
		this.sykdomTableDefs = sykdomTableDefs;
	}
	/* savePasient
	 * Denne rutinen lagrer informasjon om pasient
	 * @see no.naks.biovigilans.dao.PasientDAO#savePasient(no.naks.biovigilans.model.Pasient)
	 */
	public void savePasient(Pasient pasient){
		
		pasient.setParams();
		int[] types = pasient.getTypes();
		Object[] params = pasient.getParams();
		String sql = insertPatientSQL;
		Long id = pasient.getPasient_Id();
		if (id != null){
			sql = updatePatientSQL;
			types = pasient.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			pasient.setPasient_Id(getPrimaryKey(pasientPrimaryKey,pasientprimarykeyTableDefs));
		}
//		tablesUpdate = null;
		
		Iterator iterator = pasient.getSykdommer().keySet().iterator();
		while (iterator.hasNext()){
			String key =(String) iterator.next();
			Sykdom sykdom = (Sykdom)pasient.getSykdommer().get(key);
			sykdom.setPasient_Id(pasient.getPasient_Id());
			sykdom.setParams();
			int[] sTypes = sykdom.getTypes();
			Object[] sParams = sykdom.getParams();
			Long sId = sykdom.getSykdomId();
			String ssSQL = insertsykdomSQL;
			if (sId != null){
				ssSQL = updatesykdomSQL;
				sTypes = sykdom.getUtypes();
			}
			TablesUpdateImpl sykdomtablesUpdate = new TablesUpdateImpl(getDataSource(),ssSQL,sTypes);
			sykdomtablesUpdate.insert(sParams);
			sykdomtablesUpdate= null;
		}
		Iterator antiIterator = pasient.getAntistoffer().keySet().iterator();
		while (antiIterator.hasNext()){
			String key =(String) antiIterator.next();
			Antistoff antistoff = (Antistoff)pasient.getAntistoffer().get(key);
			antistoff.setPasient_Id(pasient.getPasient_Id());
			antistoff.setParams();
		}

	}
}
