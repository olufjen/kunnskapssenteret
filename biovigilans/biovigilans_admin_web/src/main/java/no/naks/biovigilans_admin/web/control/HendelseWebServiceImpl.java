package no.naks.biovigilans_admin.web.control;

import no.naks.biovigilans.model.PasientGruppeImpl;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.service.HendelseTablesService;
import no.naks.biovigilans.service.PasientTableService;

import no.naks.framework.web.control.MasterWebServiceImpl;

public class HendelseWebServiceImpl extends MasterWebServiceImpl implements
		HendelseWebService{

	private PasientTableService pasientService;
	private HendelseTablesService hendelseTablesService;
	
	public HendelseWebServiceImpl() {
		super();
		 System.out.println("Hendelsewebservice created");
	}

	
	public HendelseTablesService getHendelseTablesService() {
		return hendelseTablesService;
	}


	public void setHendelseTablesService(HendelseTablesService hendelseTablesService) {
		this.hendelseTablesService = hendelseTablesService;
	}


	public PasientTableService getPasientService() {
		return pasientService;
	}


	public void setPasientService(PasientTableService pasientService) {
		this.pasientService = pasientService;
	}




	/* 
	 * saveVigilansMelder
	 * Denne rutinen oppdaterer Vigilansmelding med melderid
	 */
	public void saveVigilansMelder(Vigilansmelding melding){
		hendelseTablesService.saveVigilansmelding(melding);
	}



}
