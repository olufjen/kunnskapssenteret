package no.naks.biovigilans.service;

import no.naks.biovigilans.dao.MelderDAO;
import no.naks.biovigilans.model.Melder;

public class MelderTableServiceImpl implements MelderTableService {

	private MelderDAO melderDAO;
	
	
	public MelderDAO getMelderDAO() {
		return melderDAO;
	}
	public void setMelderDAO(MelderDAO melderDAO) {
		this.melderDAO = melderDAO;
	}



	public void saveMelder(Melder melder) {
		
		melderDAO.saveMelder(melder);

	}

}
