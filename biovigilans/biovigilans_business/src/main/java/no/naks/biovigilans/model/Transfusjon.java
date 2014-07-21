package no.naks.biovigilans.model;

import java.util.Date;
import java.util.Map;

public interface Transfusjon {

	public Date getTransfusjondato();

	public void setTransfusjondato(Date transfusjondato);

	public String getTransfusjonsklokkeslett();

	public void setTransfusjonsklokkeslett(String transfusjonsklokkeslett);

	public String getHastegrad();

	public void setHastegrad(String hastegrad);

	public String getFeiltranfudert();

	public void setFeiltranfudert(String feiltranfudert);

	public String getIndikasjon();

	public void setIndikasjon(String indikasjon);

	public int getAntalenheter();

	public void setAntalenheter(int antalenheter);

	public Map<String, Pasientkomplikasjon> getPasientKomplikasjoner();

	public void setPasientKomplikasjoner(
			Map<String, Pasientkomplikasjon> pasientKomplikasjoner);

	public Map<String, String> getTransfusjonsFields();

	public void setTransfusjonsFields(Map<String, String> transfusjonsFields);

	public String[] getKeys();

	public void setKeys(String[] keys);
	public void settransfusjonsFieldsMaps(String[]userFields);
	

	public Map<String, Blodprodukt> getBlodProdukter();

	public void setBlodProdukter(Map<String, Blodprodukt> blodProdukter);
	
	
}
