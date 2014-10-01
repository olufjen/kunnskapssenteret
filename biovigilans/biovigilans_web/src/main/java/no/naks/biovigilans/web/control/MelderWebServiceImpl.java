package no.naks.biovigilans.web.control;

import no.naks.biovigilans.service.MelderTableService;
import no.naks.biovigilans.web.model.MelderwebModel;

public class MelderWebServiceImpl implements MelderWebService {

	private MelderTableService melderTableService;
	
	public MelderWebServiceImpl(){
		super();
	}
	
	public MelderTableService getMelderTableService() {
		return melderTableService;
	}

	public void setMelderTableService(MelderTableService melderTableService) {
		this.melderTableService = melderTableService;
	}

	public void saveMelder(MelderwebModel melderwebModel) {
		
		melderTableService.saveMelder(melderwebModel.getMelder());
	}

}
