package no.naks.biovigilans.web.control;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.web.model.MelderwebModel;

public interface MelderWebService {

	public void saveMelder(MelderwebModel melderwebModel);
	public List selectMelder(String epost);
	public Map<String,List> selectMeldinger(String meldingsNokkel);
}
