package no.naks.biovigilans.service;

import no.naks.biovigilans.model.Giver;
import no.naks.biovigilans.model.Vigilansmelding;

public interface GiverTableService {
	
	public void saveGiver(Giver giver);
	public void saveVigilansmelding(Vigilansmelding vigilansmelding);

}
