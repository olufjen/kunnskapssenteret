package no.naks.fagprosedyrer.model;

import java.util.Date;

public interface Innmeldtprosedyre {

	public Long getInnmeldtprosedyre_Id();
	public void setInnmeldtprosedyre_Id(Long innmeldtprosedyre_Id);
	public String getFormal();
	public void setFormal(String formal);
	public Date getOppstartdato();
	public void setOppstartdato(Date oppstartdato);
	public Date getFerdigdato();
	public void setFerdigdato(Date ferdigdato);
	
}