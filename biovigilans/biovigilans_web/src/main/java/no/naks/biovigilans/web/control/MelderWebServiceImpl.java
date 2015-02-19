package no.naks.biovigilans.web.control;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.service.MelderTableService;
import no.naks.biovigilans.web.model.MelderwebModel;

public class MelderWebServiceImpl implements MelderWebService {

	private MelderTableService melderTableService;
	
	public MelderWebServiceImpl(){
		super();
	}
	
	public MelderTableService getMelderTableService() {
		return melderTableService;
	}

	public void setMelderTableService(MelderTableService melderTableService) {
		this.melderTableService = melderTableService;
	}

	public void saveMelder(MelderwebModel melderwebModel) {
		
		melderTableService.saveMelder(melderwebModel.getMelder());
	}
	
	public List selectMelder(String epost){
		return melderTableService.selectMelder(epost);
	}
	public Map<String,List> selectMeldinger(String meldingsNokkel){
		return melderTableService.selectMeldinger(meldingsNokkel);
	}

}
