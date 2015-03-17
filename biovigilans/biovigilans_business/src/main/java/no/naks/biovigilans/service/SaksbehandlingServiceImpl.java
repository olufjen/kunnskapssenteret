package no.naks.biovigilans.service;

import java.util.List;

import no.naks.biovigilans.dao.SaksbehandlingDAO;

public class SaksbehandlingServiceImpl implements SaksbehandlingService {
	private SaksbehandlingDAO saksbehandlingDAO;
	
	
	public SaksbehandlingDAO getSaksbehandlingDAO() {
		return saksbehandlingDAO;
	}


	public void setSaksbehandlingDAO(SaksbehandlingDAO saksbehandlingDAO) {
		this.saksbehandlingDAO = saksbehandlingDAO;
	}


	@Override
	public List collectMessages() {
		
		return saksbehandlingDAO.collectMessages();
	}

}
