package no.naks.biovigilans_admin.web.control;

import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.service.PasientTableService;

import no.naks.framework.web.control.MasterWebService;

public interface HendelseWebService extends MasterWebService {


	public PasientTableService getPasientService();


	public void setPasientService(PasientTableService pasientService);
	public void saveVigilansMelder(Vigilansmelding melding);
	
	
}
