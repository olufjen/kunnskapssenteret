package no.naks.biovigilans.felles.control;

import no.naks.biovigilans.model.Komplikasjonsklassifikasjon;
import no.naks.biovigilans.service.KomplikasjonsklassifikasjonTableService;

public class KomplikasjonsklassifikasjonWebServiceImpl implements KomplikasjonsklassifikasjonWebService {

	KomplikasjonsklassifikasjonTableService komplikasjonsklassifikasjonTableService;

	public KomplikasjonsklassifikasjonTableService getKomplikasjonsklassifikasjonTableService() {
		return komplikasjonsklassifikasjonTableService;
	}

	public void setKomplikasjonsklassifikasjonTableService(
			KomplikasjonsklassifikasjonTableService komplikasjonsklassifikasjonTableService) {
		this.komplikasjonsklassifikasjonTableService = komplikasjonsklassifikasjonTableService;
	}
	
	public void saveKomplikasjonsklassifikasjon(Komplikasjonsklassifikasjon komplikasjonsklassifikasjon){
		komplikasjonsklassifikasjonTableService.saveKomplikasjonsklassifikasjon(komplikasjonsklassifikasjon);
	}
	
}
