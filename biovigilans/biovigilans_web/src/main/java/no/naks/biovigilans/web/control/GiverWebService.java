package no.naks.biovigilans.web.control;

import no.naks.biovigilans.web.model.GiverKomplikasjonwebModel;
import no.naks.framework.web.control.MasterWebService;

public interface GiverWebService extends MasterWebService {

		public void saveGiver(GiverKomplikasjonwebModel giverModel);
		public void saveVigilansmelding(GiverKomplikasjonwebModel giverModel);
		public void saveGiverkomplikasjon(GiverKomplikasjonwebModel giverModel);
}
