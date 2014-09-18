package no.naks.biovigilans.web.control;

import no.naks.biovigilans.service.GiverTableService;
import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.framework.web.control.MasterWebServiceImpl;

public class GiverWebServiceImpl extends MasterWebServiceImpl implements GiverWebService {

	private GiverTableService giverTableService;
	
	public GiverWebServiceImpl(){
		super();
	}
	
	public GiverTableService getGiverTableService() {
		return giverTableService;
	}

	public void setGiverTableService(GiverTableService giverTableService) {
		this.giverTableService = giverTableService;
	}

	public void saveGiver(GiverKomplikasjonwebModel giverModel) {
		giverTableService.saveGiver(giverModel.getGiver());
		
	}
	public void saveVigilansmelding(GiverKomplikasjonwebModel giverModel) {
		giverTableService.saveVigilansmelding(giverModel.getVigilansmelding());
		
	}
}
