package no.naks.fagprosedyrer.model;

import java.util.Date;

public interface Prosedyre {
	public String getTittel();
	public void setTittel(String tittel);
	public int getProsedyrestatus();
	public void setProsedyrestatus(int prosedyrestatus);
	public Date getLitterartursok();
	public void setLitterartursok(Date litterartursok);
	public Date getNestelitteratursok();
	public void setNestelitteratursok(Date nestelitteratursok);
	public String getVersjonsnr();
	public void setVersjonsnr(String versjonsnr);
	
}