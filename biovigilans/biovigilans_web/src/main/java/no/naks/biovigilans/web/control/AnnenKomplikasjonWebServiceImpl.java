package no.naks.biovigilans.web.control;

import no.naks.biovigilans.model.Annenkomplikasjon;
import no.naks.biovigilans.service.AnnenKomplikasjonTableService;
import no.naks.biovigilans.web.model.AnnenKomplikasjonwebModel;

public class AnnenKomplikasjonWebServiceImpl implements
		AnnenKomplikasjonWebService {
	
	AnnenKomplikasjonTableService annenKomplikasjonTableService;

	public AnnenKomplikasjonTableService getAnnenKomplikasjonTableService() {
		return annenKomplikasjonTableService;
	}

	public void setAnnenKomplikasjonTableService(
			AnnenKomplikasjonTableService annenKomplikasjonTableService) {
		this.annenKomplikasjonTableService = annenKomplikasjonTableService;
	}
	
	
	public void saveAnnenKomplikasjon(AnnenKomplikasjonwebModel annenKomplikasjonWebModel){
		annenKomplikasjonTableService.saveAnnenKomplikasjon(annenKomplikasjonWebModel.getAnnenKomplikasjon());
	}
}
