package no.naks.biovigilans.dao;

import no.naks.biovigilans.model.Transfusjon;
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
			
		}
	}

}
