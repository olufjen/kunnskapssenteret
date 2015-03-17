package no.naks.biovigilans.dao;

import java.util.List;

import no.naks.biovigilans.service.SaksbehandlingService;
import no.naks.rammeverk.kildelag.dao.AbstractAdmintablesDAO;

public class SaksbehandlingDAOImpl extends AbstractAdmintablesDAO implements
		SaksbehandlingDAO{

	private String[] vigilandsMeldingTableDefs;
	private String selectvigilansMeldingSQL;
	private VigilansSelect vigilansSelect;

	
	public String[] getVigilandsMeldingTableDefs() {
		return vigilandsMeldingTableDefs;
	}


	public void setVigilandsMeldingTableDefs(String[] vigilandsMeldingTableDefs) {
		this.vigilandsMeldingTableDefs = vigilandsMeldingTableDefs;
	}


	public String getSelectvigilansMeldingSQL() {
		return selectvigilansMeldingSQL;
	}


	public void setSelectvigilansMeldingSQL(String selectvigilansMeldingSQL) {
		this.selectvigilansMeldingSQL = selectvigilansMeldingSQL;
	}


	@Override
	public List collectMessages() {
		vigilansSelect = new VigilansSelect(getDataSource(),selectvigilansMeldingSQL,vigilandsMeldingTableDefs);
		List meldinger = vigilansSelect.execute();
		return meldinger;
	}

}
