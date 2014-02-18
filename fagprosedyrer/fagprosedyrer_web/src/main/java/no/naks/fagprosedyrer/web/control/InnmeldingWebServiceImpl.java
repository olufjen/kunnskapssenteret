package no.naks.fagprosedyrer.web.control;

import no.naks.fagprosedyrer.web.client.resource.InnmeldingResource;
import no.naks.fagprosedyrer.web.model.Innmelding;
import no.naks.framework.web.control.MasterWebServiceImpl;

public class InnmeldingWebServiceImpl extends MasterWebServiceImpl implements
		InnmeldingWebService{

	public InnmeldingWebServiceImpl() {
		super();
		 System.out.println("Innmeldingwebservice created");
	}

	@Override
	public void saveInnmelding(Innmelding innMelding) {
		
		
	}



}
