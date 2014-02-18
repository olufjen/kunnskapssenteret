package no.naks.fagprosedyrer.web.control;

import no.naks.fagprosedyrer.web.model.Innmelding;
import no.naks.framework.web.control.MasterWebService;

public interface InnmeldingWebService extends MasterWebService {

	public void saveInnmelding(Innmelding innMelding);
	
}
