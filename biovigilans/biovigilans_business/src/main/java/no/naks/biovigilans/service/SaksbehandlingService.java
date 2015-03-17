package no.naks.biovigilans.service;

import java.util.List;

import no.naks.biovigilans.dao.SaksbehandlingDAO;

public interface SaksbehandlingService {
	
	public SaksbehandlingDAO getSaksbehandlingDAO();


	public void setSaksbehandlingDAO(SaksbehandlingDAO saksbehandlingDAO);

	public List collectMessages();
}
