package no.naks.biovigilans.service;

import no.naks.biovigilans.dao.PasientDAO;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Transfusjon;

public interface PasientTableService {

	public PasientDAO getPasientDAO();

	public void setPasientDAO(PasientDAO pasientDAO);
	
	public void savePasient(Pasient pasient);
	public void saveTransfusjon(Transfusjon transfusjon);
	
}
