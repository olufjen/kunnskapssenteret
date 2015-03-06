package no.naks.biovigilans.web.control;

import java.util.List;

import no.naks.biovigilans.model.PasientGruppeImpl;
import no.naks.biovigilans.model.Vigilansmelding;
import no.naks.biovigilans.service.HendelseTablesService;
import no.naks.biovigilans.service.PasientTableService;
import no.naks.biovigilans.web.model.PasientKomplikasjonWebModel;
import no.naks.biovigilans.web.model.TransfusjonWebModel;
import no.naks.framework.web.control.MasterWebServiceImpl;

/**
 * HendelsesWebService
 * Denne tjenesten sørger for lagring av pasienthendelser
 * Den benyttes også til å hente ut alle meldinger knyttet til en gitt melder
 * @author olj
 *
 */
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



	public void saveHendelse(PasientKomplikasjonWebModel innMelding) {

		pasientService.savePasient(innMelding.getPasient());
		
	}


	
	public void saveTransfusjon(TransfusjonWebModel transfusjon,PasientKomplikasjonWebModel innMelding) {
		transfusjon.setPasient(innMelding.getPasient());
		pasientService.saveTransfusjon(transfusjon.getTransfusjon());
		
	}
	/* 
	 * saveVigilansMelder
	 * Denne rutinen oppdaterer Vigilansmelding med melderid
	 */
	public void saveVigilansMelder(Vigilansmelding melding){
		hendelseTablesService.saveVigilansmelding(melding);
	}

	public List<Vigilansmelding> collectMeldinger(Long meldeid){
		
		return hendelseTablesService.collectMeldinger(meldeid);
	}

}
