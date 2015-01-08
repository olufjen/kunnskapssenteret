package no.naks.biovigilans.service;

import no.naks.biovigilans.dao.KomplikasjonsklassifikasjonDAO;
import no.naks.biovigilans.model.Komplikasjonsklassifikasjon;

public class KomplikasjonsklassifikasjonTableServiceImpl implements
		KomplikasjonsklassifikasjonTableService {

	KomplikasjonsklassifikasjonDAO komplikasjonsklassifikasjonDAO ;

	public KomplikasjonsklassifikasjonDAO getKomplikasjonsklassifikasjonDAO() {
		return komplikasjonsklassifikasjonDAO;
	}

	public void setKomplikasjonsklassifikasjonDAO(
			KomplikasjonsklassifikasjonDAO komplikasjonsklassifikasjonDAO) {
		this.komplikasjonsklassifikasjonDAO = komplikasjonsklassifikasjonDAO;
	}
	
	public void saveAnnenKomplikasjon(Komplikasjonsklassifikasjon komplikasjonsklassifikasjon){
		komplikasjonsklassifikasjonDAO.saveAnnenKomplikasjon(komplikasjonsklassifikasjon);
	}
}
