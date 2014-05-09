package no.naks.biovigilans.web.control;

import no.naks.biovigilans.model.PasientGruppeImpl;
import no.naks.biovigilans.web.client.resource.InnmeldingResource;
import no.naks.biovigilans.web.model.PasientKomplikasjon;
import no.naks.framework.web.control.MasterWebServiceImpl;

public class HendelseWebServiceImpl extends MasterWebServiceImpl implements
		HendelseWebService{

	public HendelseWebServiceImpl() {
		super();
		 System.out.println("Hendelsewebservice created");
	}

	@Override
	public void saveHendelse(PasientKomplikasjon innMelding) {

		
	}



}
