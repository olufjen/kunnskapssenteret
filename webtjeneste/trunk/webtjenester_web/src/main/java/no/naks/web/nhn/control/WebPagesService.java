package no.naks.web.nhn.control;

import java.util.Date;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.model.SelectItem;

import no.naks.emok.model.IBasismelding;
import no.naks.nhn.model.Person;
import no.naks.services.nhn.client.adr.CommunicationPartyStub.CommunicationParty;

public interface WebPagesService {
	public TableWebService getTableWebService();

	public void setTableWebService(TableWebService tableWebService);

	public SelectItem[] getForetakItems();

	public void setForetakItems(SelectItem[] foretakItems);

	public UISelectItems getForetak();

	public void setForetak(UISelectItems foretak);

	public List getForetakUIlist();

	public void setForetakUIlist(List foretakUIlist);
	public void findOrganisation(String herId);
	public void searchOrganisation(String orgName);
	public void findDepartment(String aId);
	public UISelectItems getAvdeling();

	public void setAvdeling(UISelectItems avdeling);
	public List getAvdelingUIlist();

	public void setAvdelingUIlist(List avdelingUIlist);

	public IBasismelding getMelding();

	public void setMelding(IBasismelding melding);
	
	public Person getPerson();

	public boolean isIkkeSykehus();
	public void setIkkeSykehus(boolean ikkeSykehus);

	public String getSessionId();
	public void setSessionId(String sessionId);

	public String getValgtCommuncationParty();
	public void setValgtCommuncationParty(String valgtCommuncationParty);
	public String getClientIP();
	public void setClientIP(String clientIP);
	public void setPerson(Person person);
	public Person getLeder();
	public void setLeder(Person leder);
	
	public String getStedforHendelsen();

	public void setStedforHendelsen(String stedforHendelsen);

	public Date getTidForhendelsen();

	public void setTidForhendelsen(Date tidForhendelsen);

	public String getMale();

	public void setMale(String male);

	public String getFemale();

	public void setFemale(String female);

	public String getIkkeKjent();

	public void setIkkeKjent(String ikkeKjent);
	

	public String getCaseNr();

	public void setCaseNr(String caseNr);
	public String getKjonnKode();

	public void setKjonnKode(String kjonnKode);

	public String getKlokkeSlett();

	public void setKlokkeSlett(String klokkeSlett);
	public void setFullDate();
	public void setAvdsaksnumber();
	public void dognkodeIndex();
	
	public String getLareavHendelse();
	public void setLareavHendelse(String lareavHendelse);
	
	public String getSpesialSted();
	public void setSpesialSted(String spesialSted);

	public String getTempAr();
	public void setTempAr(String tempAr);

	public String getOrgHendelse();
	public void setOrgHendelse(String orgHendelse);
	public String getValgtOrganisasjon();
	public void setValgtOrganisasjon(String valgtOrganisasjon);
	
	public String getValgtDepartment();
	public void setValgtDepartment(String valgtDepartment);
	public List<CommunicationParty> getOrganisations() ;


}
