package no.naks.biovigilans.felles.control;

import java.util.List;

import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.service.PasientTableService;
import no.naks.biovigilans.felles.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.felles.model.TransfusjonWebModel;
import no.naks.framework.web.control.MasterWebService;

public interface HendelseWebService extends MasterWebService {

	public void saveHendelse(PasientKomplikasjonWebModel innMelding);
	public void saveTransfusjon(TransfusjonWebModel transfusjon,PasientKomplikasjonWebModel innMelding);
	public PasientTableService getPasientService();


	public void setPasientService(PasientTableService pasientService);
	public void saveVigilansMelder(Vigilansmelding melding);
	public List<Vigilansmelding> collectMeldinger(Long meldeid);
	
}
