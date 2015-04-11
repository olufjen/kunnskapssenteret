package no.naks.biovigilans.felles.control;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.felles.model.MelderwebModel;

public interface MelderWebService {

	public void saveMelder(MelderwebModel melderwebModel);
	public List selectMelder(String epost);
	public Map<String,List> selectMeldinger(String meldingsNokkel);
}
