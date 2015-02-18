package no.naks.biovigilans.service;

import java.util.List;
import java.util.Map;

import no.naks.biovigilans.model.Melder;

public interface MelderTableService {
	
	public void saveMelder(Melder melder);
	public List selectMelder(String epost);
	public List selectMeldinger(String meldingsNokkel);
}
