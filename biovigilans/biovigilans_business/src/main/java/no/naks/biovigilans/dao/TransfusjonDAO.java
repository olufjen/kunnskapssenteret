package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Transfusjon;;

public interface TransfusjonDAO {

	public void saveTransfusjon(Transfusjon transfusjon);
	public String getInsertblodProduktSQL();
	public void setInsertblodProduktSQL(String insertblodProduktSQL);
	public String getUpdateblodProduktSQL();
	public String getInsertTransfusjonSQL();
	public void setInsertTransfusjonSQL(String insertTransfusjonSQL);
	public String getUpdateTransfusjonSQL();
	public void setUpdateTransfusjonSQL(String updateTransfusjonSQL);
	public String getTransfusjonPrimaryKey();
	public void setTransfusjonPrimaryKey(String transfusjonPrimaryKey);
	public String[] getTransfusjonprimarykeyTableDefs();
	public void setTransfusjonprimarykeyTableDefs(
			String[] transfusjonprimarykeyTableDefs);
	public String getInsertPasientkomplikasjonSQL();
	public void setInsertPasientkomplikasjonSQL(String insertPasientkomplikasjonSQL);
	public String getUpdatePasientkomplikasjonSQL();
	public void setUpdatePasientkomplikasjonSQL(String updatePasientkomplikasjonSQL);
	public String getInsertMeldingSQL();
	public void setInsertMeldingSQL(String insertMeldingSQL);
	public String getUpdateMeldingSQL();
	public void setUpdateMeldingSQL(String updateMeldingSQL);
	public String getMeldingPrimaryKey();
	public void setMeldingPrimaryKey(String meldingPrimaryKey);
	public String[] getMeldingprimarykeyTableDefs();
	public void setMeldingprimarykeyTableDefs(String[] meldingprimarykeyTableDefs);

}
