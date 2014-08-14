package no.naks.biovigilans.service;

import no.naks.biovigilans.dao.PasientDAO;
import no.naks.biovigilans.dao.TransfusjonDAO;
import no.naks.biovigilans.model.Pasient;
import no.naks.biovigilans.model.Pasientgruppe;
import no.naks.biovigilans.model.Transfusjon;

public class PasientTableServiceImpl implements PasientTableService {

	private PasientDAO pasientDAO;
	private TransfusjonDAO transfusjonDAO;

	public PasientDAO getPasientDAO() {
		return pasientDAO;
	}

	public void setPasientDAO(PasientDAO pasientDAO) {
		this.pasientDAO = pasientDAO;
	}

	
	public TransfusjonDAO getTransfusjonDAO() {
		return transfusjonDAO;
	}

	public void setTransfusjonDAO(TransfusjonDAO transfusjonDAO) {
		this.transfusjonDAO = transfusjonDAO;
	}

	public void savePasient(Pasient pasient){
		pasientDAO.savePasient(pasient);
	}

	
	public void saveTransfusjon(Transfusjon transfusjon) {
		transfusjonDAO.saveTransfusjon(transfusjon);
		
	}
	
}
