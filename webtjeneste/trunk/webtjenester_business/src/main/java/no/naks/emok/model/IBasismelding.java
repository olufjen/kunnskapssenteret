package no.naks.emok.model;

import java.util.Date;

import no.kith.xmlstds.emok.codes.CS;
import no.kith.xmlstds.emok.codes.CV;

public interface IBasismelding extends ILeveransemelding {

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#isSvarpaettersporring()
	 */
	public  boolean isSvarpaettersporring();

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#setSvarpaettersporring(boolean)
	 */
	public  void setSvarpaettersporring(boolean svarpaettersporring);

	/* (non-Javadoc)
	 * @see no.naks.emok.model.IBasismelding#getStedforhendelsen()
	 */
	public  String getStedforhendelsen();

	public  void setStedforhendelsen(String stedforhendelsen);

	public  String getHendelsesbeskrivelse();

	public  void setHendelsesbeskrivelse(String hendelsesbeskrivelse);

	public  String getUtfortestrakstiltak();

	public  void setUtfortestrakstiltak(String utfortestrakstiltak);

	public  String getForslagtiltak();

	public  void setForslagtiltak(String forslagtiltak);

	public  String getArsaksbeskrivelse();

	public  void setArsaksbeskrivelse(String arsaksbeskrivelse);

	public  String getKonsekvenser();

	public  void setKonsekvenser(String konsekvenser);

	public  String getHvordanoppdaget();

	public  void setHvordanoppdaget(String hvordanoppdaget);

	public  int getArfodt();

	public  void setArfodt(int arfodt);

	public  CS getKjonn();

	public  void setKjonn(CS kjonn);

	public  CS getDognkode();

	public  void setDognkode(CS dognkode);

	public  Date getTidforhendelsen();

	public  void setTidforhendelsen(Date tidforhendelsen);

	public  String getTypeuhell();

	public  void setTypeuhell(String typeuhell);

	public  CS getAlvorlighetsgrad();

	public  void setAlvorlighetsgrad(CS alvorlighetsgrad);

	public  String getRolle();

	public  void setRolle(String rolle);

	public  boolean isOnskerhjelp();

	public  void setOnskerhjelp(boolean onskerhjelp);
	
}