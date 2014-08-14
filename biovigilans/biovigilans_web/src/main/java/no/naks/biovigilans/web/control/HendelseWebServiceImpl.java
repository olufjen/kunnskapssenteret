package no.naks.biovigilans.web.control;

import no.naks.biovigilans.model.PasientGruppeImpl;
import no.naks.biovigilans.service.PasientTableService;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.framework.web.control.MasterWebServiceImpl;

public class HendelseWebServiceImpl extends MasterWebServiceImpl implements
		HendelseWebService{

	private PasientTableService pasientService;
	
	public HendelseWebServiceImpl() {
		super();
		 System.out.println("Hendelsewebservice created");
	}

	
	public PasientTableService getPasientService() {
		return pasientService;
	}


	public void setPasientService(PasientTableService pasientService) {
		this.pasientService = pasientService;
	}



	public void saveHendelse(PasientKomplikasjonWebModel innMelding) {

		pasientService.savePasient(innMelding.getPasient());
		
	}


	
	public void saveTransfusjon(TransfusjonWebModel transfusjon,PasientKomplikasjonWebModel innMelding) {
		transfusjon.setPasient(innMelding.getPasient());
		pasientService.saveTransfusjon(transfusjon.getTransfusjon());
		
	}



}
