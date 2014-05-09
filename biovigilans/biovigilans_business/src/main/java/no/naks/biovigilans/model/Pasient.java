package no.naks.biovigilans.model;

public interface Pasient {
	public Long getPasient_Id();
	public void setPasient_Id(Long pasient_Id);
	public String getKjonn();
	public void setKjonn(String kjonn);
	public String getAldersGruppe();
	public void setAldersGruppe(String aldersGruppe);
	public String getAntiStoff();
	public void setAntiStoff(String antiStoff);
	public String getInneliggendePoli();
	public void setInneliggendePoli(String inneliggendePoli);
	public String getAvdeling();
	public void setAvdeling(String avdeling);
}
