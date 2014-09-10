package no.naks.biovigilans.web.control;

import no.naks.biovigilans.service.DonasjonTableService;
import no.naks.biovigilans.web.model.DonasjonwebModel;

public class DonasjonWebServiceImpl implements DonasjonWebService {

	private DonasjonTableService donasjonTableService;
	
	
	public DonasjonWebServiceImpl() {
		super();
	}


	public DonasjonTableService getDonasjonTableService() {
		return donasjonTableService;
	}


	public void setDonasjonTableService(DonasjonTableService donasjonTableService) {
		this.donasjonTableService = donasjonTableService;
	}


	public void saveDonasjon(DonasjonwebModel donasjonwebModel) {
	
		donasjonTableService.saveDonasjon(donasjonwebModel.getDonasjon());
	}

}
