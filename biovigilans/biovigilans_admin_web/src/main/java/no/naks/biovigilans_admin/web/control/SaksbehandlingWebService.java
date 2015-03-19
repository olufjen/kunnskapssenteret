package no.naks.biovigilans_admin.web.control;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.service.MelderTableService;
import no.naks.biovigilans.service.SaksbehandlingService;

public interface SaksbehandlingWebService {

	public List collectMessages();
	public SaksbehandlingService getSaksbehandlingService();
	public void setSaksbehandlingService(SaksbehandlingService saksbehandlingService);	
	public Map<String,List> selectMeldinger(String meldingsNokkel);
	public MelderTableService getMelderTableService();
	public void setMelderTableService(MelderTableService melderTableService);

}
