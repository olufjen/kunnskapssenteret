package no.naks.biovigilans_admin.web.control;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.service.MelderTableService;
import no.naks.biovigilans.service.SaksbehandlingService;
import no.naks.framework.web.control.MasterWebServiceImpl;

/**
 * Denne klassen er WebService for saksbehandling
 * @author olj
 *
 */
public class SaksbehandlingWebServiceImpl extends MasterWebServiceImpl
		implements SaksbehandlingWebService {
	private SaksbehandlingService saksbehandlingService;
	private MelderTableService melderTableService;
	
	public SaksbehandlingWebServiceImpl() {
		super();
		 System.out.println("Saksbehandlingwebservice admin created");
	}


	public MelderTableService getMelderTableService() {
		return melderTableService;
	}


	public void setMelderTableService(MelderTableService melderTableService) {
		this.melderTableService = melderTableService;
	}


	public SaksbehandlingService getSaksbehandlingService() {
		return saksbehandlingService;
	}


	public void setSaksbehandlingService(SaksbehandlingService saksbehandlingService) {
		this.saksbehandlingService = saksbehandlingService;
	}


	@Override
	public List collectMessages() {
		
		return saksbehandlingService.collectMessages();
	}
	public Map<String,List> selectMeldinger(String meldingsNokkel){
		Map<String,List> meldinger = melderTableService.selectMeldinger(meldingsNokkel);
		return meldinger;
	}


}
