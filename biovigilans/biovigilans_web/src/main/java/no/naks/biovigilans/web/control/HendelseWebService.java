package no.naks.biovigilans.web.control;

import no.naks.biovigilans.service.PasientTableService;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.framework.web.control.MasterWebService;

public interface HendelseWebService extends MasterWebService {

	public void saveHendelse(PasientKomplikasjonWebModel innMelding);
	public PasientTableService getPasientService();


	public void setPasientService(PasientTableService pasientService);
	
}
