package no.naks.biovigilans.dao;

import java.util.Iterator;

import no.naks.biovigilans.model.AbstractVigilansmelding;
import no.naks.biovigilans.model.Blodprodukt;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.Sykdom;
import no.naks.biovigilans.model.Transfusjon;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;
import no.naks.rammeverk.kildelag.dao.TablesUpdateImpl;
import no.naks.rammeverk.kildelag.dao.Tablesupdate;

/**
 * Denne klassen lagrer en Transfusjon og alle relaterte tabeller
 * @author olj
 *
 */
public class TransfusjonDAOImpl extends AbstractAdmintablesDAO implements
		TransfusjonDAO {
	private Tablesupdate tablesUpdate = null;
	private String insertTransfusjonSQL;
	private String updateTransfusjonSQL;
	private String transfusjonPrimaryKey;
	private String[] transfusjonprimarykeyTableDefs;
	private String insertblodProduktSQL;
	private String updateblodProduktSQL;
	private String insertPasientkomplikasjonSQL;
	private String updatePasientkomplikasjonSQL;
	private String insertMeldingSQL;
	private String updateMeldingSQL;
	private String meldingPrimaryKey;
	private String[]meldingprimarykeyTableDefs;  
	
		
	
	public String getInsertTransfusjonSQL() {
		return insertTransfusjonSQL;
	}


	public void setInsertTransfusjonSQL(String insertTransfusjonSQL) {
		this.insertTransfusjonSQL = insertTransfusjonSQL;
	}


	public String getUpdateTransfusjonSQL() {
		return updateTransfusjonSQL;
	}


	public void setUpdateTransfusjonSQL(String updateTransfusjonSQL) {
		this.updateTransfusjonSQL = updateTransfusjonSQL;
	}


	public String getTransfusjonPrimaryKey() {
		return transfusjonPrimaryKey;
	}


	public void setTransfusjonPrimaryKey(String transfusjonPrimaryKey) {
		this.transfusjonPrimaryKey = transfusjonPrimaryKey;
	}


	public String[] getTransfusjonprimarykeyTableDefs() {
		return transfusjonprimarykeyTableDefs;
	}


	public void setTransfusjonprimarykeyTableDefs(
			String[] transfusjonprimarykeyTableDefs) {
		this.transfusjonprimarykeyTableDefs = transfusjonprimarykeyTableDefs;
	}


	public String getInsertblodProduktSQL() {
		return insertblodProduktSQL;
	}


	public void setInsertblodProduktSQL(String insertblodProduktSQL) {
		this.insertblodProduktSQL = insertblodProduktSQL;
	}


	public String getUpdateblodProduktSQL() {
		return updateblodProduktSQL;
	}


	public void setUpdateblodProduktSQL(String updateblodProduktSQL) {
		this.updateblodProduktSQL = updateblodProduktSQL;
	}


	public String getInsertPasientkomplikasjonSQL() {
		return insertPasientkomplikasjonSQL;
	}


	public void setInsertPasientkomplikasjonSQL(String insertPasientkomplikasjonSQL) {
		this.insertPasientkomplikasjonSQL = insertPasientkomplikasjonSQL;
	}


	public String getUpdatePasientkomplikasjonSQL() {
		return updatePasientkomplikasjonSQL;
	}


	public void setUpdatePasientkomplikasjonSQL(String updatePasientkomplikasjonSQL) {
		this.updatePasientkomplikasjonSQL = updatePasientkomplikasjonSQL;
	}


	public String getInsertMeldingSQL() {
		return insertMeldingSQL;
	}


	public void setInsertMeldingSQL(String insertMeldingSQL) {
		this.insertMeldingSQL = insertMeldingSQL;
	}


	public String getUpdateMeldingSQL() {
		return updateMeldingSQL;
	}


	public void setUpdateMeldingSQL(String updateMeldingSQL) {
		this.updateMeldingSQL = updateMeldingSQL;
	}


	public String getMeldingPrimaryKey() {
		return meldingPrimaryKey;
	}


	public void setMeldingPrimaryKey(String meldingPrimaryKey) {
		this.meldingPrimaryKey = meldingPrimaryKey;
	}


	public String[] getMeldingprimarykeyTableDefs() {
		return meldingprimarykeyTableDefs;
	}


	public void setMeldingprimarykeyTableDefs(String[] meldingprimarykeyTableDefs) {
		this.meldingprimarykeyTableDefs = meldingprimarykeyTableDefs;
	}


	/**
	 * savePasientkomplikasjon
	 * Denne rutinen lagrer en Vigilansmelding, en pasientkomplikasjon og relaterte tabeller
	 * @param transId TransfusjonID
	 * @param pasientkomplikasjon En pasientkomplikasjon
	 */
	private void savePasientkomplikasjon(Long transId,Pasientkomplikasjon pasientkomplikasjon){
		Vigilansmelding melding = (Vigilansmelding)pasientkomplikasjon;
		melding.setMeldingParams();
		int[]meldingTypes = melding.getTypes();
		Object[]meldingParams = melding.getParams();
		Long id = melding.getMeldeid();
		String meldeSQL = insertMeldingSQL;
		if (id != null){
			meldeSQL = updateMeldingSQL;
			meldingTypes = melding.getUtypes();
		}
		if (id == null){
			melding.setMeldeid(getPrimaryKey(meldingPrimaryKey,meldingprimarykeyTableDefs));
		}
		id = melding.getMeldeid();
		tablesUpdate = new TablesUpdateImpl(getDataSource(),meldeSQL,meldingTypes);
		tablesUpdate.insert(meldingParams);
		tablesUpdate = null;
		pasientkomplikasjon.setKomplikasjonstypes();
		pasientkomplikasjon.setParams();
		int[] types = pasientkomplikasjon.getTypes();
		Object[] params = pasientkomplikasjon.getParams();
		String sql = insertPasientkomplikasjonSQL;
		
		pasientkomplikasjon.setTransfusjonsId(transId);
	
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		
	}
	
	/* saveTransfusjon
	 * Denne rutinen lagrer en transfusjon til en gitt pasient og relaterte tabeller
	 * 
	 */
	public void saveTransfusjon(Transfusjon transfusjon) {
		transfusjon.setParams();
		int[] types = transfusjon.getTypes();
		
		Object[] params = transfusjon.getParams();
		String sql = insertTransfusjonSQL;
		Long id = transfusjon.getTransfusjonsId();
		if (id != null){
			sql = updateTransfusjonSQL;
			types = transfusjon.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		if (id == null){
			transfusjon.setTransfusjonsId(getPrimaryKey(transfusjonPrimaryKey,transfusjonprimarykeyTableDefs));
			id = transfusjon.getTransfusjonsId();
		}
		Iterator iterator = transfusjon.getBlodProdukter().keySet().iterator();
		while (iterator.hasNext()){
			String key = (String) iterator.next();
			Blodprodukt blodprodukt = (Blodprodukt)transfusjon.getBlodProdukter().get(key);
			
			blodprodukt.setTransfusjonsId(transfusjon.getTransfusjonsId());
			blodprodukt.setParams();
			int[] sTypes = blodprodukt.getTypes();
			Object[] sParams = blodprodukt.getParams();
			Long bId = blodprodukt.getBlodProduktId();
			String bSQL = insertblodProduktSQL;
			if (bId != null){
				bSQL = updateblodProduktSQL;
				sTypes = blodprodukt.getUtypes();
				
			}
			TablesUpdateImpl sykdomtablesUpdate = new TablesUpdateImpl(getDataSource(),bSQL,sTypes);
			sykdomtablesUpdate.insert(sParams);
			sykdomtablesUpdate= null;
			
		}
		tablesUpdate = null;
		Iterator komplikasjoner = transfusjon.getPasientKomplikasjoner().keySet().iterator();
		while (komplikasjoner.hasNext()){
			String key = (String)komplikasjoner.next();
			Pasientkomplikasjon komplikasjon = (Pasientkomplikasjon)transfusjon.getPasientKomplikasjoner().get(key);
			savePasientkomplikasjon(id, komplikasjon);
		}
		
		
	}

	public String getInsertTransfusjonSQL() {
		return insertTransfusjonSQL;
	}

	public void setInsertTransfusjonSQL(String insertTransfusjonSQL) {
		this.insertTransfusjonSQL = insertTransfusjonSQL;
	}

}
