package no.naks.biovigilans.web.control;

import no.naks.biovigilans.model.PasientGruppeImpl;

import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.framework.web.control.MasterWebServiceImpl;

public class HendelseWebServiceImpl extends MasterWebServiceImpl implements
		HendelseWebService{

	public HendelseWebServiceImpl() {
		super();
		 System.out.println("Hendelsewebservice created");
	}

	@Override
	public void saveHendelse(PasientKomplikasjonWebModel innMelding) {

		
	}



}
