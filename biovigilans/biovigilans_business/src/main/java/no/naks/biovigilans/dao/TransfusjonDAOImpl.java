package no.naks.biovigilans.dao;

import java.sql.Time;
import java.util.Iterator;

import no.naks.biovigilans.model.AbstractVigilansmelding;
import no.naks.biovigilans.model.Blodprodukt;
import no.naks.biovigilans.model.Hemolyse;
import no.naks.biovigilans.model.Pasientkomplikasjon;
import no.naks.biovigilans.model.Produktegenskap;
import no.naks.biovigilans.model.Sykdom;
import no.naks.biovigilans.model.Symptomer;
import no.naks.biovigilans.model.Transfusjon;
import no.naks.biovigilans.model.Utredning;
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
	private String insertSymptomerSQL;
	private String symptomerPrimaryKey;
	private String[] symptomerprimarykeyTableDefs;
	private String insertUtredningSQL;
	private String insertHemolyseSQL;
	private String utredningPrimaryKey;
	private String[] utredningprimarykeyTabledefs;
	private String insertproduktEgenskapSQL;
	private String blodproduktPrimarykey;
	private String[] blodproduktPrimarykeyTableDefs;
	
	
	
	
		
	
	public String getInsertproduktEgenskapSQL() {
		return insertproduktEgenskapSQL;
	}


	public void setInsertproduktEgenskapSQL(String insertproduktEgenskapSQL) {
		this.insertproduktEgenskapSQL = insertproduktEgenskapSQL;
	}


	public String getBlodproduktPrimarykey() {
		return blodproduktPrimarykey;
	}


	public void setBlodproduktPrimarykey(String blodproduktPrimarykey) {
		this.blodproduktPrimarykey = blodproduktPrimarykey;
	}


	public String[] getBlodproduktPrimarykeyTableDefs() {
		return blodproduktPrimarykeyTableDefs;
	}


	public void setBlodproduktPrimarykeyTableDefs(
			String[] blodproduktPrimarykeyTableDefs) {
		this.blodproduktPrimarykeyTableDefs = blodproduktPrimarykeyTableDefs;
	}


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


	public String getInsertSymptomerSQL() {
		return insertSymptomerSQL;
	}


	public void setInsertSymptomerSQL(String insertSymptomerSQL) {
		this.insertSymptomerSQL = insertSymptomerSQL;
	}


	public String getSymptomerPrimaryKey() {
		return symptomerPrimaryKey;
	}


	public void setSymptomerPrimaryKey(String symptomerPrimaryKey) {
		this.symptomerPrimaryKey = symptomerPrimaryKey;
	}


	public String[] getSymptomerprimarykeyTableDefs() {
		return symptomerprimarykeyTableDefs;
	}


	public void setSymptomerprimarykeyTableDefs(
			String[] symptomerprimarykeyTableDefs) {
		this.symptomerprimarykeyTableDefs = symptomerprimarykeyTableDefs;
	}


	public String getInsertUtredningSQL() {
		return insertUtredningSQL;
	}


	public void setInsertUtredningSQL(String insertUtredningSQL) {
		this.insertUtredningSQL = insertUtredningSQL;
	}


	public String getInsertHemolyseSQL() {
		return insertHemolyseSQL;
	}


	public void setInsertHemolyseSQL(String insertHemolyseSQL) {
		this.insertHemolyseSQL = insertHemolyseSQL;
	}


	public String getUtredningPrimaryKey() {
		return utredningPrimaryKey;
	}


	public void setUtredningPrimaryKey(String utredningPrimaryKey) {
		this.utredningPrimaryKey = utredningPrimaryKey;
	}


	public String[] getUtredningprimarykeyTabledefs() {
		return utredningprimarykeyTabledefs;
	}


	public void setUtredningprimarykeyTabledefs(
			String[] utredningprimarykeyTabledefs) {
		this.utredningprimarykeyTabledefs = utredningprimarykeyTabledefs;
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
		melding.setMeldingTypes();
		int[]meldingTypes = melding.getTypes();
		Object[]meldingParams = melding.getParams();
		Long id = melding.getMeldeid();
		String meldeSQL = insertMeldingSQL;
		if (id != null){
			meldeSQL = updateMeldingSQL;
			meldingTypes = melding.getUtypes();
		}
		tablesUpdate = new TablesUpdateImpl(getDataSource(),meldeSQL,meldingTypes);
		tablesUpdate.insert(meldingParams);
		if (id == null){
			melding.setMeldeid(getPrimaryKey(meldingPrimaryKey,meldingprimarykeyTableDefs));
			melding.setMeldingsnokkel(null);
			melding.setMeldingParams();
			melding.setMeldingTypes();
			meldeSQL = updateMeldingSQL;
			meldingTypes = melding.getUtypes();
			meldingParams= melding.getParams();
			tablesUpdate = new TablesUpdateImpl(getDataSource(),meldeSQL,meldingTypes);
			tablesUpdate.insert(meldingParams);
		}
		id = melding.getMeldeid();
		tablesUpdate = null;

		pasientkomplikasjon.setTransfusjonsId(transId);
//		pasientkomplikasjon.setMeldeid(id);
		pasientkomplikasjon.setKomplikasjonstypes();
		pasientkomplikasjon.setParams();
		int[] types = pasientkomplikasjon.getTypes();
		Object[] params = pasientkomplikasjon.getParams();
		String sql = insertPasientkomplikasjonSQL;
		
	
		tablesUpdate = new TablesUpdateImpl(getDataSource(),sql,types);
		tablesUpdate.insert(params);
		Iterator iterator = pasientkomplikasjon.getSymptomer().keySet().iterator();
		while (iterator.hasNext()){
			String key = (String)iterator.next();
			Symptomer symptom = (Symptomer)pasientkomplikasjon.getSymptomer().get(key);
			symptom.setMeldeId(id);
			symptom.setParams();
			int[] sTypes = symptom.getTypes();
			Object[] sParams = symptom.getParams();
			Long bId = symptom.getSymptomId();
			String bSQL =  insertSymptomerSQL;
			Tablesupdate sykdomtablesUpdate = new TablesUpdateImpl(getDataSource(),bSQL,sTypes);
			sykdomtablesUpdate.insert(sParams);
			sykdomtablesUpdate= null;
		}
		Utredning utredning = pasientkomplikasjon.getUtredning();
		utredning.setMeldeId(id);
		utredning.setParams();
		int[] utypes = utredning.getTypes();
		Object[] uparams = utredning.getParams();
		String usql = insertUtredningSQL;
		Tablesupdate utredningUpdate = new TablesUpdateImpl(getDataSource(),usql,utypes);
		utredningUpdate.insert(uparams);
		utredning.setUtredningId(getPrimaryKey(utredningPrimaryKey,utredningprimarykeyTabledefs));
		Iterator utredIterator = utredning.getHemolyseAnalyser().keySet().iterator();
		while (utredIterator.hasNext()){
			String key = (String)utredIterator.next();
			Hemolyse hemolyseParam = (Hemolyse)utredning.getHemolyseAnalyser().get(key);
			hemolyseParam.setUtredningid(utredning.getUtredningId());
			hemolyseParam.setParams();
			int[]hTypes = hemolyseParam.getTypes();
			Object[] hParams = hemolyseParam.getParams();
			String hSQL = insertHemolyseSQL;
			Tablesupdate hemoTablesupdate = new TablesUpdateImpl(getDataSource(),hSQL,hTypes);
			hemoTablesupdate.insert(hParams);
			hemoTablesupdate = null;
		}
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
			blodprodukt.setBlodProduktId(getPrimaryKey(blodproduktPrimarykey,blodproduktPrimarykeyTableDefs));
			sykdomtablesUpdate= null;
			Iterator produktIterator = blodprodukt.getProduktEgenskaper().keySet().iterator();
			while(produktIterator.hasNext()){
				String pKey = (String) produktIterator.next();
				Produktegenskap egenskap = (Produktegenskap)blodprodukt.getProduktEgenskaper().get(pKey);
				egenskap.setBlodProduktId(blodprodukt.getBlodProduktId());
				egenskap.setParams();
				int[]ptypes = egenskap.getTypes();
				Object[] pParams = egenskap.getParams();
				String egenskapSQL = insertproduktEgenskapSQL;
				TablesUpdateImpl egenskapUpdate = new TablesUpdateImpl(getDataSource(),egenskapSQL,ptypes);
				egenskapUpdate.insert(pParams);
				egenskapUpdate = null;
				
			}
		}
		tablesUpdate = null;
		Time tidForkompl = transfusjon.getTransklokke();
		Iterator komplikasjoner = transfusjon.getPasientKomplikasjoner().keySet().iterator();
		while (komplikasjoner.hasNext()){
			String key = (String)komplikasjoner.next();
			Pasientkomplikasjon komplikasjon = (Pasientkomplikasjon)transfusjon.getPasientKomplikasjoner().get(key);
			Vigilansmelding melding = (Vigilansmelding)komplikasjon;
			melding.setKlokkesletthendelse(tidForkompl);
			savePasientkomplikasjon(id, komplikasjon);
		}
		
		
	}

}
